package pl.diminutive.find.control;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TextHandler {

    private final Set<String> diminutives = new LinkedHashSet<>();
    private final Set<String> allWords = new LinkedHashSet<>();
    private final Set<String> wordWithoutDiminutives = new LinkedHashSet<>();

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

        wordWithoutDiminutives.addAll(allWords);
        wordWithoutDiminutives.removeAll(diminutiveWords);
    }

    public void clear(){
        diminutives.clear();
        allWords.clear();
        wordWithoutDiminutives.clear();
    }
}
