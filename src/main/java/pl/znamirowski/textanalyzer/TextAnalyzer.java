package pl.znamirowski.textanalyzer;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class TextAnalyzer {

    private final String text;
    private Map<String, Integer> topTenWords;
    private Integer sentenceCount;
    private Integer whitespaceCount;
    private Integer nonAlphabeticCount;

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
        if (sentenceCount == null)
            sentenceCount = SentenceCounter.countSentences(text);

        return sentenceCount;
    }

    public int countWhitespaceChars() {
        if (whitespaceCount != null)
            return whitespaceCount;

        char[] whites = { ' ', '\t', '\n', '\f', '\r' };
        int counter = 0;

        for (char c : whites)
            counter += StringUtils.countMatches(text, c);

        whitespaceCount = counter;
        return whitespaceCount;
    }

    public int countCharacters() {
        return text.length();
    }

    public int countNonAlphaChars() {
        if (nonAlphabeticCount != null)
            return nonAlphabeticCount;

        int letters = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c))
                ++letters;
        }

        nonAlphabeticCount = text.length() - letters;
        return nonAlphabeticCount;
    }
}
