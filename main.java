import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

public class main {

	public static void main(String[] args) {
		try {
			String inputText = readInput(args);

			gramaticaLexer lexer = new gramaticaLexer(CharStreams.fromString(inputText));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			tokens.fill();

			printTokens(tokens, lexer);

			if (contains(args, "--parse")) {
				runParser(tokens);
			}
		} catch (Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.exit(1);
		}
	}

	private static String readInput(String[] args) throws IOException {
		if (args.length == 0 || "--parse".equals(args[0])) {
			CharStream stdin = CharStreams.fromStream(System.in);
			return stdin.toString();
		}

		Path inputPath = Path.of(args[0]);
		return Files.readString(inputPath);
	}

	private static void printTokens(CommonTokenStream tokens, gramaticaLexer lexer) {
		boolean hasLexicalErrors = false;

		System.out.printf("%-6s %-18s %-20s %-8s%n", "LINEA", "TIPO", "LEXEMA", "COLUMNA");
		System.out.println("-------------------------------------------------------------");

		for (Token token : tokens.getTokens()) {
			if (token.getType() == Token.EOF) {
				continue;
			}

			String tokenName = lexer.getVocabulary().getSymbolicName(token.getType());
			String lexeme = escapeLexeme(token.getText());

			System.out.printf(
				"%-6d %-18s %-20s %-8d%n",
				token.getLine(),
				tokenName,
				lexeme,
				token.getCharPositionInLine()
			);

			if (token.getType() == gramaticaLexer.ERROR_CHAR) {
				hasLexicalErrors = true;
			}
		}

		if (hasLexicalErrors) {
			System.out.println("\nSe detectaron errores lexicos (token ERROR_CHAR).");
		} else {
			System.out.println("\nAnalisis lexico finalizado sin errores.");
		}
	}

	private static void runParser(CommonTokenStream tokens) {
		tokens.seek(0);
		gramaticaParser parser = new gramaticaParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new org.antlr.v4.runtime.BaseErrorListener() {
			@Override
			public void syntaxError(
				org.antlr.v4.runtime.Recognizer<?, ?> recognizer,
				Object offendingSymbol,
				int line,
				int charPositionInLine,
				String msg,
				org.antlr.v4.runtime.RecognitionException e
			) {
				System.err.printf("Error sintactico en linea %d:%d - %s%n", line, charPositionInLine, msg);
			}
		});

		ParseTree tree = parser.r();
		if (parser.getNumberOfSyntaxErrors() == 0) {
			System.out.println("Analisis sintactico OK.");
			System.out.println(tree.toStringTree(parser));
		} else {
			System.out.printf("Analisis sintactico con %d error(es).%n", parser.getNumberOfSyntaxErrors());
		}
	}

	private static boolean contains(String[] args, String value) {
		for (String arg : args) {
			if (value.equals(arg)) {
				return true;
			}
		}
		return false;
	}

	private static String escapeLexeme(String text) {
		return text
			.replace("\\", "\\\\")
			.replace("\n", "\\n")
			.replace("\r", "\\r")
			.replace("\t", "\\t");
	}
}
