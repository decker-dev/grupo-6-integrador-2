package org.example.util;

public class LogicTruthTableUtil {

    public static int convertirOperacionAEntero(String operacion) {
        switch (operacion) {
            case "AND": return 1;
            case "OR": return 2;
            case "XOR": return 3;
            case "NAND": return 4;
            case "NOR": return 5;
            case "NOT": return 6;
            default: throw new RuntimeException("Operación desconocida");
        }
    }

    public static String convertirEnteroAOperacion(int numero) {
        switch (numero) {
            case 1: return "AND";
            case 2: return "OR";
            case 3: return "XOR";
            case 4: return "NAND";
            case 5: return "NOR";
            case 6: return "NOT";
            default: throw new RuntimeException("Código de operación desconocido");
        }
    }
}
