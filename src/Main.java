import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.Paths;
import org.antlr.v4.gui.TreeViewer;
import javax.swing.JFrame;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            String path = args.length > 0 ? args[0] : "input/prueba.txt";
            CharStream input = CharStreams.fromPath(Paths.get(path));

            // 1. Configurar Lexer y su manejo de errores
            gramaticaLexer lexer = new gramaticaLexer(input);
            ManejadorErrores errorListenerLexer = new ManejadorErrores();
            lexer.removeErrorListeners(); // Quitar el reporte por defecto
            lexer.addErrorListener(errorListenerLexer);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // 2. Configurar Parser y su manejo de errores
            gramaticaParser parser = new gramaticaParser(tokens);
            ManejadorErrores errorListenerParser = new ManejadorErrores();
            parser.removeErrorListeners(); // Quitar el reporte por defecto
            parser.addErrorListener(errorListenerParser);

            ParseTree tree = parser.r();

            // --- Visualización del Árbol Sintáctico ---
            // Crear una ventana (JFrame) para mostrar el árbol
            // --- Visualización del Árbol Sintáctico ---
            JFrame frame = new JFrame("Visualizador de Árbol Sintáctico - Octavio Briguera");
            TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);

// 1. Aumentamos la escala para que el texto sea legible
            viewer.setScale(1.2);

// 2. Metemos el viewer adentro de un JScrollPane para poder navegarlo
            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(viewer);
            scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            frame.add(scrollPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// 3. Definimos un tamaño de ventana inicial cómodo
            frame.setSize(1200, 800);
            frame.setVisible(true);
            // ------------------------------------------

            // 3. Validación antes de seguir al análisis semántico
            if (!errorListenerLexer.tieneErrores() && !errorListenerParser.tieneErrores()) {
                System.out.println("Análisis léxico y sintáctico completado con éxito.");

                Semantico as = new Semantico();
                as.visit(tree);

                // En el método main, después del análisis semántico:
                if (as.getErrores() == 0) {
                    System.out.println("Análisis semántico exitoso. Generando TAC...");
                    GeneradorTAC tac = new GeneradorTAC();
                    tac.visit(tree);
                    System.out.println("\n--- CÓDIGO INTERMEDIO ---");
                    tac.imprimirCodigo();
                }
            } else {
                int totales = errorListenerLexer.getContadorErrores() + errorListenerParser.getContadorErrores();
                System.err.println("Análisis abortado: Se encontraron " + totales + " errores léxicos/sintácticos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}