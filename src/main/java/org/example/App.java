package org.example;

import org.example.model.Dictionary;
import org.example.model.StaticDictionary;
import org.example.util.DictionaryUtil;

public class App {

    public static void main(String[] args) {
        Dictionary dictionary = new StaticDictionary();
        dictionary.add(1, 2);
        dictionary.add(2, 4);
        dictionary.add(8, 16);
        DictionaryUtil.print(dictionary);
    }

}
