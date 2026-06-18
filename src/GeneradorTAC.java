import java.util.*;

class InstruccionTAC {
    String op, arg1, arg2, res;
    InstruccionTAC(String op, String a1, String a2, String r) {
        this.op = op; this.arg1 = a1; this.arg2 = a2; this.res = r;
    }

    @Override
    public String toString() {
        // Etiqueta (funciones o Lxx)
        if (res == null && arg1 == null && arg2 == null) return op + ":";

        if (op.equals("=")) return res + " = " + arg1;
        if (op.equals("goto")) return "goto " + res;
        if (op.equals("ifFalse")) return "if " + arg1 + " == false goto " + res;

        // Nuevas operaciones
        if (op.equals("param")) return "param " + arg1;
        if (op.equals("call")) return res + " = call " + arg1 + ", " + arg2;
        if (op.equals("return")) return arg1.isEmpty() ? "return" : "return " + arg1;

        if (op.equals("[]=")) return res + "[" + arg2 + "] = " + arg1;

        // Operaciones aritméticas y lógicas generales
        return res + " = " + arg1 + " " + op + " " + arg2;
    }
}

public class GeneradorTAC extends gramaticaBaseVisitor<String> {
    private List<InstruccionTAC> codigo = new ArrayList<>();
    private int tempCount = 0;
    private int labelCount = 0;

    private String newTemp() { return "t" + (tempCount++); }
    private String newLabel() { return "L" + (labelCount++); }

    public List<InstruccionTAC> getCodigo() {
        return this.codigo;
    }

    public void imprimirCodigo() {
        System.out.println("\n--- CÓDIGO INTERMEDIO ---");
        for (InstruccionTAC i : codigo) System.out.println(i);
    }

    @Override
    public String visitAsignacionSimple(gramaticaParser.AsignacionSimpleContext ctx) {
        String val = visit(ctx.expr());
        codigo.add(new InstruccionTAC("=", val, "", ctx.ID().getText()));
        return ctx.ID().getText();
    }

    @Override
    public String visitAddSubExpr(gramaticaParser.AddSubExprContext ctx) {
        String l = visit(ctx.expr(0));
        String r = visit(ctx.expr(1));
        String t = newTemp();
        codigo.add(new InstruccionTAC(ctx.op.getText(), l, r, t));
        return t;
    }

    @Override
    public String visitMulDivExpr(gramaticaParser.MulDivExprContext ctx) {
        String l = visit(ctx.expr(0));
        String r = visit(ctx.expr(1));
        String t = newTemp();
        codigo.add(new InstruccionTAC(ctx.op.getText(), l, r, t));
        return t;
    }

    @Override
    public String visitIfStat(gramaticaParser.IfStatContext ctx) {
        String cond = visit(ctx.expr()); // Esto ahora devolverá tX gracias al paso anterior
        String labelFalse = newLabel();
        String labelEnd = newLabel();

        // 1. Si la condición es falsa, saltamos al ELSE (o al final)
        codigo.add(new InstruccionTAC("ifFalse", cond, "", labelFalse));

        // 2. Visitamos el bloque TRUE (lo que está adentro del if)
        visit(ctx.bloque(0));

        // 3. Salto incondicional al final para no ejecutar el else
        codigo.add(new InstruccionTAC("goto", "", "", labelEnd));

        // 4. Etiqueta para el camino falso
        codigo.add(new InstruccionTAC(labelFalse, null, null, null));
        if (ctx.ELSE() != null) {
            visit(ctx.bloque(1));
        }

        // 5. Etiqueta final
        codigo.add(new InstruccionTAC(labelEnd, null, null, null));

        return null;
    }

    @Override public String visitNumberExpr(gramaticaParser.NumberExprContext ctx) { return ctx.NUMBER().getText(); }
    @Override public String visitFloatExpr(gramaticaParser.FloatExprContext ctx) { return ctx.FLOAT_NUMBER().getText(); }
    @Override public String visitCharExpr(gramaticaParser.CharExprContext ctx) { return ctx.CHAR_LITERAL().getText(); }
    @Override public String visitIdExpr(gramaticaParser.IdExprContext ctx) { return ctx.ID().getText(); }
    @Override public String visitBoolExpr(gramaticaParser.BoolExprContext ctx) { return ctx.getText(); }
    @Override public String visitParensExpr(gramaticaParser.ParensExprContext ctx) { return visit(ctx.expr()); }

    @Override
    public String visitArrayAccessExpr(gramaticaParser.ArrayAccessExprContext ctx) {
        String idx = visit(ctx.expr());
        String t = newTemp();
        codigo.add(new InstruccionTAC("[]", ctx.ID().getText(), idx, t));
        return t;
    }

    @Override
    public String visitCallExpr(gramaticaParser.CallExprContext ctx) {
        int cantidadArgumentos = 0;

        // 1. Procesar argumentos si existen
        if (ctx.argumentos() != null) {
            List<String> temporalesArgumentos = new ArrayList<>();

            // Primero evaluamos todas las expresiones para obtener sus temporales
            for (gramaticaParser.ExprContext exprCtx : ctx.argumentos().expr()) {
                temporalesArgumentos.add(visit(exprCtx));
                cantidadArgumentos++;
            }

            // Luego generamos la instrucción 'param' por cada argumento
            for (String argTemp : temporalesArgumentos) {
                codigo.add(new InstruccionTAC("param", argTemp, "", ""));
            }
        }

        // 2. Generar el temporal para el resultado de la función
        String t = newTemp();
        String nombreFuncion = ctx.ID().getText();

        // 3. Emitir el call (Ej: t0 = call suma, 2)
        codigo.add(new InstruccionTAC("call", nombreFuncion, String.valueOf(cantidadArgumentos), t));

        return t;
    }

    @Override
    public String visitCompExpr(gramaticaParser.CompExprContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        String temp = newTemp();
        codigo.add(new InstruccionTAC(ctx.op.getText(), left, right, temp));
        return temp;
    }

    ///

    @Override
    public String visitWhileStat(gramaticaParser.WhileStatContext ctx) {
        String labelStart = newLabel();
        String labelEnd = newLabel();

        // 1. Etiqueta de inicio del loop
        codigo.add(new InstruccionTAC(labelStart, null, null, null));

        // 2. Evaluar condición
        String cond = visit(ctx.expr());
        codigo.add(new InstruccionTAC("ifFalse", cond, "", labelEnd));

        // 3. Cuerpo del while
        visit(ctx.bloque());

        // 4. Salto incondicional al inicio y etiqueta de salida
        codigo.add(new InstruccionTAC("goto", "", "", labelStart));
        codigo.add(new InstruccionTAC(labelEnd, null, null, null));

        return null;
    }

    @Override
    public String visitForStat(gramaticaParser.ForStatContext ctx) {
        String labelStart = newLabel();
        String labelEnd = newLabel();

        // 1. Inicialización
        visit(ctx.asignacion(0));

        // 2. Inicio del ciclo
        codigo.add(new InstruccionTAC(labelStart, null, null, null));

        // 3. Condición
        String cond = visit(ctx.expr());
        codigo.add(new InstruccionTAC("ifFalse", cond, "", labelEnd));

        // 4. Cuerpo y actualización
        visit(ctx.bloque());
        visit(ctx.asignacion(1));

        // 5. Reinicio
        codigo.add(new InstruccionTAC("goto", "", "", labelStart));
        codigo.add(new InstruccionTAC(labelEnd, null, null, null));

        return null;
    }

    @Override
    public String visitDeclFuncion(gramaticaParser.DeclFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();

        // 1. Generamos la etiqueta con el nombre de la función
        codigo.add(new InstruccionTAC(nombreFuncion, null, null, null));

        // 2. Visitamos el cuerpo de la función
        visit(ctx.bloque());

        return null;
    }

    @Override
    public String visitDeclFuncionVoid(gramaticaParser.DeclFuncionVoidContext ctx) {
        String nombreFuncion = ctx.ID().getText();

        // 1. Etiqueta de la función
        codigo.add(new InstruccionTAC(nombreFuncion, null, null, null));

        // 2. Visitamos el cuerpo
        visit(ctx.bloque());

        // 3. Si es void, aseguramos un return implícito al final del bloque TAC
        codigo.add(new InstruccionTAC("return", "", "", ""));

        return null;
    }

    @Override
    public String visitReturnStat(gramaticaParser.ReturnStatContext ctx) {
        if (ctx.expr() != null) {
            // Evaluamos la expresión a retornar (devuelve un tX)
            String valor = visit(ctx.expr());
            codigo.add(new InstruccionTAC("return", valor, "", ""));
        } else {
            // Return vacío (para funciones void)
            codigo.add(new InstruccionTAC("return", "", "", ""));
        }
        return null;
    }

    @Override
    public String visitAsignacionArray(gramaticaParser.AsignacionArrayContext ctx) {
        String idx = visit(ctx.expr(0)); // Posición/Índice
        String val = visit(ctx.expr(1)); // Expresión/Valor a guardar
        String id = ctx.ID().getText();  // Nombre del arreglo

        // Estructura: op="[]=", arg1=valor, arg2=índice, res=identificador_arreglo
        codigo.add(new InstruccionTAC("[]=", val, idx, id));
        return id;
    }
}