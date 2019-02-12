package pl.znamirowski.textanalyzer;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {

    private final String text;

    public TextAnalyzer(String text) {
        if (text == null)
            throw new NullPointerException();

        this.text = text;
    }

    public Map<String, Integer> topTenWords() {
        Map<String, Integer> topTen = splitAndAggregateWords(text);
        topTen = findAndSortTenMostPopularWords(topTen);
        
        return topTen;
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

    private Map<String, Integer> findAndSortTenMostPopularWords(Map<String, Integer> words) {
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(words.entrySet());
        wordList.sort(Map.Entry.comparingByValue());
        Collections.reverse(wordList);

        Map<String, Integer> popularWords = new LinkedHashMap<>();
        int wordsToAdd = wordList.size() > 10 ? 10 : wordList.size();

        for (int i = 0; i < wordsToAdd; ++i)
            popularWords.put(wordList.get(i).getKey(), wordList.get(i).getValue());

        return popularWords;
    }

    private Map<String, Integer> splitAndAggregateWords(String text) {
        final String regex = "[a-zA-Z]+(?>['-]?[a-zA-Z]+)?";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(text);

        Map<String, Integer> words = new HashMap<>();

        while (matcher.find()) {
            String word = matcher.group();

            if (word.length() <= 5)
                continue;

            Integer count = words.getOrDefault(word, 0);
            words.put(word, count + 1);
        }

        return words;
    }

}
