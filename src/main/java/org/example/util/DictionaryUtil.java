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
        Set keys1 = dict1.getKeys();
        Set keys2 = dict2.getKeys();

        if (!haveSameKeys(keys1, keys2)) {
            return false;
        }

        Set keysToCheck = copy(keys1);
        while (!keysToCheck.isEmpty()) {
            int key = keysToCheck.choose();
            keysToCheck.remove(key);

            List values1 =  dict1.get(key);
            List values2 =  dict2.get(key);

            if (values1.length() != values2.length()) {
                return false;
            }

            Set set1 = listToSet(values1);
            Set set2 = listToSet(values2);

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

    private static boolean haveSameValues(List values1, List values2) {
        if (values1.length() != values2.length()) {
            return false;
        }

        Set set1 = listToSet(values1);
        Set set2 = listToSet(values2);

        return SetUtil.equals(set1, set2);
    }

    private static Set copy(Set original) {
        return SetUtil.copy(original);
    }

    private static Set listToSet(List list) {
        Set set = new StaticSet();
        for (int i = 0; i < list.length(); i++) {
            set.add(list.get(i));
        }
        return set;
    }


}
