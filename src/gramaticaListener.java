// Generated from C:/Users/Octavio/Documents/Repos/FinalTDC/src/gramatica.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link gramaticaParser}.
 */
public interface gramaticaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link gramaticaParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(gramaticaParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link gramaticaParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(gramaticaParser.RContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclVar}
	 * labeled alternative in {@link gramaticaParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclVar(gramaticaParser.DeclVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclVar}
	 * labeled alternative in {@link gramaticaParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclVar(gramaticaParser.DeclVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclArray}
	 * labeled alternative in {@link gramaticaParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclArray(gramaticaParser.DeclArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclArray}
	 * labeled alternative in {@link gramaticaParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclArray(gramaticaParser.DeclArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclFuncion}
	 * labeled alternative in {@link gramaticaParser#funcion}.
	 * @param ctx the parse tree
	 */
	void enterDeclFuncion(gramaticaParser.DeclFuncionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclFuncion}
	 * labeled alternative in {@link gramaticaParser#funcion}.
	 * @param ctx the parse tree
	 */
	void exitDeclFuncion(gramaticaParser.DeclFuncionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclFuncionVoid}
	 * labeled alternative in {@link gramaticaParser#funcion}.
	 * @param ctx the parse tree
	 */
	void enterDeclFuncionVoid(gramaticaParser.DeclFuncionVoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclFuncionVoid}
	 * labeled alternative in {@link gramaticaParser#funcion}.
	 * @param ctx the parse tree
	 */
	void exitDeclFuncionVoid(gramaticaParser.DeclFuncionVoidContext ctx);
	/**
	 * Enter a parse tree produced by {@link gramaticaParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(gramaticaParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link gramaticaParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(gramaticaParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link gramaticaParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(gramaticaParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link gramaticaParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(gramaticaParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link gramaticaParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(gramaticaParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link gramaticaParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(gramaticaParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DeclStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterDeclStat(gramaticaParser.DeclStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DeclStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitDeclStat(gramaticaParser.DeclStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AsignacionStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterAsignacionStat(gramaticaParser.AsignacionStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AsignacionStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitAsignacionStat(gramaticaParser.AsignacionStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(gramaticaParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(gramaticaParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(gramaticaParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(gramaticaParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterForStat(gramaticaParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitForStat(gramaticaParser.ForStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(gramaticaParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(gramaticaParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterExprStat(gramaticaParser.ExprStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitExprStat(gramaticaParser.ExprStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AsignacionSimple}
	 * labeled alternative in {@link gramaticaParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacionSimple(gramaticaParser.AsignacionSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AsignacionSimple}
	 * labeled alternative in {@link gramaticaParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacionSimple(gramaticaParser.AsignacionSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AsignacionArray}
	 * labeled alternative in {@link gramaticaParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacionArray(gramaticaParser.AsignacionArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AsignacionArray}
	 * labeled alternative in {@link gramaticaParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacionArray(gramaticaParser.AsignacionArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(gramaticaParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(gramaticaParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloatExpr(gramaticaParser.FloatExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloatExpr(gramaticaParser.FloatExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(gramaticaParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(gramaticaParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(gramaticaParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(gramaticaParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(gramaticaParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(gramaticaParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpr(gramaticaParser.ArrayAccessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpr(gramaticaParser.ArrayAccessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParensExpr(gramaticaParser.ParensExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParensExpr(gramaticaParser.ParensExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(gramaticaParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(gramaticaParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(gramaticaParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(gramaticaParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(gramaticaParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(gramaticaParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharExpr(gramaticaParser.CharExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharExpr(gramaticaParser.CharExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link gramaticaParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(gramaticaParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link gramaticaParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(gramaticaParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link gramaticaParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(gramaticaParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gramaticaParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(gramaticaParser.TipoContext ctx);
}