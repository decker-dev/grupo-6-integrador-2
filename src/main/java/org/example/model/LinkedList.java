package org.example.model;

import org.example.model.nodes.Node;

public class LinkedList implements List {

    private Node first;

    public LinkedList() {
        this.first = null;
    }

    @Override
    public void add(int a) {
        if(this.first == null) {
            this.first = new Node(a, null);
            return;
        }

        Node candidate = this.first;
        while(candidate.getNext() != null) {
            candidate = candidate.getNext();
        }

        candidate.setNext(new Node(a, null));
    }

    @Override
    public void remove() {
        if(this.first == null) {
            throw new RuntimeException("No se puede eliminar de una lista vacía");
        }

        if(this.first.getNext() == null) {
            this.first = null;
            return;
        }

        Node backup = this.first;
        Node candidate = this.first.getNext();
        while(candidate.getNext() != null) {
            backup = candidate;
            candidate = candidate.getNext();
        }

        backup.setNext(null);
    }

    @Override
    public int length() {
        if(this.first == null) {
            return 0;
        }

        int count = 1;
        Node candidate = this.first;
        while(candidate.getNext() != null) {
            candidate = candidate.getNext();
            count++;
        }

        return count;
    }

    @Override
    public int get(int index) {
        if(index < 0) {
            throw new RuntimeException("El índice no puede ser negativo");
        }
        int size = length();
        if(index >= size) {
            throw new RuntimeException("El índice está fuera de rango");
        }

        if(index == 0) {
            return this.first.getValue();
        }

        int count = 1;
        Node candidate = this.first.getNext();
        while(candidate.getNext() != null) {
            if(count == index) {
                return candidate.getValue();
            }
            candidate = candidate.getNext();
            count++;
        }

        return 0;
    }
}
