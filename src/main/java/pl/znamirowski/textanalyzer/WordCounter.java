package pl.znamirowski.textanalyzer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    private final static String regex = "[a-zA-Z]+(?>['-]?[a-zA-Z]+)?";
    private final static Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    public static Map<String, Integer> topTenWords(String text) {
        if (text == null)
            throw new NullPointerException();

        Map<String, Integer> topTen = splitAndAggregateWords(text);
        topTen = findAndSortTenMostPopularWords(topTen);

        return topTen;
    }

    private static Map<String, Integer> findAndSortTenMostPopularWords(Map<String, Integer> words) {
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(words.entrySet());
        wordList.sort(Map.Entry.comparingByValue());
        Collections.reverse(wordList);

        Map<String, Integer> popularWords = new LinkedHashMap<>();
        int wordsToAdd = wordList.size() > 10 ? 10 : wordList.size();

        for (int i = 0; i < wordsToAdd; ++i)
            popularWords.put(wordList.get(i).getKey(), wordList.get(i).getValue());

        return popularWords;
    }

    private static Map<String, Integer> splitAndAggregateWords(String text) {
        Matcher matcher = pattern.matcher(text);
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
