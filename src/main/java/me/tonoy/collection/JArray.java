package me.tonoy.collection;

import java.util.HashMap;

public class JArray {
    private static int getOccurrences(String source, String word) {
        HashMap<String, Integer> freq = new HashMap<>();
        String[] items = source.toLowerCase().replaceAll("\\.", "").split(" ");
        for (String item : items) {
            if (freq.containsKey(item)) {
                freq.put(item, freq.get(item) + 1);
            } else {
                freq.put(item, 1);
            }
        }
        return freq.getOrDefault(word, 0);
    }

    public static void main(String[] args) {
        String source = "Here is an example. Right here.";
        String word = "here";
        int result = getOccurrences(source, word);
        System.out.println(result);
    }

}