import java.io.PrintWriter;
import java.util.*;
import java.io.IOException;


/*
Toma el código intermedio (TAC) que ya está limpio y optimizado, lo traduce a pseudoEnsamblador
Además, se encarga de guardar este resultado en un archivo .asm
*/

public class GeneradorEnsamblador {

    public void generar(List<InstruccionTAC> codigo, String rutaSalida) {
        System.out.println("\u001B[32m[INFO] Iniciando generación de código objeto...\u001B[0m");

        // Usamos PrintWriter para escribir en el archivo
        try (PrintWriter writer = new PrintWriter(rutaSalida)) {
            for (InstruccionTAC i : codigo) {
                if (i.res == null && i.arg1 == null && i.arg2 == null) {
                    writer.println(i.op + ":"); // Escribe en el archivo
                    System.out.println(i.op + ":"); // Imprime en consola
                    continue;
                }

                String instruccionASM = "";
                //Mapea estructuras del TAC a comandos especificos
                switch (i.op) {
                    case "=" -> instruccionASM = "MOV " + i.res + ", " + i.arg1;
                    case "goto" -> instruccionASM = "JMP " + i.res;
                    case "ifFalse" -> {
                        instruccionASM = "CMP " + i.arg1 + ", 0\nJE " + i.res;
                    }
                    case "+", "-", "*", "/", "%" -> {
                        instruccionASM = "LOAD R1, " + i.arg1 + "\n" +
                                traducirOp(i.op) + " R1, " + i.arg2 + "\n" +
                                "STORE " + i.res + ", R1";
                    }
                    case ">", "<", "==", "!=", ">=", "<=" -> {
                        instruccionASM = "LOAD R1, " + i.arg1 + "\n" +
                                "CMP R1, " + i.arg2 + "\n" +
                                traducirRelacional(i.op) + " " + i.res;
                    }
                    case "return" -> {
                        if (i.arg1 != null && !i.arg1.isEmpty()) {
                            instruccionASM = "MOV EAX, " + i.arg1 + "\nRET";
                        } else {
                            instruccionASM = "RET";
                        }
                    }
                    case "param" -> instruccionASM = "PUSH " + i.arg1;
                    case "call" -> {
                        instruccionASM = "CALL " + i.arg1;
                        if (i.res != null && !i.res.isEmpty()) instruccionASM += "\nMOV " + i.res + ", EAX";
                    }
                    case "[]=" -> {
                        instruccionASM = "LOAD R2, " + i.arg2 + "\nSTORE " + i.res + "[R2], " + i.arg1;
                    }
                    case "[]" -> {
                        instruccionASM = "LOAD R2, " + i.arg2 + "\nLOAD R1, " + i.arg1 + "[R2]\nSTORE " + i.res + ", R1";
                    }
                    default -> instruccionASM = "; Instrucción no implementada: " + i.op;
                }

                writer.println(instruccionASM);
                System.out.println(instruccionASM);
            }
            System.out.println("\u001B[32m[ÉXITO] Archivo generado correctamente en: " + rutaSalida + "\u001B[0m");
        } catch (IOException e) {
            System.err.println("\u001B[31m[ERROR] No se pudo guardar el archivo: " + e.getMessage() + "\u001B[0m");
        }
    }

    //Metodos auxiliares para limpiar el switch principal
    private String traducirOp(String op) {
        return switch (op) {
            case "+" -> "ADD";
            case "-" -> "SUB";
            case "*" -> "MUL";
            case "/" -> "DIV";
            case "%" -> "MOD";
            default -> "NOP";
        };
    }

    private String traducirRelacional(String op) {
        return switch (op) {
            case ">" -> "SETG";   // Set if Greater
            case "<" -> "SETL";   // Set if Less
            case "==" -> "SETE";  // Set if Equal
            case "!=" -> "SETNE"; // Set if Not Equal
            case ">=" -> "SETGE"; // Set if Greater or Equal
            case "<=" -> "SETLE"; // Set if Less or Equal
            default -> "NOP";
        };
    }
}