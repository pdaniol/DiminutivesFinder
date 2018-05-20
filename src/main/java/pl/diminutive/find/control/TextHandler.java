package pl.diminutive.find.control;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TextHandler {

    private static Set<String> diminutives = new LinkedHashSet<>();
    private static Set<String> allWords = new LinkedHashSet<>();
    private static Set<String> wordWithoutDiminutives = new LinkedHashSet<>();

    public Set<String> getDiminutives() {
        return diminutives;
    }

    public Set<String> getWordWithoutDiminutives() {
        return wordWithoutDiminutives;
    }

    public void processText(String text) {
        IsDiminutiveChecker checker = new IsDiminutiveChecker();
        TextScanner textScanner = new TextScanner();
        allWords.addAll(textScanner.getChunkedText(text));
        Set<String> diminutiveWords = allWords.stream().filter(word -> checker.isDiminutive(word, diminutives)).collect(Collectors.toSet());

        wordWithoutDiminutives = allWords;
        wordWithoutDiminutives.removeAll(diminutiveWords);
    }
}
