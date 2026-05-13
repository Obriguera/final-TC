import java.util.*;

class InstruccionTAC {
    String op, arg1, arg2, res;
    InstruccionTAC(String op, String a1, String a2, String r) {
        this.op = op; this.arg1 = a1; this.arg2 = a2; this.res = r;
    }
    @Override
    public String toString() {
        if (op.equals("=")) return res + " = " + arg1;
        if (op.startsWith("L") && res == null) return op + ":";
        if (op.equals("goto")) return "goto " + res;
        if (op.equals("ifFalse")) return "if " + arg1 + " == false goto " + res;
        if (op.equals("call")) return res + " = " + arg1 + " call";
        return res + " = " + arg1 + " " + op + " " + arg2;
    }
}

public class GeneradorTAC extends gramaticaBaseVisitor<String> {
    private List<InstruccionTAC> codigo = new ArrayList<>();
    private int tempCount = 0;
    private int labelCount = 0;

    private String newTemp() { return "t" + (tempCount++); }
    private String newLabel() { return "L" + (labelCount++); }

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
        // Podés visitar los argumentos para que generen sus propios temporales antes del call
        if (ctx.argumentos() != null) {
            visit(ctx.argumentos());
        }
        String t = newTemp();
        // Indicamos que es una llamada a la función
        codigo.add(new InstruccionTAC("call", ctx.ID().getText(), "", t));
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


}