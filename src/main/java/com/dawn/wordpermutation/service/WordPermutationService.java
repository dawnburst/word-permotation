package com.dawn.wordpermutation.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
}
