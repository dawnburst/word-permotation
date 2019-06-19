package com.dawn.wordpermutation.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.paukov.combinatorics3.Generator;
import org.springframework.stereotype.Service;

@Service
public class WordPermutationService {

  public Set<String> loadDictionaryToSet(File dictionary) {

    Set<String> wordsSet = new HashSet<>();

    BufferedReader reader;
    try {
      reader =
          new BufferedReader(
              new FileReader(dictionary));
      String line = reader.readLine();
      while (line != null) {
        System.out.println(line);
        wordsSet.add(line);
        // read next line
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(wordsSet.size());
    return wordsSet;
  }

  public List<String> perms(String string) {

    List<String> result = new ArrayList<String>();
    char[] values = string.toCharArray();
    for (int width = 1; width <= values.length; width++) { // for every length
      int stack[] = new int[width];
      for (int i = 0; i < stack.length; i++) { // start from a specific point without duplicates
        stack[i] = stack.length - i - 1;
      }
      int position = 0;
      while (position < width) {

        position = 0;
        StringBuilder sb = new StringBuilder();
        while (position < width) { // build the string
          sb.append(values[stack[position]]);
          position++;
        }
        result.add(sb.toString());
        position = 0;
        while (position < width) {
          if (stack[position] < values.length - 1) {
            stack[position]++;
            if (!containsDuplicate(stack)) {
              break;
            } else {
              position = 0;
            }
          } else {
            stack[position] = 0;
            position++;
          }
        }
      }
    }
    return result;
  }

  private boolean containsDuplicate(int[] stack) {
    for (int i = 0; i < stack.length; i++) {
      for (int j = 0; j < stack.length; j++) {
        if (stack[i] == stack[j] && i != j) {
          return true;
        }
      }
    }
    return false;
  }


  public Set<String> getWordCombination(String word) {

    ImmutableList<Character> characters = Lists.charactersOf(word);

    Set<String> listOfCombination = IntStream.range(2, characters.size() + 1)
        .mapToObj(i -> Generator
            .combination(characters)
            .simple(i)
            .stream()
            .map(list -> list
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining()))
            .collect(Collectors.toList()))
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());

    listOfCombination.forEach(System.out::println);

    System.out.println("Numbers of combinations: " + listOfCombination.size());
    return listOfCombination;
  }
}
