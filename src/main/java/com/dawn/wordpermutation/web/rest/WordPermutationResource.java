package com.dawn.wordpermutation.web.rest;

import com.dawn.wordpermutation.service.WordPermutationService;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
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

  private File dictionary = new File(
      "/home/dawn/IdeaProjects/word-permutation/src/main/resources/new_dict-he.dat");
  private Set<String> dictionaryToSet = null;

  @PostConstruct
  public void initDictionary() {
    dictionaryToSet =
        wordPermutationService.loadDictionaryToSet(dictionary);
  }

  @GetMapping("word-permutation")
  public Set<String> getAllPermutation(@RequestParam String word) {

    Set<String> perms = wordPermutationService.getWordCombination(word);

//    perms
//        .stream()
//        .filter(str -> str.length() > 1)
//        .filter(dictionaryToSet::contains)
//        .sorted()
//        .collect(Collectors.toCollection(LinkedHashSet::new));

    return perms
        .stream()
        .filter(str -> str.length() > 1)
        .filter(dictionaryToSet::contains)
        .sorted()
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  @GetMapping("combination")
  public Set<String> getCombination(@RequestParam String word) {
    return wordPermutationService.getWordCombination(word);
  }
}
