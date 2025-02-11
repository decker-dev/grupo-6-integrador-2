package org.example.util;

import org.example.model.*;


public class DictionaryUtil {

    public static void print(Dictionary dictionary) {
        Set keys = dictionary.getKeys();

        while(!keys.isEmpty()) {
            int key = keys.choose();
            int value = dictionary.get(key);
            System.out.println("key: " + key + ", value: " + value);
            keys.remove(key);
        }
    }
    public static boolean equals(MultipleDictionary dict1, MultipleDictionary dict2) {
        // Primero verificamos que tengan las mismas claves
        Set keys1 = dict1.getKeys();
        Set keys2 = dict2.getKeys();

        if (!haveSameKeys(keys1, keys2)) {
            return false;
        }

        // Para cada clave, verificamos que tengan los mismos valores
        Set keysToCheck = copy(keys1);
        while (!keysToCheck.isEmpty()) {
            int key = keysToCheck.choose();
            keysToCheck.remove(key);

            // Obtenemos las listas de valores para la clave actual
            List values1 =  dict1.get(key);
            List values2 =  dict2.get(key);

            // Si tienen diferente cantidad de valores, no son iguales
            if (values1.length() != values2.length()) {
                return false;
            }

            // Convertimos las listas a conjuntos para comparar sin importar el orden
            Set set1 = listToSet(values1);
            Set set2 = listToSet(values2);

            // Verificamos que tengan los mismos valores
            if (!haveSameValues(set1, set2)) {
                return false;
            }
        }

        return true;
    }
    private static boolean haveSameKeys(Set keys1, Set keys2) {
        if (SetUtil.size(keys1) != SetUtil.size(keys2)) {
            return false;
        }

        Set copyKeys1 = SetUtil.copy(keys1);
        while (!copyKeys1.isEmpty()) {
            int key = copyKeys1.choose();
            if (!SetUtil.in(key, keys2)) {
                return false;
            }
            copyKeys1.remove(key);
        }
        return true;
    }

    private static boolean haveSameValues(Set values1, Set values2) {
        if (SetUtil.size(values1) != SetUtil.size(values2)) {
            return false;
        }

        Set copyValues1 = SetUtil.copy(values1);
        while (!copyValues1.isEmpty()) {
            int value = copyValues1.choose();
            if (!SetUtil.in(value, values2)) {
                return false;
            }
            copyValues1.remove(value);
        }
        return true;
    }

    private static Set copy(Set original) {
        return SetUtil.copy(original);
    }
    private static Set listToSet(List list) {
        Set set = new StaticSet();
        for (int value : list) {
            set.add(value);
        }
        return set;
    }

}
