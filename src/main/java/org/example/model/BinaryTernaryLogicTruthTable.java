package org.example.model;

public interface BinaryTernaryLogicTruthTable {
    /**
     * Devuelve el resultado de la operación binaria para la pareja (input1, input2).
     * Precondición: input1, input2 ∈ {0,1,2}.
     */
    int get(int input1, int input2);

    /**
     * Retorna el conjunto de “claves” (cada clave codifica un par de entradas).
     */
    Set getKeys();

    /**
     * Agrega el mapeo de la pareja (input1, input2) al resultado output.
     * Precondición: No existe ya un mapeo para esa pareja.
     */
    void add(int input1, int input2, int output);
}