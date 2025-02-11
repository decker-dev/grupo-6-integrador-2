package org.example.model;

import org.example.model.nodes.LogicDictionaryNode;
import org.example.util.LogicTruthTableUtil;

public class UnaryBinaryLogicTruthTable implements LogicTruthTable {

    private LogicDictionaryNode node;

    public UnaryBinaryLogicTruthTable() {
        this.node = null;
        agregar("NOT", new int[][]{
                {0, 1},
                {1, 0}
        });
    }

    @Override
    public int[][] obtener(String operacion) {
        LogicDictionaryNode aux = this.node;
        while (aux != null) {
            if (aux.getKey().equals(operacion)) {
                return aux.getValue();
            }
            aux = aux.getNext();
        }
        throw new RuntimeException("Operación no encontrada");
    }

    @Override
    public Set obtenerOperaciones() {
        Set conjunto = new StaticSet();
        LogicDictionaryNode aux = this.node;
        while (aux != null) {
            conjunto.add(LogicTruthTableUtil.convertirOperacionAEntero(aux.getKey()));
            aux = aux.getNext();
        }
        return conjunto;
    }

    @Override
    public void agregar(String operacion, int[][] tabla) {
        if (this.node == null) {
            this.node = new LogicDictionaryNode(operacion, tabla, null);
            return;
        }

        LogicDictionaryNode aux = this.node;
        while (aux.getNext() != null) {
            if (aux.getKey().equals(operacion)) {
                aux.setValue(tabla);
                return;
            }
            aux = aux.getNext();
        }

        if (aux.getKey().equals(operacion)) {
            aux.setValue(tabla);
        } else {
            aux.setNext(new LogicDictionaryNode(operacion, tabla, null));
        }
    }

    @Override
    public void eliminar(String operacion) {
        if (this.node == null) {
            throw new RuntimeException("Operación no encontrada");
        }

        if (this.node.getKey().equals(operacion)) {
            this.node = this.node.getNext();
            return;
        }

        LogicDictionaryNode prev = this.node;
        LogicDictionaryNode current = this.node.getNext();

        while (current != null) {
            if (current.getKey().equals(operacion)) {
                prev.setNext(current.getNext());
                return;
            }
            prev = current;
            current = current.getNext();
        }

        throw new RuntimeException("Operación no encontrada");
    }
}
