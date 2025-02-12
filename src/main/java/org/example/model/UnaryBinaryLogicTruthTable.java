package org.example.model;

public interface UnaryBinaryLogicTruthTable {
    /**
     * Devuelve el resultado de la operación unaria para el valor de entrada.
     * Precondición: input ∈ {0,1}.
     */
    int get(int input);

    /**
     * Retorna el conjunto de entradas definidas.
     */
    Set getKeys();

    /**
     * Agrega el par (input, output) a la tabla de verdad.
     * Precondición: No existe ya un mapeo para ese input.
     */
    void add(int input, int output);
}