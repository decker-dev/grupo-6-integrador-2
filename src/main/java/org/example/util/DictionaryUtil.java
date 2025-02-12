package org.example.util;

import org.example.model.*;

import java.util.Arrays;

public class DictionaryUtil {

    public static void print(Dictionary dictionary) {
        Set keys = dictionary.getKeys();

        while (!keys.isEmpty()) {
            int key = keys.choose();
            int value = dictionary.get(key);
            System.out.println("key: " + key + ", value: " + value);
            keys.remove(key);
        }
    }
    /**
     * Método auxiliar que convierte una List en un Set.
     * Se asume que la List posee métodos length() y get(int index).
     *
     * @param list La lista a convertir.
     * @return Un conjunto que contiene los elementos de la lista.
     */
    private static Set listToSet(List list) {
        Set set = new StaticSet();
        for (int i = 0; i < list.length(); i++) {
            set.add(list.get(i));
        }
        return set;
    }
    /**
     * Determina si dos diccionarios múltiples son iguales.
     * Dos diccionarios múltiples son iguales si:
     * - Tienen el mismo conjunto de claves.
     * - Para cada clave, el contenedor de valores (ignorando el orden) es igual.
     * <p>
     * Precondición: d1 y d2 no son nulos.
     * Postcondición: Retorna true si son iguales, false en caso contrario.
     *
     * @param d1 Primer diccionario múltiple.
     * @param d2 Segundo diccionario múltiple.
     * @return true si d1 y d2 son iguales, false de lo contrario.
     */
    public static boolean multipleDictionariesEqual(MultipleDictionary d1, MultipleDictionary d2) {
        Set keys1 = d1.getKeys();
        Set keys2 = d2.getKeys();

        // Verificar que ambos tengan el mismo conjunto de claves.
        if (!SetUtil.equals(keys1, keys2)) {
            return false;
        }

        // Se recorre el conjunto de claves (usando una copia para no modificar el original).
        Set keysCopy = SetUtil.copy(keys1);
        while (!keysCopy.isEmpty()) {
            int key = keysCopy.choose();
            // Se obtienen las listas asociadas a la clave en cada diccionario múltiple.
            List list1 = d1.get(key);
            List list2 = d2.get(key);
            // Convertir cada lista a un conjunto para comparar sin tener en cuenta el orden.
            Set set1 = listToSet(list1);
            Set set2 = listToSet(list2);
            if (!SetUtil.equals(set1, set2)) {
                return false;
            }
            keysCopy.remove(key);
        }

        return true;
    }

    /**
     * Determina si un diccionario representa parte de la secuencia de Fibonacci.
     * Se asume que la clave representa el índice de la sucesión y que el valor representa
     * el valor en ese índice.
     * Se verifica que:
     * - El diccionario tenga al menos los índices 0 y 1.
     * - Los índices sean consecutivos (0, 1, 2, ...).
     * - Se cumpla que f(0)=0, f(1)=1 y para n>=2, f(n) = f(n-1) + f(n-2).
     * <p>
     * Precondición: d no es nulo.
     * Postcondición: Retorna true si d representa parte de la secuencia de Fibonacci, false en caso contrario.
     *
     * @param d El diccionario a evaluar.
     * @return true si d representa parte de la secuencia Fibonacci, false de lo contrario.
     */
    public static boolean isFibonacciDictionary(Dictionary d) {
        Set keys = d.getKeys();
        int size = SetUtil.size(keys);
        if (size == 0) return false;

        // Extraer los índices en un arreglo y ordenarlos.
        int[] indices = new int[size];
        int i = 0;
        Set keysCopy = SetUtil.copy(keys);
        while (!keysCopy.isEmpty()) {
            int key = keysCopy.choose();
            indices[i++] = key;
            keysCopy.remove(key);
        }
        Arrays.sort(indices);

        // Verificar que los índices sean consecutivos y que comiencen en 0.
        if (indices[0] != 0) return false;
        for (i = 1; i < indices.length; i++) {
            if (indices[i] != indices[i - 1] + 1) {
                return false;
            }
        }

        // Verificar f(0)=0 y f(1)=1.
        try {
            if (d.get(0) != 0 || d.get(1) != 1) {
                return false;
            }
        } catch (RuntimeException e) {
            return false;
        }

        // Verificar la recurrencia Fibonacci: para n>=2, f(n) = f(n-1) + f(n-2).
        for (i = 2; i < indices.length; i++) {
            int expected = d.get(i - 1) + d.get(i - 2);
            if (d.get(i) != expected) {
                return false;
            }
        }

        return true;
    }
}

