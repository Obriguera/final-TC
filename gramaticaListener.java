// Generated from gramatica.g4 by ANTLR 4.13.2
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
	 * Enter a parse tree produced by {@link gramaticaParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(gramaticaParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link gramaticaParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(gramaticaParser.TokenContext ctx);
}