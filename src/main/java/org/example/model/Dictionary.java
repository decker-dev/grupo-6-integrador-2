package org.example.model;

/**
 * Interfaz Dictionary que define una estructura de datos de clave-valor.
 * Implementa un diccionario donde cada clave tiene un único valor asociado.
 */
public interface Dictionary {

    /**
     * Obtiene el valor asociado a una clave específica.
     *
     * **Precondición**: La clave debe existir en el diccionario.
     * **Postcondición**: Retorna el valor asociado a la clave sin modificar el diccionario.
     * **Estrategia de implementación**:
     * - **Versión dinámica** (`DynamicFunction`): Recorre la lista enlazada de nodos (`DictionaryNode`)
     *   hasta encontrar la clave buscada o lanzar una excepción si no existe.
     * - **Versión estática** (`StaticFunction`): Recorre un array bidimensional (`data[][]`)
     *   linealmente hasta encontrar la clave.
     *
     * @param key Clave a buscar.
     * @return Valor asociado a la clave.
     * @throws RuntimeException si la clave no existe.
     */
    int get(int key);

    /**
     * Obtiene un conjunto con todas las claves del diccionario.
     *
     * **Precondición**: Ninguna.
     * **Postcondición**: Devuelve un conjunto (`Set`) con todas las claves almacenadas.
     * **Estrategia de implementación**:
     * - **Versión dinámica** (`DynamicFunction`): Recorre la lista enlazada (`DictionaryNode`) y
     *   almacena cada clave en un `StaticSet`.
     * - **Versión estática** (`StaticFunction`): Itera sobre el array bidimensional `data[][]`
     *   y extrae las claves en un `StaticSet`.
     *
     * @return Conjunto de claves almacenadas en el diccionario.
     */
    Set getKeys();

    /**
     * Agrega una nueva clave con su valor al diccionario.
     *
     * **Precondición**: Si la clave ya existe, su valor es reemplazado.
     * **Postcondición**: Se añade o actualiza la clave en el diccionario.
     * **Estrategia de implementación**:
     * - **Versión dinámica** (`DynamicFunction`): Recorre la lista enlazada (`DictionaryNode`),
     *   si la clave existe, actualiza su valor; si no, agrega un nuevo nodo al final.
     * - **Versión estática** (`StaticFunction`): Busca la clave en el array `data[][]`. Si la
     *   encuentra, actualiza el valor; si no, agrega un nuevo par clave-valor si hay espacio disponible.
     *
     * @param key Clave a agregar.
     * @param value Valor asociado a la clave.
     */
    void add(int key, int value);

    /**
     * Elimina una clave del diccionario.
     *
     * **Precondición**: La clave debe existir en el diccionario.
     * **Postcondición**: La clave y su valor asociado son eliminados.
     * **Estrategia de implementación**:
     * - **Versión dinámica** (`DynamicFunction`): Recorre la lista enlazada (`DictionaryNode`),
     *   si encuentra la clave, ajusta los punteros de los nodos para eliminarla.
     * - **Versión estática** (`StaticFunction`): Busca la clave en `data[][]`. Si la encuentra,
     *   la reemplaza por el último elemento del array y reduce el tamaño.
     *
     * @param key Clave a eliminar.
     * @throws RuntimeException si la clave no existe.
     */
    void remove(int key);
}
