package pl.diminutive.find.control;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsDiminutiveChecker {

    static WebDictionaryParser webDictParser;

    private static Set<String> suffixes;
    static {
        suffixes = new HashSet<String>() {{
            addAll(Arrays.asList("ek", "ka", "ko", "ki", "eczek", "eczka", "eczko", "ik", "yk", "czyk", "iczek", "yczek",
                    "ak", "aczek", "aczyk", "uk", "ch", "cha", "iszek", "aszek", "uszek", "uszka", "uszko", "ulek", "oszek", "uszczek",
                    "yszek", "czko", "czka", "ączko", "eńko", "etko", "ułka", "yczka", "iczka", "iczko", "uchna", "uś",
                    "usia", "cia", "iś", "yś", "ś", "ia", "ik", "iku", "sikiem", "ia", "ul", "ula", "ulo", "oś", "aś", "uń", "unia",
                    "unio", "as", "isz", "osz", "usz", "ina", "ec", "ica", "ic "));
//   Sufiksy przymiotnikowe i przysłówkowe
            addAll(Arrays.asList("eńki", "eńka", "eńkie", "enieńki", "enieńka", "enieńkie", "uni", "unia", "unio",
                    "usi", "usia", "usio", "utki", "utka", "utkie", "uteczki", "uteczka", "uteńkie", "utuchny", "utuchna",
                    "utuchne", "uty", "uta", "utenieczki", "utenieczka", "utenieczkie", "ućki", "ućka", "ućkie", "uczki",
                    "uczka", "uczkie", "uśki", "uśka", "uśkie", "ylki", "ylka", "ylkie", "uchny", "uchna", "uchne"));

        }};
    }

    public IsDiminutiveChecker(){
        this.webDictParser = new WebDictionaryParser();
    }
    public boolean isDiminutive(String word, Set<String> diminutives) {
        if (diminutives.contains(word)) {
            return true;
        }
        if (isDiminutiveItself(word)) {
            return false;
        }
        Optional<String> foundSuffix = hasDiminutiveSuffix(word);
        if (foundSuffix.isPresent() && webDictParser.hasInformationAboutDiminutiveInDictionary(word)) {
            diminutives.add(word);
            return true;
        }
        return false;
    }

    private boolean isDiminutiveItself(String word) {
        Pattern p = Pattern.compile(".*zdrobni.*");
        Matcher m = p.matcher(word);
        return m.matches();
    }

    private Optional<String> hasDiminutiveSuffix(String word) {
        return suffixes.stream()
                .filter(s -> s.length() <= word.length())
                .filter(s -> {
                    boolean hasSuffix = s.equals(word.substring(word.length() - s.length()));
                    return hasSuffix;
                }).findFirst();
    }
}
