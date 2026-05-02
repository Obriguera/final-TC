// Generated from C:/Users/Octavio/Documents/Repos/FinalTDC/src/gramatica.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link gramaticaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface gramaticaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link gramaticaParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(gramaticaParser.RContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclVar}
	 * labeled alternative in {@link gramaticaParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclVar(gramaticaParser.DeclVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclArray}
	 * labeled alternative in {@link gramaticaParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclArray(gramaticaParser.DeclArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclFuncion}
	 * labeled alternative in {@link gramaticaParser#funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclFuncion(gramaticaParser.DeclFuncionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclFuncionVoid}
	 * labeled alternative in {@link gramaticaParser#funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclFuncionVoid(gramaticaParser.DeclFuncionVoidContext ctx);
	/**
	 * Visit a parse tree produced by {@link gramaticaParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(gramaticaParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link gramaticaParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(gramaticaParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link gramaticaParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(gramaticaParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclStat(gramaticaParser.DeclStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AsignacionStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacionStat(gramaticaParser.AsignacionStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(gramaticaParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(gramaticaParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStat(gramaticaParser.ForStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(gramaticaParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprStat}
	 * labeled alternative in {@link gramaticaParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStat(gramaticaParser.ExprStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AsignacionSimple}
	 * labeled alternative in {@link gramaticaParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacionSimple(gramaticaParser.AsignacionSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AsignacionArray}
	 * labeled alternative in {@link gramaticaParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacionArray(gramaticaParser.AsignacionArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(gramaticaParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatExpr(gramaticaParser.FloatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(gramaticaParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(gramaticaParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(gramaticaParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessExpr(gramaticaParser.ArrayAccessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensExpr(gramaticaParser.ParensExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(gramaticaParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(gramaticaParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(gramaticaParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharExpr}
	 * labeled alternative in {@link gramaticaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharExpr(gramaticaParser.CharExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link gramaticaParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(gramaticaParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link gramaticaParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(gramaticaParser.TipoContext ctx);
}