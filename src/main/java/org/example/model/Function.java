package org.example.model;

/**
 * Representa un mapeo (función) entre enteros.
 */
public interface Function {
    /**
     * Retorna el valor asociado a la clave.
     *
     * Precondición: La función no es vacía y la clave existe.
     * Postcondición: Se devuelve el entero asociado a la clave indicada.
     *
     * @param key clave (dominio)
     * @return valor asociado
     */
    int get(int key);

    /**
     * Retorna el conjunto de claves (dominio de la función).
     *
     * Precondición: -
     * Postcondición: Se devuelve un conjunto con todas las claves definidas.
     *
     * @return conjunto de claves.
     */
    Set getDomain();

    /**
     * Agrega un par (clave, valor) a la función.
     *
     * Precondición: La clave no se encuentra definida (o, en una versión inmutable,
     *                se crea una nueva función con el nuevo par).
     * Postcondición: La función resultante contiene el nuevo par, sin modificar el estado anterior.
     *
     * @param key la clave
     * @param value el valor asociado
     */
    Function add(int key, int value);

    /**
     * Elimina la asociación de la clave.
     *
     * Precondición: La clave existe en la función.
     * Postcondición: La función resultante ya no contiene la clave especificada.
     *
     * @param key la clave a eliminar.
     */
    Function remove(int key);
}