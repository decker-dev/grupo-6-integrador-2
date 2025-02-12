package org.example.model;

public class StaticBinaryBinaryLogicTruthTable implements BinaryBinaryLogicTruthTable {
    private static final int CAPACITY = 4;  // 4 combinaciones para {0,1}x{0,1}
    // Cada fila: [clave, output, input1, input2]
    // Se codifica la pareja como: clave = 10*input1 + input2.
    private int[][] data;
    private int count;

    public StaticBinaryBinaryLogicTruthTable() {
        data = new int[CAPACITY][4];
        count = 0;
    }

    private int encode(int a, int b) {
        return 10 * a + b;
    }

    @Override
    public int get(int input1, int input2) {
        int key = encode(input1, input2);
        for (int i = 0; i < count; i++) {
            if (data[i][0] == key) {
                return data[i][1];
            }
        }
        throw new RuntimeException("Par no definido: (" + input1 + "," + input2 + ")");
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
    public void add(int input1, int input2, int output) {
        int key = encode(input1, input2);
        for (int i = 0; i < count; i++) {
            if (data[i][0] == key) {
                throw new RuntimeException("Par ya definido: (" + input1 + "," + input2 + ")");
            }
        }
        if (count >= CAPACITY) {
            throw new RuntimeException("Capacidad máxima alcanzada");
        }
        data[count][0] = key;
        data[count][1] = output;
        data[count][2] = input1;
        data[count][3] = input2;
        count++;
    }
}