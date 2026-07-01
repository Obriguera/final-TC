import java.util.*;


/*
"Molde" para guardar la información de cada identificador (variable o funcion) que el compilador
encuentra en el código fuente.
 */
class Simbolo {
    String nombre, tipo;
    boolean esFuncion, usado = false;
    List<String> tiposParametros;
    int linea;

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


/*
Estructura que administra todos los símbolos utilizando una Pila de diccionarios.
Cada diccionario en una pila representa un scope
*/

public class TablaSimbolos {

    private Stack<Map<String, Simbolo>> scopes = new Stack<>();

    public TablaSimbolos() {
        enterScope(); // Crear ámbito global
    }


    // Crea un diccionario vacia y lo apila
    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    // Destruye el diccionario actual al salir de un bloque.
    // Antes de salir itera por lo elementos, si encuentra una variable sin usar imprime el
    // [WARNING] correspondiente. Luego saca el diccionario de la pila
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

    /*
     Intentan agregar un símbolo al ámbito actual. Verifican si el nombre ya existe en ese diccionario
     exacto, Si existe devuelve false. Si no existe lo guardan
    */
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


    /*
    Recorre la pula desde arriba hacia abajo (desde el ambito interno hacia el global)
    Si encuentra la variable, cambia su estado a s.usado=true y la devuelve.
    */
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


    /*
    Trabaja igual que RESOLVE, pero sin marcar el estado USADO
    */
    public Simbolo buscarSinMarcar(String nombre) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(nombre)) {
                return scopes.get(i).get(nombre);
            }
        }
        return null;
    }
}