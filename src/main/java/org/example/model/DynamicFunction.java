package org.example.model;

import org.example.model.nodes.DictionaryNode;

public class DynamicFunction implements Function {

    private DictionaryNode node;

    @Override
    public int get(int key) {
        DictionaryNode aux = this.node;
        while (aux != null) {
            if (aux.getKey() == key) {
                return aux.getValue();
            }
            aux = aux.getNext();
        }
        throw new RuntimeException("Clave no encontrada");
    }

    @Override
    public Set getKeys() {
        Set keys = new StaticSet();
        DictionaryNode aux = this.node;
        while (aux != null) {
            keys.add(aux.getKey());
            aux = aux.getNext();
        }
        return keys;
    }

    @Override
    public void add(int key, int value) {
        if (this.node == null) {
            this.node = new DictionaryNode(key, value, null);
            return;
        }

        DictionaryNode aux = this.node;
        while (aux.getNext() != null) {
            if (aux.getKey() == key) {
                aux.setValue(value);
                return;
            }
            aux = aux.getNext();
        }

        if (aux.getKey() == key) {
            aux.setValue(value);
        } else {
            aux.setNext(new DictionaryNode(key, value, null));
        }
    }

    @Override
    public void remove(int key) {
        if (this.node == null) {
            throw new RuntimeException("Clave no encontrada");
        }

        if (this.node.getKey() == key) {
            this.node = this.node.getNext();
            return;
        }

        DictionaryNode prev = this.node;
        DictionaryNode current = this.node.getNext();

        while (current != null) {
            if (current.getKey() == key) {
                prev.setNext(current.getNext());
                return;
            }
            prev = current;
            current = current.getNext();
        }

        throw new RuntimeException("Clave no encontrada");
    }
}
