package org.example.model;

public interface LogicTruthTable {
    int[][] obtener(String operacion);
    Set obtenerOperaciones();
    void agregar(String operacion, int[][] tabla);
    void eliminar(String operacion);
}
