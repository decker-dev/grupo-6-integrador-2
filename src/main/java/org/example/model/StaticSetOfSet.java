package org.example.model;

import java.util.Random;

public class StaticSetOfSet implements SetOfSet {

    private static final int MAX = 10000;

    private final Set[] array;
    private int count;
    private final Random random;

    public StaticSetOfSet() {
        this.array = new Set[MAX];
        this.count = 0;
        this.random = new Random();
    }

    @Override
    public void add(Set a) {
        for(int i = 0; i < count; i++) {
            if(array[i].equals(a)) {
                return;
            }
        }
        array[count] = a;
        count++;
    }

    @Override
    public void remove(Set a) {
        for(int i = 0; i < count; i++) {
            if(array[i].equals(a)) {
                array[i] = array[count - 1];
                count--;
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public Set choose() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede elegir un elemento de un conjunto vacío");
        }
        if(this.count == 1) {
            return array[0];
        }
        int randomIndex = random.nextInt(count);
        return array[randomIndex];
    }
}
