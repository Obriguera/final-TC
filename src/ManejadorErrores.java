import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ManejadorErrores extends BaseErrorListener {
    private int contadorErrores = 0;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        contadorErrores++;
        System.err.println("Error detectado:");
        System.err.println("  - Ubicación: Línea " + line + ", Columna " + charPositionInLine);
        System.err.println("  - Mensaje: " + msg);
        System.err.println("--------------------------------------------------");
    }

    public boolean tieneErrores() {
        return contadorErrores > 0;
    }

    public int getContadorErrores() {
        return contadorErrores;
    }
}