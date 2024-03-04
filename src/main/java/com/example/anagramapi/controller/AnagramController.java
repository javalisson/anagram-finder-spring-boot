package com.example.anagramapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.anagramapi.service.AnagramFinder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnagramController {

    @Autowired
    private AnagramFinder anagramFinder;

    @PostMapping("/addword")
    public void addWord(@RequestBody String word) {
        anagramFinder.addWord(word);
    }

    @GetMapping("/allanagrams")
    public List<List<String>> getAllAnagrams() {
        return anagramFinder.findAnagrams();
    }

    @GetMapping("/anagrams")
    public List<String> getAnagramsForWord(@RequestParam String word) {
        return anagramFinder.getAnagramsForWord(word);
    }
}
