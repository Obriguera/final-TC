import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Leer archivo de entrada
            String path = args.length > 0 ? args[0] : "input/prueba.txt";
            CharStream input = CharStreams.fromPath(Paths.get(path));

            // 2. Lexer: Análisis Léxico
            gramaticaLexer lexer = new gramaticaLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // 3. Parser: Análisis Sintáctico
            gramaticaParser parser = new gramaticaParser(tokens);
            ParseTree tree = parser.r(); // 'r' es tu regla inicial

            // 4. Verificar errores sintácticos antes de seguir
            if (parser.getNumberOfSyntaxErrors() == 0) {
                System.out.println("Análisis sintáctico completado con éxito.");

                // 5. Visitor: Análisis Semántico
                Semantico as = new Semantico();
                as.visit(tree);

                if (as.getErrores() == 0) {
                    System.out.println("Análisis semántico completado con éxito. El código es válido.");
                } else {
                    System.err.println("Se encontraron " + as.getErrores() + " errores semánticos.");
                }
            } else {
                System.err.println("Se encontraron errores sintácticos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}