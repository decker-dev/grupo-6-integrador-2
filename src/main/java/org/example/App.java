package org.example;

import org.example.model.*;
import org.example.model.Set;
import org.example.util.*;
import org.example.model.Dictionary;
import org.example.model.StaticDictionary;

public class App {

    public static void main(String[] args) {
        System.out.println("==== PRUEBA DE LsAS TABLAS DE VERDAD ====\n");

        probarTDA(new UnaryBinaryLogicTruthTable(), "UnaryBinaryLogicTruthTable");
        probarTDA(new BinaryBinaryLogicTruthTable(), "BinaryBinaryLogicTruthTable");
        probarTDA(new UnaryTernaryLogicTruthTable(), "UnaryTernaryLogicTruthTable");
        probarTDA(new BinaryTernaryLogicTruthTable(), "BinaryTernaryLogicTruthTable");

        System.out.println("\n==== PRUEBA DE DICTIONARY ====\n");

        Dictionary dictionary = new StaticDictionary();
        dictionary.add(1, 2);
        dictionary.add(2, 4);
        dictionary.add(8, 16);
        DictionaryUtil.print(dictionary);
    }

    public static void probarTDA(LogicTruthTable tda, String nombre){}
}
