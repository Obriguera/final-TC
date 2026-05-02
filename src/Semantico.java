public class Semantico extends gramaticaBaseVisitor<Object> {
    private TablaSimbolos tabla = new TablaSimbolos();
    private int errores = 0;

    public int getErrores() {
        return errores;
    }

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
        return null;
    }

    @Override
    public Object visitDeclArray(gramaticaParser.DeclArrayContext ctx) {
        String tipo = ctx.tipo().getText();
        String id = ctx.ID().getText();
        if (!tabla.define(id, tipo + "[]", false)) {
            reportarError("Array duplicado '" + id + "'");
        }
        return null;
    }

    // --- GESTIÓN DE FUNCIONES ---

    @Override
    public Object visitDeclFuncion(gramaticaParser.DeclFuncionContext ctx) {
        String tipo = ctx.tipo().getText();
        String id = ctx.ID().getText();

        // Registrar la función en el ámbito actual (global)
        if (!tabla.define(id, tipo, true)) {
            reportarError("Nombre de funcion duplicado '" + id + "'");
        }

        tabla.enterScope(); // Entrar al ámbito de la función (para parámetros)
        if (ctx.parametros() != null) {
            visit(ctx.parametros());
        }
        visit(ctx.bloque()); // El bloque creará su propio sub-ámbito si así lo definimos en visitBloque
        tabla.exitScope();
        return null;
    }

    @Override
    public Object visitDeclFuncionVoid(gramaticaParser.DeclFuncionVoidContext ctx) {
        String id = ctx.ID().getText();
        if (!tabla.define(id, "void", true)) {
            reportarError("Nombre de funcion duplicado '" + id + "'");
        }

        tabla.enterScope();
        if (ctx.parametros() != null) {
            visit(ctx.parametros());
        }
        visit(ctx.bloque());
        tabla.exitScope();
        return null;
    }

    @Override
    public Object visitParametro(gramaticaParser.ParametroContext ctx) {
        String tipo = ctx.tipo().getText();
        String id = ctx.ID().getText();
        if (!tabla.define(id, tipo, false)) {
            reportarError("Parametro duplicado '" + id + "'");
        }
        return null;
    }

    // --- GESTIÓN DE ÁMBITOS EN ESTRUCTURAS ---

    @Override
    public Object visitBloque(gramaticaParser.BloqueContext ctx) {
        tabla.enterScope();
        Object res = visitChildren(ctx);
        tabla.exitScope();
        return res;
    }

    @Override
    public Object visitIfStat(gramaticaParser.IfStatContext ctx) {
        String tipoCond = (String) visit(ctx.expr());
        if (!"bool".equals(tipoCond)) {
            reportarError("La condicion del 'if' debe ser bool, se encontro: " + tipoCond);
        }
        // visitBloque ya maneja los scopes de los bloques { ... }
        return visitChildren(ctx);
    }

    @Override
    public Object visitWhileStat(gramaticaParser.WhileStatContext ctx) {
        String tipoCond = (String) visit(ctx.expr());
        if (!"bool".equals(tipoCond)) {
            reportarError("La condicion del 'while' debe ser bool, se encontro: " + tipoCond);
        }
        return visitChildren(ctx);
    }

    @Override
    public Object visitForStat(gramaticaParser.ForStatContext ctx) {
        // En esta gramática, for usa asignaciones existentes, no declaraciones.
        return visitChildren(ctx);
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
        return null;
    }

    @Override
    public Object visitAsignacionArray(gramaticaParser.AsignacionArrayContext ctx) {
        String id = ctx.ID().getText();
        Simbolo s = tabla.resolve(id);
        if (s == null) {
            reportarError("Array '" + id + "' no declarado");
        } else {
            String tipoIndice = (String) visit(ctx.expr(0));
            if (!"int".equals(tipoIndice)) {
                reportarError("El indice del array debe ser int, se encontro: " + tipoIndice);
            }
            String tipoExpr = (String) visit(ctx.expr(1));
            String tipoBase = s.tipo.replace("[]", "");
            checkTipos(tipoBase, tipoExpr, id);
        }
        return null;
    }

    // --- EXPRESIONES (CHEQUEO DE TIPOS) ---

    @Override
    public Object visitNumberExpr(gramaticaParser.NumberExprContext ctx) {
        return "int";
    }

    @Override
    public Object visitFloatExpr(gramaticaParser.FloatExprContext ctx) {
        return "float";
    }

    @Override
    public Object visitCharExpr(gramaticaParser.CharExprContext ctx) {
        return "char";
    }

    @Override
    public Object visitBoolExpr(gramaticaParser.BoolExprContext ctx) {
        return "bool";
    }

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
    public Object visitArrayAccessExpr(gramaticaParser.ArrayAccessExprContext ctx) {
        String id = ctx.ID().getText();
        Simbolo s = tabla.resolve(id);
        if (s == null) {
            reportarError("Array '" + id + "' no declarado");
            return "error";
        }
        String tipoIndice = (String) visit(ctx.expr());
        if (!"int".equals(tipoIndice)) {
            reportarError("El indice del array debe ser int");
        }
        return s.tipo.replace("[]", "");
    }

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
    public Object visitCompExpr(gramaticaParser.CompExprContext ctx) {
        return "bool";
    }

    @Override
    public Object visitParensExpr(gramaticaParser.ParensExprContext ctx) {
        return visit(ctx.expr());
    }

    // --- MÉTODOS AUXILIARES ---

    private void checkTipos(String esperado, String encontrado, String id) {
        if (encontrado.equals("error")) return;
        if (!esperado.equals(encontrado)) {
            // Permitir promocion simple int -> double/float y float -> double
            if (esperado.equals("double") && encontrado.equals("int")) return;
            if (esperado.equals("float") && encontrado.equals("int")) return;
            if (esperado.equals("double") && encontrado.equals("float")) return;

            reportarErrorTipos(id, esperado, encontrado);
        }
    }

    private String inferirTipo(String t1, String t2) {
        if (t1.equals("error") || t2.equals("error")) return "error";
        if (t1.equals("double") || t2.equals("double")) return "double";
        if (t1.equals("float") || t2.equals("float")) return "float";
        if (t1.equals("int") || t2.equals("int")) return "int";
        return t1;
    }
}