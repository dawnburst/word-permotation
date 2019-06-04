package com.dawn.wordpermutation.web.rest;

import com.dawn.wordpermutation.service.WordPermutationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WordPermutationResource {

    private final WordPermutationService wordPermutationService;

    @GetMapping("word-permutation")
    public List<String> getAllPermutation(@RequestParam String word) {
        return wordPermutationService.perms(word);
    }
}
