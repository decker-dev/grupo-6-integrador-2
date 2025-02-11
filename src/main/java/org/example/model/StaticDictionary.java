package org.example.model;

public class StaticDictionary implements Dictionary {

    private static final int MAX = 10000;
    private final int[][] matrix;
    private int count;

    public StaticDictionary() {
        this.matrix = new int[MAX][2];
    }

    @Override
    public int get(int key) {
        if(count == 0) {
            throw new RuntimeException("No se puede obtener un valor de una estructura vacía");
        }
        for(int i = 0; i < count; i++) {
            if(matrix[i][0] == key) {
                return matrix[i][1];
            }
        }
        throw new RuntimeException("La clave no existe");
    }

    @Override
    public Set getKeys() {
        Set result = new StaticSet();
        for(int i = 0; i < count; i++) {
            result.add(matrix[i][0]);
        }
        return result;
    }

    @Override
    public void add(int key, int value) {
        if(count == 0) {
            matrix[0][0] = key;
            matrix[0][1] = value;
            count++;
            return;
        }

        for(int i = 0; i < count; i++) {
            if(matrix[i][0] == key) {
                if(matrix[i][1] == value) {
                    throw new RuntimeException("El par ya existe");
                }
                throw new RuntimeException("La clave ya existe");
            }
        }
        matrix[count][0] = key;
        matrix[count][1] = value;
        count++;
    }

    @Override
    public void remove(int key) {
        for(int i = 0; i < count; i++) {
            if(matrix[i][0] == key) {
                matrix[i][0] = matrix[count-1][0];
                matrix[i][1] = matrix[count-1][1];
                count--;
                return;
            }
        }
        throw new RuntimeException("La clave no existe");
    }
}
