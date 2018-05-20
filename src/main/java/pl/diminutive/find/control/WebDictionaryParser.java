package pl.diminutive.find.control;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDictionaryParser {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    public boolean hasInformationAboutDiminutiveInDictionary(String word){
        Elements wikislownikElements = readWikiPageContent(word);
        Elements sjpElements = readSjpPageContent(word);

        return hasWordZdrobn(wikislownikElements) || hasWordZdrobnienie(sjpElements) || hasWordZdrobniale(sjpElements);
    }

    private Elements readSjpPageContent(String word) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://sjp.pl/" + word).get();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Problem while connectiong with sjp online dictionary, caused: " + e.getMessage());
        }
        return doc.select("body > p")
                .attr("style", "margin: .5em 0; font: medium/1.4 sans-serif; max-width: 32em; ");
    }

    private Elements readWikiPageContent(String word) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://pl.wiktionary.org/wiki/" + word).get();
        } catch (org.jsoup.HttpStatusException e) {
            logger.log(Level.WARNING, "Problem while connectiong with wikisłownik online dictionary, status code: " +
                    e.getStatusCode() + ", word: " + word + ", caused: " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.WARNING, "Problem while connectiong with wikisłownik online dictionary, caused: " + e.getMessage());
        }
        if(!Objects.isNull(doc)){
            return doc.select("body > div")
                    .attr("id", "mw-content-text")
                    .select("div")
                    .attr("class", "mw-parser-output")
                    .select("dfn")
                    .attr("class", "lang-pl fldn-3 fldt-znaczenia")
                    .select("span")
                    .attr("class", "short-wrapper lang-pl fldn-3 fldt-znaczenia")
                    .attr("title", "zdrobniale – zdrobniale, zdrobnienie")
                    .attr("data-expanded", "zdrobniale");
        }
        return null;
    }

    private boolean hasWordZdrobniale( Elements pElements) {
        if(!Objects.isNull(pElements)){
            return !pElements.select(":containsOwn(zdrobniale)").isEmpty();
        }
        return false;
    }

    private boolean hasWordZdrobnienie( Elements pElements) {
        if(!Objects.isNull(pElements)){
            return !pElements.select(":containsOwn(zdrobnienie)").isEmpty();
        }
        return false;
    }

    private static boolean hasWordZdrobn(Elements pElements) {
        if(!Objects.isNull(pElements)){
            return !pElements.select(":containsOwn(zdrobn.)").isEmpty();
        }
        return false;
    }
}
