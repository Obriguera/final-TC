import java.util.*;

class Simbolo {
    String nombre;
    String tipo;
    boolean esFuncion;
    List<String> tiposParametros; // Tipos de los parámetros para funciones

    // Constructor para variables y funciones
    Simbolo(String n, String t, boolean f) {
        this.nombre = n;
        this.tipo = t;
        this.esFuncion = f;
        this.tiposParametros = new ArrayList<>();
    }

    // Agrega un tipo a la firma de la función
    void addParametro(String tipo) {
        this.tiposParametros.add(tipo);
    }
}

public class TablaSimbolos {
    private Stack<Map<String, Simbolo>> scopes = new Stack<>();

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        if (!scopes.isEmpty()) scopes.pop();
    }

    // Define un símbolo básico (mantiene compatibilidad con variables)
    public boolean define(String nombre, String tipo, boolean esFuncion) {
        if (scopes.peek().containsKey(nombre)) {
            return false;
        }
        scopes.peek().put(nombre, new Simbolo(nombre, tipo, esFuncion));
        return true;
    }

    // Define un objeto Simbolo completo (útil para funciones con parámetros)
    public boolean defineSimboloCompleto(Simbolo s) {
        if (scopes.peek().containsKey(s.nombre)) {
            return false;
        }
        scopes.peek().put(s.nombre, s);
        return true;
    }

    public Simbolo resolve(String nombre) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(nombre)) {
                return scopes.get(i).get(nombre);
            }
        }
        return null;
    }
}