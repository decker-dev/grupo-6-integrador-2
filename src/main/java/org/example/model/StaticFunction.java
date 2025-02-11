package org.example.model;

public class StaticFunction implements Function {

    private int[][] data;
    private int size;
    private final int capacity = 100;

    public StaticFunction() {
        this.data = new int[capacity][2];
        this.size = 0;
    }

    @Override
    public int get(int key) {
        for (int i = 0; i < size; i++) {
            if (data[i][0] == key) {
                return data[i][1];
            }
        }
        throw new RuntimeException("Clave no encontrada");
    }

    @Override
    public Set getKeys() {
        Set keys = new StaticSet();
        for (int i = 0; i < size; i++) {
            keys.add(data[i][0]);
        }
        return keys;
    }

    @Override
    public void add(int key, int value) {
        for (int i = 0; i < size; i++) {
            if (data[i][0] == key) {
                data[i][1] = value;
                return;
            }
        }
        if (size < capacity) {
            data[size][0] = key;
            data[size][1] = value;
            size++;
        } else {
            throw new RuntimeException("Capacidad máxima alcanzada");
        }
    }

    @Override
    public void remove(int key) {
        for (int i = 0; i < size; i++) {
            if (data[i][0] == key) {
                data[i] = data[size - 1];
                size--;
                return;
            }
        }
        throw new RuntimeException("Clave no encontrada");
    }
}
