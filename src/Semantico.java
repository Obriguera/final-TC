import org.antlr.v4.runtime.Token;
import java.util.ArrayList;
import java.util.List;

public class Semantico extends gramaticaBaseVisitor<Object> {
    private TablaSimbolos tabla = new TablaSimbolos();
    private int errores = 0;
    private String funcionActualTipo = null;

    public int getErrores() { return errores; }

    private void reportarError(Token t, String mensaje) {
        System.err.println("Error Semántico [" + t.getLine() + ":" + t.getCharPositionInLine() + "]: " + mensaje);
        errores++;
    }

    /**
     * Valida si un bloque garantiza un retorno (útil para funciones no void).
     */
    private boolean garantizaReturn(gramaticaParser.BloqueContext ctx) {
        if (ctx == null) return false;
        for (gramaticaParser.SentenciaContext s : ctx.sentencia()) {
            if (s instanceof gramaticaParser.ReturnStatContext) {
                return true;
            }
            if (s instanceof gramaticaParser.IfStatContext) {
                gramaticaParser.IfStatContext ifCtx = (gramaticaParser.IfStatContext) s;
                if (ifCtx.ELSE() != null) {
                    if (garantizaReturn(ifCtx.bloque(0)) && garantizaReturn(ifCtx.bloque(1))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void checkTipos(String esperado, String encontrado, Token t) {
        if (encontrado == null || encontrado.equals("error") || esperado == null) {
            return;
        }
        if (!esperado.equals(encontrado)) {
            // Permitir promociones automáticas (int -> float/double y float -> double)
            if ((esperado.equals("double") || esperado.equals("float")) && encontrado.equals("int")) {
                return;
            }
            if (esperado.equals("double") && encontrado.equals("float")) {
                return;
            }
            reportarError(t, "Tipos incompatibles. Se esperaba '" + esperado + "' pero se encontró '" + encontrado + "'.");
        }
    }

    private String resultType(String t1, String t2) {
        if (t1 == null || t2 == null || t1.equals("error") || t2.equals("error")) return "error";
        if (t1.equals("double") || t2.equals("double")) return "double";
        if (t1.equals("float") || t2.equals("float")) return "float";
        if (t1.equals("int") && t2.equals("int")) return "int";
        return "error"; 
    }

    // ==========================================
    // DECLARACIONES
    // ==========================================

    @Override
    public Object visitDeclVar(gramaticaParser.DeclVarContext ctx) {
        String tipo = ctx.tipo().getText();
        Token idToken = ctx.ID().getSymbol();

        if (!tabla.define(idToken.getText(), tipo, false, idToken.getLine())) {
            reportarError(idToken, "Identificador '" + idToken.getText() + "' ya declarado.");
        }

        if (ctx.expr() != null) {
            String tipoExpr = (String) visit(ctx.expr());
            checkTipos(tipo, tipoExpr, idToken);
        }
        return "void";
    }

    @Override
    public Object visitDeclArray(gramaticaParser.DeclArrayContext ctx) {
        String tipo = ctx.tipo().getText() + "[]";
        Token idToken = ctx.ID().getSymbol();

        if (!tabla.define(idToken.getText(), tipo, false, idToken.getLine())) {
            reportarError(idToken, "Identificador '" + idToken.getText() + "' ya declarado.");
        }
        return "void";
    }

    @Override
    public Object visitDeclFuncion(gramaticaParser.DeclFuncionContext ctx) {
        String tipo = ctx.tipo().getText();
        Token idToken = ctx.ID().getSymbol();

        Simbolo f = new Simbolo(idToken.getText(), tipo, true, idToken.getLine());
        if (ctx.parametros() != null) {
            for (var p : ctx.parametros().parametro()) f.addParametro(p.tipo().getText());
        }

        if (!tabla.defineSimboloCompleto(f)) {
            reportarError(idToken, "La función '" + idToken.getText() + "' ya existe.");
        }

        funcionActualTipo = tipo;
        tabla.enterScope();
        if (ctx.parametros() != null) visit(ctx.parametros());
        visit(ctx.bloque());

        if (!tipo.equals("void") && !garantizaReturn(ctx.bloque())) {
            reportarError(idToken, "La función debe garantizar un retorno de tipo " + tipo);
        }

        tabla.exitScope(); 
        funcionActualTipo = null;
        return "void";
    }

    @Override
    public Object visitDeclFuncionVoid(gramaticaParser.DeclFuncionVoidContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        String tipo = "void";

        Simbolo f = new Simbolo(idToken.getText(), tipo, true, idToken.getLine());
        if (ctx.parametros() != null) {
            for (var p : ctx.parametros().parametro()) f.addParametro(p.tipo().getText());
        }

        if (!tabla.defineSimboloCompleto(f)) {
            reportarError(idToken, "La función '" + idToken.getText() + "' ya existe.");
        }

        funcionActualTipo = tipo;
        tabla.enterScope();
        if (ctx.parametros() != null) visit(ctx.parametros());
        visit(ctx.bloque());
        tabla.exitScope();
        funcionActualTipo = null;
        return "void";
    }

    @Override
    public Object visitParametro(gramaticaParser.ParametroContext ctx) {
        String tipo = ctx.tipo().getText();
        Token idToken = ctx.ID().getSymbol();
        if (!tabla.define(idToken.getText(), tipo, false, idToken.getLine())) {
            reportarError(idToken, "Parámetro '" + idToken.getText() + "' ya declarado.");
        }
        return tipo;
    }

    // ==========================================
    // SENTENCIAS Y CONTROL DE FLUJO
    // ==========================================

    @Override
    public Object visitAsignacionSimple(gramaticaParser.AsignacionSimpleContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        Simbolo s = tabla.resolve(idToken.getText());
        if (s == null) {
            reportarError(idToken, "La variable '" + idToken.getText() + "' no ha sido declarada.");
            return "error";
        }
        String tipoExpr = (String) visit(ctx.expr());
        checkTipos(s.tipo, tipoExpr, idToken);
        return s.tipo;
    }

    @Override
    public Object visitAsignacionArray(gramaticaParser.AsignacionArrayContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        Simbolo s = tabla.resolve(idToken.getText());
        if (s == null) {
            reportarError(idToken, "El arreglo '" + idToken.getText() + "' no ha sido declarado.");
            return "error";
        }
        
        String tipoIndex = (String) visit(ctx.expr(0));
        if (!"int".equals(tipoIndex) && !"error".equals(tipoIndex)) {
            reportarError(idToken, "El índice del arreglo debe ser entero.");
        }

        if (!s.tipo.endsWith("[]")) {
            reportarError(idToken, "El identificador '" + idToken.getText() + "' no es un arreglo.");
        } else {
            String tipoBase = s.tipo.replace("[]", "");
            String tipoExpr = (String) visit(ctx.expr(1));
            checkTipos(tipoBase, tipoExpr, idToken);
        }

        return s.tipo.replace("[]", "");
    }

    @Override
    public Object visitReturnStat(gramaticaParser.ReturnStatContext ctx) {
        if (funcionActualTipo == null) {
            reportarError(ctx.RETURN().getSymbol(), "Instrucción 'return' fuera de una función.");
            return "error";
        }
        
        if (ctx.expr() != null) {
            String tipoRetorno = (String) visit(ctx.expr());
            if (funcionActualTipo.equals("void")) {
                reportarError(ctx.RETURN().getSymbol(), "Una función 'void' no debe retornar un valor.");
            } else {
                checkTipos(funcionActualTipo, tipoRetorno, ctx.RETURN().getSymbol());
            }
        } else {
            if (!funcionActualTipo.equals("void")) {
                reportarError(ctx.RETURN().getSymbol(), "La función espera un retorno de tipo '" + funcionActualTipo + "'.");
            }
        }
        return null;
    }

    @Override
    public Object visitIfStat(gramaticaParser.IfStatContext ctx) {
        String condicion = (String) visit(ctx.expr());
        if (!"bool".equals(condicion) && !"error".equals(condicion)) {
            reportarError(ctx.IF().getSymbol(), "La condición del 'if' debe ser booleana.");
        }
        visit(ctx.bloque(0));
        if (ctx.ELSE() != null) {
            visit(ctx.bloque(1));
        }
        return null;
    }

    @Override
    public Object visitWhileStat(gramaticaParser.WhileStatContext ctx) {
        String condicion = (String) visit(ctx.expr());
        if (!"bool".equals(condicion) && !"error".equals(condicion)) {
            reportarError(ctx.WHILE().getSymbol(), "La condición del 'while' debe ser booleana.");
        }
        visit(ctx.bloque());
        return null;
    }

    @Override
    public Object visitForStat(gramaticaParser.ForStatContext ctx) {
        visit(ctx.asignacion(0));
        String condicion = (String) visit(ctx.expr());
        if (!"bool".equals(condicion) && !"error".equals(condicion)) {
            reportarError(ctx.start, "La condición del 'for' debe ser booleana.");
        }
        visit(ctx.asignacion(1));
        visit(ctx.bloque());
        return null;
    }

    @Override
    public Object visitExprStat(gramaticaParser.ExprStatContext ctx) {
        visit(ctx.expr());
        return null;
    }

    // ==========================================
    // EXPRESIONES Y OPERADORES
    // ==========================================

    @Override
    public Object visitMulDivExpr(gramaticaParser.MulDivExprContext ctx) {
        String t1 = (String) visit(ctx.expr(0));
        String t2 = (String) visit(ctx.expr(1));
        
        if (ctx.op.getText().equals("%")) {
            if (!"int".equals(t1) || !"int".equals(t2)) {
                if (!"error".equals(t1) && !"error".equals(t2)) {
                    reportarError(ctx.op, "La operación módulo '%' requiere operandos enteros.");
                }
                return "error";
            }
            return "int";
        }
        
        String res = resultType(t1, t2);
        if (res.equals("error") && !t1.equals("error") && !t2.equals("error")) {
            reportarError(ctx.op, "Operación aritmética no válida entre '" + t1 + "' y '" + t2 + "'.");
        }
        return res;
    }

    @Override
    public Object visitAddSubExpr(gramaticaParser.AddSubExprContext ctx) {
        String t1 = (String) visit(ctx.expr(0));
        String t2 = (String) visit(ctx.expr(1));
        String res = resultType(t1, t2);
        if (res.equals("error") && !t1.equals("error") && !t2.equals("error")) {
            reportarError(ctx.op, "Operación aritmética no válida entre '" + t1 + "' y '" + t2 + "'.");
        }
        return res;
    }

    @Override
    public Object visitCompExpr(gramaticaParser.CompExprContext ctx) {
        String t1 = (String) visit(ctx.expr(0));
        String t2 = (String) visit(ctx.expr(1));
        
        if ((t1.equals("int") || t1.equals("float") || t1.equals("double")) &&
            (t2.equals("int") || t2.equals("float") || t2.equals("double"))) {
            return "bool";
        }
        if (t1.equals(t2)) {
            return "bool"; // p.ej. bool == bool, char == char
        }
        if (!t1.equals("error") && !t2.equals("error")) {
            reportarError(ctx.op, "Tipos incompatibles en comparación: '" + t1 + "' y '" + t2 + "'.");
        }
        return "bool";
    }

    @Override
    public Object visitCallExpr(gramaticaParser.CallExprContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        Simbolo s = tabla.resolve(idToken.getText());
        if (s == null) {
            reportarError(idToken, "La función '" + idToken.getText() + "' no está declarada.");
            return "error";
        }
        if (!s.esFuncion) {
            reportarError(idToken, "'" + idToken.getText() + "' no es una función.");
            return "error";
        }

        int expectedArgs = s.tiposParametros.size();
        int actualArgs = ctx.argumentos() != null ? ctx.argumentos().expr().size() : 0;
        
        if (expectedArgs != actualArgs) {
            reportarError(idToken, "La función '" + idToken.getText() + "' espera " + expectedArgs + " argumentos, pero se recibieron " + actualArgs + ".");
        } else if (actualArgs > 0) {
            for (int i = 0; i < actualArgs; i++) {
                String tipoArg = (String) visit(ctx.argumentos().expr(i));
                checkTipos(s.tiposParametros.get(i), tipoArg, ctx.argumentos().expr(i).start);
            }
        }
        return s.tipo;
    }

    @Override
    public Object visitIdExpr(gramaticaParser.IdExprContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        Simbolo s = tabla.resolve(idToken.getText());
        if (s == null) {
            reportarError(idToken, "La variable '" + idToken.getText() + "' no ha sido declarada.");
            return "error";
        }
        return s.tipo;
    }

    @Override
    public Object visitArrayAccessExpr(gramaticaParser.ArrayAccessExprContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        Simbolo s = tabla.resolve(idToken.getText());
        if (s == null) {
            reportarError(idToken, "El arreglo '" + idToken.getText() + "' no ha sido declarado.");
            return "error";
        }

        String tipoIndex = (String) visit(ctx.expr());
        if (!"int".equals(tipoIndex) && !"error".equals(tipoIndex)) {
            reportarError(idToken, "El índice del arreglo debe ser entero.");
        }

        if (!s.tipo.endsWith("[]")) {
            reportarError(idToken, "El identificador '" + idToken.getText() + "' no es un arreglo.");
            return "error";
        }

        return s.tipo.replace("[]", "");
    }

    @Override
    public Object visitParensExpr(gramaticaParser.ParensExprContext ctx) {
        return visit(ctx.expr());
    }

    // ==========================================
    // LITERALES
    // ==========================================

    @Override public Object visitNumberExpr(gramaticaParser.NumberExprContext ctx) { return "int"; }
    @Override public Object visitFloatExpr(gramaticaParser.FloatExprContext ctx) { 
        String text = ctx.FLOAT_NUMBER().getText();
        if (text.endsWith("f") || text.endsWith("F")) {
            return "float";
        }
        // Asumiendo que por defecto los literales como 3.14 son de tipo float según la regla que falla 
        // y como se comportan en este analizador, pero vamos a dejarlo que los FLOAT_NUMBER genéricos son de tipo double en general para C, 
        // O lo inferimos por el contexto. En C/Java `3.14` es un double. `3.14f` es float.
        // Dado el error "Se esperaba 'double' pero se encontró 'float'" en `valorPi = 3.14;`, parece que el lexer o parser lo devuelve y nosotros decíamos float.
        return "double"; 
    }
    @Override public Object visitBoolExpr(gramaticaParser.BoolExprContext ctx) { return "bool"; }
    @Override public Object visitCharExpr(gramaticaParser.CharExprContext ctx) { return "char"; }
}