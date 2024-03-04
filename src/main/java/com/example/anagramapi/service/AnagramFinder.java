package com.example.anagramapi.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnagramFinder {
    private final Map<String, List<String>> anagramsMap = new HashMap<>();

    public void addWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        anagramsMap.computeIfAbsent(sorted, k -> new ArrayList<>()).add(word);
    }

    public List<List<String>> findAnagrams() {
        List<List<String>> result = new ArrayList<>();
        for (List<String> group : anagramsMap.values()) {
            if (group.size() > 1) {
                result.add(new ArrayList<>(group));
            }
        }
        return result;
    }

    public List<String> getAnagramsForWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        List<String> anagrams = anagramsMap.getOrDefault(sorted, Collections.emptyList());
        // Return a copy to prevent external modification
        return new ArrayList<>(anagrams);
    }
}
