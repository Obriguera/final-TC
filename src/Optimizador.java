import java.util.*;


/*
Optimizador --> limpia, reduce y hace que el código intermedio sea lo más rápido y
                eficiente posible
antes de pasarlo a lenguaje ensamblador
*/
public class Optimizador {


    private TablaSimbolos tabla; //Para consultar información histórica de las variables
    private AgenteIA agenteIA; // Instancia y entrena automaticamente el modelo

    public Optimizador(TablaSimbolos tabla) {
        this.tabla = tabla;
        this.agenteIA = new AgenteIA();
    }


    /*
   Aplica:
            Propagación de Constantes:  Utiliza un diccionario para recordar el valor literal
                de una variable. Si lee t0=5 lo guarda. Si en la siguiente linea lee 't1 = t0 +2',
                reemplzada t0 por 5.
            Constant Folding: Una vez propagados los valores, si detecta una instruccion matematica
                con dos numeros puros, le pide al metodo auxiliar 'aplicarOperacion' que resuelva la matematica
                en tiempo de compilacion. La instruccion se transforma en una simple asignacion
            Simplificación Algebraica: Aplica reglas matemáticas neutrales. Si detecta operaciones como
                x = y + 0 o x = 0 + y, elimina la suma y la convierte en asignacion directa.
    */
    public List<InstruccionTAC> optimizar(List<InstruccionTAC> codigo) {
        List<InstruccionTAC> optimizado = new ArrayList<>(codigo);
        boolean cambio;

        //Aplica técnicas de análisis de flujo de datos. Usa el do-while para aplicar las
        //Optimizaciones en cascada.
        do {
            cambio = false;
            // Mapa para Propagación de Constantes (Soluciona Bug 3)
            Map<String, String> constantes = new HashMap<>();

            for (int i = 0; i < optimizado.size(); i++) {
                InstruccionTAC inst = optimizado.get(i);

                // Reemplazamos variables por sus valores constantes si los conocemos
                if (constantes.containsKey(inst.arg1)) inst.arg1 = constantes.get(inst.arg1);
                if (constantes.containsKey(inst.arg2)) inst.arg2 = constantes.get(inst.arg2);

                // Registrar nueva constante (Ej: t0 = 5)
                if (inst.op.equals("=") && esNumero(inst.arg1)) {
                    constantes.put(inst.res, inst.arg1);
                }

                // 1. Constant Folding (Ahora funciona gracias a la propagación)
                if (esNumero(inst.arg1) && esNumero(inst.arg2) && esOperacionAritmetica(inst.op)) {
                    int val1 = Integer.parseInt(inst.arg1);
                    int val2 = Integer.parseInt(inst.arg2);
                    int res = aplicarOperacion(val1, inst.op, val2);

                    optimizado.set(i, new InstruccionTAC("=", String.valueOf(res), "", inst.res));
                    constantes.put(inst.res, String.valueOf(res)); // Guardamos el nuevo valor
                    cambio = true;
                }

                // 2. Simplificación Algebraica Mejorada (Ambos lados)
                if (inst.op.equals("+")) {
                    if ("0".equals(inst.arg2)) { // x = y + 0
                        optimizado.set(i, new InstruccionTAC("=", inst.arg1, "", inst.res));
                        cambio = true;
                    } else if ("0".equals(inst.arg1)) { // x = 0 + y
                        optimizado.set(i, new InstruccionTAC("=", inst.arg2, "", inst.res));
                        cambio = true;
                    }
                }
            }
        } while (cambio);

        return optimizado;
    }

    /*
    Delega la limpieza al AgenteIA en dos fases:
        Fase 1 (Extraccion de caracteristicas): El optimizador escanea toda la lista de instrucciones TAC.
        Por cada instruccion, anota en dos diccionarios el comportamiento de cada variable (asignaciones y lecturas).
        Si ve X = ..., suma una signación a X, si ve ... = x + 1, suma una lectura a X

        Fase 2 (Inferencia y limpieza): Vuelve a escanear el código. Cuando encuentra una instrucción que asigna
         asigna un valor a una variable (que no sea una llamada a funcion, ya que estas pueden tener efectos secundarios),
         extrae los contadores de asignaciones y lecturas de esa variable y se los pasa a
         agenteIA.esCodigoMuerto(). Si la IA devuelve 'true' ejecuta un continue,
         lo que la alimina de la lista final 'optimizado'
    */

    public List<InstruccionTAC> eliminarCodigoMuerto(List<InstruccionTAC> codigo) {
        List<InstruccionTAC> optimizado = new ArrayList<>();

        // --- FASE 1: Extracción de Características (Feature Extraction) ---
        // Vamos a contar cuántas veces se asigna y cuántas se lee cada variable.
        Map<String, Integer> lecturas = new HashMap<>();
        Map<String, Integer> asignaciones = new HashMap<>();

        for (InstruccionTAC i : codigo) {
            // Contar asignaciones (res)
            boolean esAsignacion = i.res != null && !i.res.isEmpty() && !i.op.equals("goto") && !i.op.equals("ifFalse") && !i.op.endsWith(":");
            if (esAsignacion) {
                asignaciones.put(i.res, asignaciones.getOrDefault(i.res, 0) + 1);
            }

            // Contar lecturas (arg1 y arg2)
            if (i.arg1 != null && !esNumero(i.arg1)) {
                lecturas.put(i.arg1, lecturas.getOrDefault(i.arg1, 0) + 1);
            }
            if (i.arg2 != null && !esNumero(i.arg2)) {
                lecturas.put(i.arg2, lecturas.getOrDefault(i.arg2, 0) + 1);
            }
        }

        // --- FASE 2: Inferencia de IA (Tomar la decisión) ---
        for (InstruccionTAC i : codigo) {
            boolean esAsignacion = i.res != null && !i.res.isEmpty() && !i.op.equals("goto") && !i.op.equals("ifFalse") && !i.op.endsWith(":");

            if (esAsignacion) {
                // Obtenemos los datos de la variable actual
                int cantAsignaciones = asignaciones.getOrDefault(i.res, 0);
                int cantLecturas = lecturas.getOrDefault(i.res, 0);

                // (Opcional) Aquí podrías detectar si estás dentro de un loop revisando las etiquetas L0, L1.
                // Para simplificar, lo dejaremos en false o podrías pasarle la info si la tienes.
                boolean enLoop = false;

                // ¡LA IA TOMA EL CONTROL!
                // Si la IA dice que es código muerto (true) y NO es una llamada a función (call)...
                if (!i.op.equals("call") && agenteIA.esCodigoMuerto(cantAsignaciones, cantLecturas, enLoop)) {
                    System.out.println("\u001B[35m[IA] Optimizador eliminó '" + i.res + "' (Asignaciones: " + cantAsignaciones + ", Lecturas: " + cantLecturas + ")\u001B[0m");
                    continue; // ¡Lo borramos! Saltamos esta instrucción.
                }
            }

            // Si la IA decidió que es útil (o no es una asignación), lo agregamos al código final
            optimizado.add(i);
        }

        return optimizado;
    }


    // Utiliza una expresion regular para validar de forma segura si el string es un entero,
    // antes de intentar convertirlo, previniendo caidas del compilador
    private boolean esNumero(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.matches("-?\\d+");
    }

    private boolean esOperacionAritmetica(String op) {
        return op != null && (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"));
    }


    /* Ejecuta aritmetica real. Evita la division por 0. Falla de manera segura */
    private int aplicarOperacion(int a, String op, int b) {
        if (op.equals("/") && b == 0) {
            System.err.println("\u001B[31m[ERROR CRÍTICO] División por cero detectada durante Constant Folding.\u001B[0m");
            return 0;
        }
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }
}