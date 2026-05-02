import java.util.*;

class Simbolo {
    String nombre;
    String tipo;
    boolean esFuncion;
    Simbolo(String n, String t, boolean f) { this.nombre = n; this.tipo = t; this.esFuncion = f; }
}

public class TablaSimbolos {
    // Pila de ámbitos para manejar la jerarquía (Global -> Función -> Bloque)
    private Stack<Map<String, Simbolo>> scopes = new Stack<>();

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        if (!scopes.isEmpty()) scopes.pop();
    }

    // Define un símbolo en el ámbito actual
    public boolean define(String nombre, String tipo, boolean esFuncion) {
        if (scopes.peek().containsKey(nombre)) {
            return false; // Error: Ya definido en este ámbito
        }
        scopes.peek().put(nombre, new Simbolo(nombre, tipo, esFuncion));
        return true;
    }

    // Busca un símbolo desde el ámbito actual hacia afuera
    public Simbolo resolve(String nombre) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(nombre)) {
                return scopes.get(i).get(nombre);
            }
        }
        return null; // Error: No declarado
    }
}