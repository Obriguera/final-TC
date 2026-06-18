import java.util.*;

class Simbolo {
    String nombre, tipo;
    boolean esFuncion, usado = false;
    List<String> tiposParametros;
    int linea; // Guardamos la línea para que el Warning sea preciso

    Simbolo(String n, String t, boolean f, int linea) {
        this.nombre = n;
        this.tipo = t;
        this.esFuncion = f;
        this.linea = linea;
        this.tiposParametros = new ArrayList<>();
    }

    void addParametro(String tipo) {
        this.tiposParametros.add(tipo);
    }
}

public class TablaSimbolos {

    private Stack<Map<String, Simbolo>> scopes = new Stack<>();

    public TablaSimbolos() {
        enterScope(); // Crear ámbito global
    }

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        if (scopes.size() > 1) {
            // Antes de destruir el ámbito, avisamos sobre variables inútiles
            for (Simbolo s : scopes.peek().values()) {
                if (!s.esFuncion && !s.usado) {
                    System.out.println("[WARNING] Línea " + s.linea + ": La variable '" + s.nombre + "' fue declarada pero nunca se usó.");
                }
            }
            scopes.pop();
        }
    }

    public boolean define(String nombre, String tipo, boolean esFunc, int linea) {
        if (scopes.peek().containsKey(nombre)) return false;
        scopes.peek().put(nombre, new Simbolo(nombre, tipo, esFunc, linea));
        return true;
    }

    public boolean defineSimboloCompleto(Simbolo s) {
        if (scopes.peek().containsKey(s.nombre)) return false;
        scopes.peek().put(s.nombre, s);
        return true;
    }

    public Simbolo resolve(String nombre) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(nombre)) {
                Simbolo s = scopes.get(i).get(nombre);
                s.usado = true; // Marcamos que la variable "sirvió para algo"
                return s;
            }
        }
        return null;
    }

    public Simbolo buscarSinMarcar(String nombre) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(nombre)) {
                return scopes.get(i).get(nombre);
            }
        }
        return null;
    }
}