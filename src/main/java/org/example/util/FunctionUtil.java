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


}