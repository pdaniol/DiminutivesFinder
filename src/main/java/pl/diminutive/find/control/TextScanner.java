package pl.diminutive.find.control;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TextScanner {

        public Set<String> getChunkedText(String str){

        Set<String> allWords = new LinkedHashSet<>();
        if (!str.isEmpty()) {
//            str = str.replaceAll("[ó^A-Za-z0-9\\s+ąćęłńóśźż]", "");
            String[] words = str.toLowerCase().replaceAll("[^A-Za-z0-9\\s+ąćęłńóśźż]", "").split("\\s+");
            allWords.addAll(Arrays.stream(words).collect(Collectors.toSet()));
        }
        return allWords;
    }
}
