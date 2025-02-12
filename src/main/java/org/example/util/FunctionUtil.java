package org.example.util;

import java.util.Arrays;
import org.example.model.Function;
import org.example.model.Dictionary;
import org.example.model.MultipleDictionary;
import org.example.model.Set;
import org.example.model.StaticSet;
import org.example.model.List;
import org.example.model.StaticDictionary;
import org.example.model.StaticMultipleDictionary;

/**
 * Clase de utilidades para trabajar con funciones y diccionarios (incluyendo
 * diccionarios múltiples).
 */
public class FunctionUtil {

    // Constructor privado para evitar instanciación.
    private FunctionUtil() { }

    /**
     * Método map que convierte una función a un diccionario.
     *
     * Precondición: f no es nula y está definida para algún conjunto de claves.
     * Postcondición: Se retorna un diccionario que contiene, para cada clave x del dominio de f,
     *                el par (x, f(x)).
     * Estrategia de implementación:
     *   1. Se obtiene el conjunto de claves (dominio) de la función f.
     *   2. Se recorre dicho conjunto; para cada clave se obtiene su imagen f(x) y se agrega
     *      el par (x, f(x)) al diccionario resultante.
     *   3. Se elimina cada clave del conjunto temporal para avanzar en el recorrido.
     *
     * @param f La función a convertir.
     * @return Un diccionario con las asociaciones (x, f(x)).
     */
    public static Dictionary map(Function f) {
        Dictionary dic = new StaticDictionary();
        Set domain = f.getKeys();

        while (!domain.isEmpty()) {
            int key = domain.choose();
            dic.add(key, f.get(key));
            domain.remove(key);
        }

        return dic;
    }

    /**
     * Método mapToMultiple que convierte una función a un diccionario múltiple.
     *
     * Precondición: f no es nula y está definida para algún conjunto de claves.
     * Postcondición: Se retorna un diccionario múltiple que contiene, para cada clave x del dominio de f,
     *                el par (x, f(x)), donde cada clave se asocia a un contenedor (por ejemplo, una lista)
     *                que contiene el único valor f(x).
     * Estrategia de implementación:
     *   1. Se obtiene el conjunto de claves (dominio) de la función f.
     *   2. Se recorre dicho conjunto; para cada clave se obtiene su imagen f(x) y se agrega
     *      el par (x, f(x)) al diccionario múltiple resultante.
     *   3. Se elimina cada clave del conjunto temporal para avanzar en el recorrido.
     *
     * @param f La función a convertir.
     * @return Un diccionario múltiple con las asociaciones (x, f(x)).
     */
    public static MultipleDictionary mapToMultiple(Function f) {
        MultipleDictionary mdic = new StaticMultipleDictionary();
        Set domain = f.getKeys();

        while (!domain.isEmpty()) {
            int key = domain.choose();
            mdic.add(key, f.get(key));
            domain.remove(key);
        }

        return mdic;
    }

    /**
     * Determina si dos diccionarios múltiples son iguales.
     * Dos diccionarios múltiples son iguales si:
     *   - Tienen el mismo conjunto de claves.
     *   - Para cada clave, el contenedor de valores (ignorando el orden) es igual.
     *
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
     * Determina si una función es biyectiva.
     * Una función es biyectiva si es inyectiva, es decir, no se repiten valores en la imagen.
     *
     * Precondición: f no es nula.
     * Postcondición: Retorna true si f es biyectiva, false en caso contrario.
     *
     * @param f La función a evaluar.
     * @return true si f es biyectiva, false de lo contrario.
     */
    public static boolean isBijection(Function f) {
        Set domain = f.getKeys();
        Set values = new StaticSet();
        // Se usa una copia del dominio para no modificar el original.
        Set domainCopy = SetUtil.copy(domain);

        while (!domainCopy.isEmpty()) {
            int key = domainCopy.choose();
            int value = f.get(key);
            // Si ya existe el valor, la función no es inyectiva.
            if (contains(values, value)) {
                return false;
            }
            values.add(value);
            domainCopy.remove(key);
        }
        return true;
    }

    /**
     * Método auxiliar que verifica si un conjunto contiene un elemento.
     *
     * @param set El conjunto a evaluar.
     * @param element El elemento a buscar.
     * @return true si el elemento se encuentra en el conjunto, false de lo contrario.
     */
    private static boolean contains(Set set, int element) {
        Set copy = SetUtil.copy(set);
        while (!copy.isEmpty()) {
            int e = copy.choose();
            if (e == element) {
                return true;
            }
            copy.remove(e);
        }
        return false;
    }

    /**
     * Determina si un conjunto es un Singleton.
     * Un conjunto es Singleton si contiene exactamente un elemento.
     *
     * Precondición: s no es nulo.
     * Postcondición: Retorna true si s es un Singleton, false en caso contrario.
     *
     * @param s El conjunto a evaluar.
     * @return true si s es un Singleton, false de lo contrario.
     */
    public static boolean isSingleton(Set s) {
        return SetUtil.size(s) == 1;
    }

    /**
     * Determina si un diccionario representa parte de la secuencia de Fibonacci.
     * Se asume que la clave representa el índice de la sucesión y que el valor representa
     * el valor en ese índice.
     * Se verifica que:
     *   - El diccionario tenga al menos los índices 0 y 1.
     *   - Los índices sean consecutivos (0, 1, 2, ...).
     *   - Se cumpla que f(0)=0, f(1)=1 y para n>=2, f(n) = f(n-1) + f(n-2).
     *
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