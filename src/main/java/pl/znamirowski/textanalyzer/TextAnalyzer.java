package pl.znamirowski.textanalyzer;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {

    private final String text;
    private Map<String, Integer> topTenWords;

    public TextAnalyzer(String text) {
        if (text == null)
            throw new NullPointerException();

        this.text = text;
    }

    public Map<String, Integer> topTenWords() {
        if (topTenWords == null)
            topTenWords = WordCounter.topTenWords(text);

        return new LinkedHashMap<>(topTenWords);
    }

    public int countSentences() {
        final String regex = "[a-zA-Z]+[^.?!]*[.?!]";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(text);

        int count = 0;

        while (matcher.find())
            ++count;

        return count;
    }

    public int countWhitespaceChars() {
        char[] whites = { ' ', '\t', '\n', '\f', '\r' };
        int counter = 0;

        for (char c : whites)
            counter += StringUtils.countMatches(text, c);

        return counter;
    }

    public int countCharacters() {
        return text.length();
    }

}
