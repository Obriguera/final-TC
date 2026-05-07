import java.util.ArrayList;
import java.util.List;

public class Semantico extends gramaticaBaseVisitor<Object> {
    private TablaSimbolos tabla = new TablaSimbolos();
    private int errores = 0;

    public int getErrores() { return errores; }

    private void reportarError(String mensaje) {
        System.err.println("Error Semantico: " + mensaje);
        errores++;
    }

    private void reportarErrorTipos(String id, String esperado, String encontrado) {
        System.err.println("Error Semantico en '" + id + "': Tipos incompatibles. Esperado: " + esperado + ", Encontrado: " + encontrado);
        errores++;
    }

    @Override
    public Object visitR(gramaticaParser.RContext ctx) {
        tabla.enterScope(); // Ámbito Global
        Object res = visitChildren(ctx);
        tabla.exitScope();
        return res;
    }

    // --- GESTIÓN DE VARIABLES Y ARRAYS ---

    @Override
    public Object visitDeclVar(gramaticaParser.DeclVarContext ctx) {
        String tipo = ctx.tipo().getText();
        String id = ctx.ID().getText();
        if (!tabla.define(id, tipo, false)) {
            reportarError("Variable duplicada '" + id + "'");
        }
        if (ctx.expr() != null) {
            String tipoExpr = (String) visit(ctx.expr());
            checkTipos(tipo, tipoExpr, id);
        }
        return "void";
    }

    @Override
    public Object visitDeclArray(gramaticaParser.DeclArrayContext ctx) {
        String tipo = ctx.tipo().getText();
        String id = ctx.ID().getText();
        if (!tabla.define(id, tipo + "[]", false)) {
            reportarError("Array duplicado '" + id + "'");
        }
        return "void";
    }

    // --- GESTIÓN DE FUNCIONES ---

    @Override
    public Object visitDeclFuncion(gramaticaParser.DeclFuncionContext ctx) {
        String tipo = ctx.tipo().getText();
        String id = ctx.ID().getText();

        Simbolo f = new Simbolo(id, tipo, true);
        if (ctx.parametros() != null) {
            for (gramaticaParser.ParametroContext pCtx : ctx.parametros().parametro()) {
                f.addParametro(pCtx.tipo().getText());
            }
        }

        if (!tabla.defineSimboloCompleto(f)) {
            reportarError("Nombre de funcion duplicado '" + id + "'");
        }

        tabla.enterScope();
        if (ctx.parametros() != null) {
            visit(ctx.parametros());
        }
        visit(ctx.bloque());
        tabla.exitScope();
        return "void";
    }

    @Override
    public Object visitDeclFuncionVoid(gramaticaParser.DeclFuncionVoidContext ctx) {
        String id = ctx.ID().getText();
        Simbolo f = new Simbolo(id, "void", true);

        if (ctx.parametros() != null) {
            for (gramaticaParser.ParametroContext pCtx : ctx.parametros().parametro()) {
                f.addParametro(pCtx.tipo().getText());
            }
        }

        if (!tabla.defineSimboloCompleto(f)) {
            reportarError("Nombre de funcion duplicado '" + id + "'");
        }

        tabla.enterScope();
        if (ctx.parametros() != null) {
            visit(ctx.parametros());
        }
        visit(ctx.bloque());
        tabla.exitScope();
        return "void";
    }

    @Override
    public Object visitParametro(gramaticaParser.ParametroContext ctx) {
        String tipo = ctx.tipo().getText();
        String id = ctx.ID().getText();
        if (!tabla.define(id, tipo, false)) {
            reportarError("Parametro duplicado '" + id + "'");
        }
        return tipo;
    }

    @Override
    public Object visitBloque(gramaticaParser.BloqueContext ctx) {
        tabla.enterScope();
        visitChildren(ctx);
        tabla.exitScope();
        return "void";
    }

    // --- ESTRUCTURAS DE CONTROL ---

    @Override
    public Object visitIfStat(gramaticaParser.IfStatContext ctx) {
        String tipoCond = (String) visit(ctx.expr());
        if (tipoCond != null && !"bool".equals(tipoCond) && !"error".equals(tipoCond)) {
            reportarError("La condicion del 'if' debe ser bool, se encontro: " + tipoCond);
        }
        return visitChildren(ctx);
    }

    @Override
    public Object visitWhileStat(gramaticaParser.WhileStatContext ctx) {
        String tipoCond = (String) visit(ctx.expr());
        if (tipoCond != null && !"bool".equals(tipoCond) && !"error".equals(tipoCond)) {
            reportarError("La condicion del 'while' debe ser bool, se encontro: " + tipoCond);
        }
        return visitChildren(ctx);
    }

    // --- LLAMADAS A FUNCIONES ---

    @Override
    public Object visitCallExpr(gramaticaParser.CallExprContext ctx) {
        String id = ctx.ID().getText();
        Simbolo s = tabla.resolve(id);

        if (s == null) {
            reportarError("Funcion '" + id + "' no declarada");
            return "error";
        }
        if (!s.esFuncion) {
            reportarError("'" + id + "' no es una funcion");
            return "error";
        }

        List<String> tiposArgumentos = new ArrayList<>();
        if (ctx.argumentos() != null) {
            for (gramaticaParser.ExprContext eCtx : ctx.argumentos().expr()) {
                String tArg = (String) visit(eCtx);
                tiposArgumentos.add(tArg != null ? tArg : "error");
            }
        }

        if (tiposArgumentos.size() != s.tiposParametros.size()) {
            reportarError("La funcion '" + id + "' espera " + s.tiposParametros.size() +
                    " argumentos, pero se recibieron " + tiposArgumentos.size());
        } else {
            for (int i = 0; i < tiposArgumentos.size(); i++) {
                checkTipos(s.tiposParametros.get(i), tiposArgumentos.get(i), "Argumento " + (i + 1) + " de '" + id + "'");
            }
        }
        return s.tipo;
    }

    // --- ASIGNACIONES ---

    @Override
    public Object visitAsignacionSimple(gramaticaParser.AsignacionSimpleContext ctx) {
        String id = ctx.ID().getText();
        Simbolo s = tabla.resolve(id);
        if (s == null) {
            reportarError("Variable '" + id + "' no declarada");
        } else if (s.esFuncion) {
            reportarError("No se puede asignar un valor a la funcion '" + id + "'");
        } else {
            String tipoExpr = (String) visit(ctx.expr());
            checkTipos(s.tipo, tipoExpr, id);
        }
        return "void";
    }

    @Override
    public Object visitAsignacionArray(gramaticaParser.AsignacionArrayContext ctx) {
        String id = ctx.ID().getText();
        Simbolo s = tabla.resolve(id);
        if (s == null) {
            reportarError("Array '" + id + "' no declarado");
        } else {
            String tIdx = (String) visit(ctx.expr(0));
            if (!"int".equals(tIdx) && !"error".equals(tIdx)) reportarError("Indice debe ser int");

            String tipoExpr = (String) visit(ctx.expr(1));
            String tipoBase = s.tipo.replace("[]", "");
            checkTipos(tipoBase, tipoExpr, id);
        }
        return "void";
    }

    // --- EXPRESIONES ---

    @Override
    public Object visitNumberExpr(gramaticaParser.NumberExprContext ctx) { return "int"; }
    @Override
    public Object visitFloatExpr(gramaticaParser.FloatExprContext ctx) { return "float"; }
    @Override
    public Object visitCharExpr(gramaticaParser.CharExprContext ctx) { return "char"; }
    @Override
    public Object visitBoolExpr(gramaticaParser.BoolExprContext ctx) { return "bool"; }

    @Override
    public Object visitIdExpr(gramaticaParser.IdExprContext ctx) {
        String id = ctx.ID().getText();
        Simbolo s = tabla.resolve(id);
        if (s == null) {
            reportarError("Variable '" + id + "' no declarada");
            return "error";
        }
        return s.tipo;
    }

    @Override
    public Object visitMulDivExpr(gramaticaParser.MulDivExprContext ctx) {
        String t1 = (String) visit(ctx.expr(0));
        String t2 = (String) visit(ctx.expr(1));
        return inferirTipo(t1, t2);
    }

    @Override
    public Object visitAddSubExpr(gramaticaParser.AddSubExprContext ctx) {
        String t1 = (String) visit(ctx.expr(0));
        String t2 = (String) visit(ctx.expr(1));
        return inferirTipo(t1, t2);
    }

    @Override
    public Object visitCompExpr(gramaticaParser.CompExprContext ctx) { return "bool"; }
    @Override
    public Object visitParensExpr(gramaticaParser.ParensExprContext ctx) { return visit(ctx.expr()); }

    // --- MÉTODOS AUXILIARES ---

    private void checkTipos(String esperado, String encontrado, String id) {
        if (encontrado == null || encontrado.equals("error") || esperado == null) return;

        if (!esperado.equals(encontrado)) {
            if (esperado.equals("double") && (encontrado.equals("int") || encontrado.equals("float"))) return;
            if (esperado.equals("float") && encontrado.equals("int")) return;
            reportarErrorTipos(id, esperado, encontrado);
        }
    }

    private String inferirTipo(String t1, String t2) {
        if (t1 == null || t2 == null || t1.equals("error") || t2.equals("error")) return "error";
        if (t1.equals("double") || t2.equals("double")) return "double";
        if (t1.equals("float") || t2.equals("float")) return "float";
        if (t1.equals("int") || t2.equals("int")) return "int";
        return t1;
    }
}