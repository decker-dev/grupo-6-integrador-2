package org.example.model;

public class StaticUnaryBinaryLogicTruthTable implements UnaryBinaryLogicTruthTable {
    private static final int CAPACITY = 2;
    // Cada fila: [input, output]
    private int[][] data;
    private int count;

    public StaticUnaryBinaryLogicTruthTable() {
        data = new int[CAPACITY][2];
        count = 0;
    }

    @Override
    public int get(int input) {
        for (int i = 0; i < count; i++) {
            if (data[i][0] == input) {
                return data[i][1];
            }
        }
        throw new RuntimeException("Entrada no definida: " + input);
    }

    @Override
    public Set getKeys() {
        Set keys = new StaticSet();
        for (int i = 0; i < count; i++) {
            keys.add(data[i][0]);
        }
        return keys;
    }

    @Override
    public void add(int input, int output) {
        for (int i = 0; i < count; i++) {
            if (data[i][0] == input) {
                throw new RuntimeException("Entrada ya definida: " + input);
            }
        }
        if (count >= CAPACITY) {
            throw new RuntimeException("Capacidad máxima alcanzada");
        }
        data[count][0] = input;
        data[count][1] = output;
        count++;
    }
}