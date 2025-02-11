package org.example.model;

public class StaticQueue implements Queue {

    private static final int MAX = 10000;

    private final int[] array;
    private int count;

    public StaticQueue() {
        this.array = new int[MAX];
        this.count = 0;
    }

    @Override
    public int getFirst() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede obtener el primero de una cola vacia");
        }
        return this.array[0];
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public void add(int a) {
        this.array[count] = a;
        this.count++;
    }

    @Override
    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desacolar de una cola vacia");
        }
        for(int i = 0; i < this.count - 1; i++) {
            this.array[i] = this.array[i+1];
        }
        this.count--;
    }

    public void f() {

    }

}
