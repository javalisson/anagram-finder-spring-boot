package com.example.anagramapi.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AnagramFinderTests {

    private AnagramFinder anagramFinder;

    @BeforeEach
    void setUp() {
        anagramFinder = new AnagramFinder();
    }

    @Test
    void testAddWordAndFindAnagrams() {
        anagramFinder.addWord("listen");
        anagramFinder.addWord("silent");
        anagramFinder.addWord("enlist");

        List<List<String>> anagrams = anagramFinder.findAnagrams();

        // Assuming we want at least one group of anagrams with these words
        assertTrue(anagrams.stream().anyMatch(group -> group.containsAll(List.of("listen", "silent", "enlist"))));
    }

    @Test
    void testGetAnagramsForWord() {
        anagramFinder.addWord("listen");
        anagramFinder.addWord("silent");
        anagramFinder.addWord("enlist");

        List<String> anagramsForListen = anagramFinder.getAnagramsForWord("listen");

        assertEquals(3, anagramsForListen.size());
        assertTrue(anagramsForListen.containsAll(List.of("listen", "silent", "enlist")));
    }

    @Test
    void testNoAnagramsFound() {
        anagramFinder.addWord("binary");
        anagramFinder.addWord("brainy");

        List<String> anagramsForBinary = anagramFinder.getAnagramsForWord("binary");

        assertEquals(2, anagramsForBinary.size());
        assertTrue(anagramsForBinary.containsAll(List.of("binary", "brainy")));

        List<String> anagramsForUnknown = anagramFinder.getAnagramsForWord("unknown");

        assertTrue(anagramsForUnknown.isEmpty());
    }

    @Test
    void testOnlyGroupsOfTwoOrMoreAreReturned() {
        anagramFinder.addWord("listen");
        anagramFinder.addWord("silent");
        // This word doesn't form an anagram with the above words
        anagramFinder.addWord("example");

        List<List<String>> anagrams = anagramFinder.findAnagrams();

        // Ensure only one group is returned, and it's the correct one
        assertEquals(1, anagrams.size());
        assertTrue(anagrams.get(0).containsAll(List.of("listen", "silent")));
    }
}
