package org.example.model;

import org.example.model.nodes.PriorityNode;

public class DynamicPriorityQueue implements PriorityQueue {

    private PriorityNode first;

    @Override
    public int getFirst() { // C + C + C + C + C // C
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede obtener el valor de una cola vacía"); // C
        } else {
            return this.first.getValue(); // C + C + C = C
        }
    }

    @Override
    public int getPriority() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede obtener la prioridad de una cola vacía");
        }
        return this.first.getPriority();
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public void add(int a, int priority) {
        if(this.isEmpty()) {
            this.first = new PriorityNode(a, priority, null);
            return;
        }

        if(this.first.getNext() == null) {
            if(priority < this.first.getPriority()) {
                this.first = new PriorityNode(a, priority, this.first);
                return;
            }
            this.first.setNext(new PriorityNode(a, priority, null));
            return;
        }

        if(priority < this.first.getPriority()) {
            this.first = new PriorityNode(a, priority, this.first);
            return;
        }

        int minPriority = getMinPriority();
        if(priority >= minPriority) {
            PriorityNode candidate = this.first;
            while(candidate.getNext() != null) {
                candidate = candidate.getNext();
            }
            candidate.setNext(new PriorityNode(a, priority, null));
            return;
        }

        PriorityNode backup = this.first;
        PriorityNode current = this.first.getNext();

        while(current.getNext() != null) {
            if(current.getPriority() > priority) {
                break;
            }
            backup = current;
            current = current.getNext();
        }

        backup.setNext(new PriorityNode(a, priority, current));
    }

    private int getMinPriority() { // C + C + C + N*C + C = N
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede obtener la prioridad de una cola vacía");
        }
        int candidate = Integer.MIN_VALUE; // C
        PriorityNode aux = this.first; // C

        // condicion + n*(complejidad del cuerpo)
        while(aux != null) { // C + N*C
            if(aux.getPriority() > candidate) {
                candidate = aux.getPriority();
            }
            aux = aux.getNext();
        }
        return candidate; // C
    }

    @Override
    public void remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("No se puede desacolar una cola vacía");
        }
        this.first = this.first.getNext();
    }
}
