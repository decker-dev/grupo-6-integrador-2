package org.example.util;

import org.example.model.Set;
import org.example.model.SetOfSet;
import org.example.model.StaticSet;
import org.example.model.StaticSetOfSet;

public class SetUtil {

    private SetUtil() {

    }

    public static Set copy(Set set) {
        Set copy = new StaticSet();
        Set aux = new StaticSet();

        while(!set.isEmpty()) {
            int element = set.choose();
            copy.add(element);
            aux.add(element);
            set.remove(element);
        }

        while(!aux.isEmpty()) {
            int element = aux.choose();
            set.add(element);
            aux.remove(element);
        }

        return copy;
    }

    public static Set intersection(Set set, Set set2) {
        Set copy = copy(set);
        Set intersection = new StaticSet();

        while(!copy.isEmpty()) {
            int element = copy.choose();
            if(in(element, set2)) {
                intersection.add(element);
            }
            copy.remove(element);
        }
        return intersection;
    }

    public static boolean in(int element, Set set) {
        Set copy = copy(set);
        while(!copy.isEmpty()) {
            int aux = copy.choose();
            if(aux == element) {
                return true;
            }
            copy.remove(aux);
        }
        return false;
    }

    public static Set difference(Set set, Set set2) {
        Set copy = copy(set);
        Set result = new StaticSet();

        while(!copy.isEmpty()) {
            int element = copy.choose();
                if(!in(element, set2)) {
                    result.add(element);
                }
            copy.remove(element);
        }

        return result;
    }

    public static Set union(Set set, Set set2) {
        Set copy = copy(set);
        Set copy2 = copy(set2);
        Set result = new StaticSet();

        while(!copy.isEmpty()) {
            int element = copy.choose();
            result.add(element);
            copy.remove(element);
        }

        while(!copy2.isEmpty()) {
            int element = copy2.choose();
            result.add(element);
            copy2.remove(element);
        }

        return result;
    }

    public static Set symmetricDifference(Set set, Set set2) {
        return difference(union(set, set2), intersection(set, set2));
    }

    public static boolean subseteq(Set set, Set set2) {
        Set copy = copy(set);
        while(!copy.isEmpty()) {
            int element = copy.choose();
            if(!in(element, set2)) {
                return false;
            }
            copy.remove(element);
        }
        return true;
    }

    public static int size(Set set) {
        Set copy = copy(set);
        int total = 0;
        while(!copy.isEmpty()) {
            int element = copy.choose();
            total++;
            copy.remove(element);
        }
        return total;
    }

    public static boolean equals(Set set, Set set2) {
        return subseteq(set, set2) && size(set) == size(set2);
    }

    public static SetOfSet parts(Set set) {
        SetOfSet result = new StaticSetOfSet();
        int[] array = map(set);
        int size = size(set);

        int count = (int) Math.pow(2, size);

        for(int i = 0; i < count; i++) {
            boolean[] pArray = map(i, count);
            result.add(map(array, pArray));
        }
        return result;
    }

    private static Set map(int[] array, boolean[] pArray) {
        Set result = new StaticSet();
        for(int i = 0; i < array.length; i++) {
            if(pArray[i]) {
                result.add(array[i]);
            }
        }
        return result;
    }

    private static int[] map(Set set) {
        int[] result = new int[size(set)];
        for(int i = 0; i < result.length; i++) {
            int element = set.choose();
            result[i] = element;
            set.remove(element);
        }
        return result;
    }

    private static boolean[] map(int a, int length) {
        if(length == 0 || a < 0) {
            throw new RuntimeException();
        }
        boolean[] result = new boolean[length];
        int i = 0;
        int aux = a;
        while(aux != 0) {
            if(i >= length) {
                throw new RuntimeException();
            }
            result[i] = aux % 2 == 1;
            aux = aux / 2;
            i++;
        }
        return result;
    }

}
