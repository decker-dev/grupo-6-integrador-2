package org.example.util;

import org.example.model.Set;
import org.example.model.StaticSet;
import org.example.model.StaticDictionary;
import org.example.model.Dictionary;
import org.example.model.Function;
import org.example.model.MultipleDictionary;
import org.example.model.StaticMultipleDictionary;

public class FunctionUtil {

    private static class Pair{
        int x;
        int y;

        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isBijective(Set domain, Set codomain, Set functionPairs) {
        return isInjective(domain, codomain, functionPairs) &&
                isSurjective(domain, codomain, functionPairs) &&
                isFunction(domain, functionPairs);
    }

    /**
     * Verifica si cada elemento del dominio está asociado a un único elemento
     */
    private static boolean isFunction(Set domain, Set functionPairs) {
        Set domainElements = new StaticSet();
        Set copyPairs = SetUtil.copy(functionPairs);

        while (!copyPairs.isEmpty()) {
            int pair = copyPairs.choose();
            int x = getPairX(pair);

            if (SetUtil.in(x, domainElements)) {
                return false;
            }
            domainElements.add(x);
            copyPairs.remove(pair);
        }

        return SetUtil.size(domainElements) == SetUtil.size(domain);
    }

    /**
     * Verifica si la función es inyectiva
     */
    private static boolean isInjective(Set domain, Set codomain, Set functionPairs) {
        Set usedValues = new StaticSet();
        Set copyPairs = SetUtil.copy(functionPairs);

        while (!copyPairs.isEmpty()) {
            int pair = copyPairs.choose();
            int y = getPairY(pair);

            if (SetUtil.in(y, usedValues)) {
                return false;
            }
            usedValues.add(y);
            copyPairs.remove(pair);
        }

        return true;
    }

    /**
     * Verifica si la función es sobreyectiva
     */
    private static boolean isSurjective(Set domain, Set codomain, Set functionPairs) {
        Set rangeValues = new StaticSet();
        Set copyPairs = SetUtil.copy(functionPairs);

        while (!copyPairs.isEmpty()) {
            int pair = copyPairs.choose();
            int y = getPairY(pair);
            rangeValues.add(y);
            copyPairs.remove(pair);
        }

        Set codomainCopy = SetUtil.copy(codomain);
        while (!codomainCopy.isEmpty()) {
            int element = codomainCopy.choose();
            if (!SetUtil.in(element, rangeValues)) {
                return false;
            }
            codomainCopy.remove(element);
        }

        return true;
    }

    /**
     * Obtiene la coordenada x de un par ordenado
     * Asumimos que el par está codificado como: x * MAX + y
     */
    private static int getPairX(int pair) {
        return pair / 10000;
    }

    /**
     * Obtiene la coordenada y de un par ordenado
     * Asumimos que el par está codificado como: x * MAX + y
     */
    private static int getPairY(int pair) {
        return pair % 10000;
    }

    /**
     * Crea un par ordenado codificado como un entero
     */
    public static int createPair(int x, int y) {
        return x * 10000 + y;
    }

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
     *                el par (x, f(x)), de forma que cada clave queda asociada a un único valor (en un contenedor).
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
}
