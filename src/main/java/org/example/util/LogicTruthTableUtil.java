package org.example.util;

import org.example.model.UnaryBinaryLogicTruthTable;
import org.example.model.BinaryBinaryLogicTruthTable;
import org.example.model.UnaryTernaryLogicTruthTable;
import org.example.model.BinaryTernaryLogicTruthTable;
import org.example.model.StaticUnaryBinaryLogicTruthTable;
import org.example.model.StaticBinaryBinaryLogicTruthTable;
import org.example.model.StaticUnaryTernaryLogicTruthTable;
import org.example.model.StaticBinaryTernaryLogicTruthTable;

public class LogicTruthTableUtil {

    private LogicTruthTableUtil() { }

    /**
     * Retorna la tabla de verdad para la operación NOT (unaria).
     * Definición: NOT(0)=1, NOT(1)=0.
     */
    public static UnaryBinaryLogicTruthTable notBinary() {
        StaticUnaryBinaryLogicTruthTable table = new StaticUnaryBinaryLogicTruthTable();
        table.add(0, 1);
        table.add(1, 0);
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación AND (binaria).
     * Definición:
     * 0 AND 0 = 0
     * 0 AND 1 = 0
     * 1 AND 0 = 0
     * 1 AND 1 = 1
     */
    public static BinaryBinaryLogicTruthTable andBinary() {
        StaticBinaryBinaryLogicTruthTable table = new StaticBinaryBinaryLogicTruthTable();
        table.add(0, 0, 0);
        table.add(0, 1, 0);
        table.add(1, 0, 0);
        table.add(1, 1, 1);
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación NAND (binaria).
     * Definición: Es la negación de AND.
     */
    public static BinaryBinaryLogicTruthTable nandBinary() {
        StaticBinaryBinaryLogicTruthTable table = new StaticBinaryBinaryLogicTruthTable();
        table.add(0, 0, 1);
        table.add(0, 1, 1);
        table.add(1, 0, 1);
        table.add(1, 1, 0);
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación OR (binaria).
     * Definición:
     * 0 OR 0 = 0
     * 0 OR 1 = 1
     * 1 OR 0 = 1
     * 1 OR 1 = 1
     */
    public static BinaryBinaryLogicTruthTable orBinary() {
        StaticBinaryBinaryLogicTruthTable table = new StaticBinaryBinaryLogicTruthTable();
        table.add(0, 0, 0);
        table.add(0, 1, 1);
        table.add(1, 0, 1);
        table.add(1, 1, 1);
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación NOR (binaria).
     * Definición: Es la negación de OR.
     */
    public static BinaryBinaryLogicTruthTable norBinary() {
        StaticBinaryBinaryLogicTruthTable table = new StaticBinaryBinaryLogicTruthTable();
        table.add(0, 0, 1);
        table.add(0, 1, 0);
        table.add(1, 0, 0);
        table.add(1, 1, 0);
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación XOR (binaria).
     * Definición:
     * 0 XOR 0 = 0
     * 0 XOR 1 = 1
     * 1 XOR 0 = 1
     * 1 XOR 1 = 0
     */
    public static BinaryBinaryLogicTruthTable xorBinary() {
        StaticBinaryBinaryLogicTruthTable table = new StaticBinaryBinaryLogicTruthTable();
        table.add(0, 0, 0);
        table.add(0, 1, 1);
        table.add(1, 0, 1);
        table.add(1, 1, 0);
        return table;
    }

    // *** Operaciones para lógica ternaria (valores {0,1,2}) ***

    /**
     * Retorna la tabla de verdad para la operación NOT (unaria) en lógica ternaria.
     * Definición (por ejemplo, estilo Łukasiewicz):
     * NOT(0) = 2, NOT(1) = 1, NOT(2) = 0.
     */
    public static UnaryTernaryLogicTruthTable notTernary() {
        StaticUnaryTernaryLogicTruthTable table = new StaticUnaryTernaryLogicTruthTable();
        table.add(0, 2);
        table.add(1, 1);
        table.add(2, 0);
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación AND (binaria) en lógica ternaria.
     * Definición: AND(a, b) = min(a, b)
     */
    public static BinaryTernaryLogicTruthTable andTernary() {
        StaticBinaryTernaryLogicTruthTable table = new StaticBinaryTernaryLogicTruthTable();
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                table.add(a, b, Math.min(a, b));
            }
        }
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación NAND (binaria) en lógica ternaria.
     * Definición: NAND(a, b) = NOT(AND(a, b)) = 2 - min(a, b)
     */
    public static BinaryTernaryLogicTruthTable nandTernary() {
        StaticBinaryTernaryLogicTruthTable table = new StaticBinaryTernaryLogicTruthTable();
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                table.add(a, b, 2 - Math.min(a, b));
            }
        }
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación OR (binaria) en lógica ternaria.
     * Definición: OR(a, b) = max(a, b)
     */
    public static BinaryTernaryLogicTruthTable orTernary() {
        StaticBinaryTernaryLogicTruthTable table = new StaticBinaryTernaryLogicTruthTable();
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                table.add(a, b, Math.max(a, b));
            }
        }
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación NOR (binaria) en lógica ternaria.
     * Definición: NOR(a, b) = NOT(OR(a, b)) = 2 - max(a, b)
     */
    public static BinaryTernaryLogicTruthTable norTernary() {
        StaticBinaryTernaryLogicTruthTable table = new StaticBinaryTernaryLogicTruthTable();
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                table.add(a, b, 2 - Math.max(a, b));
            }
        }
        return table;
    }

    /**
     * Retorna la tabla de verdad para la operación XOR (binaria) en lógica ternaria.
     * Definición (simplificada): Si a == b entonces 0; de lo contrario, 2.
     */
    public static BinaryTernaryLogicTruthTable xorTernary() {
        StaticBinaryTernaryLogicTruthTable table = new StaticBinaryTernaryLogicTruthTable();
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                int result = (a == b) ? 0 : 2;
                table.add(a, b, result);
            }
        }
        return table;
    }
}