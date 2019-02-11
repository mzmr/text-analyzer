package pl.znamirowski.textanalyzer;

import java.util.*;

public class TextAnalyzer {

    private final String text;

    public TextAnalyzer(String text) {
        if (text == null)
            throw new NullPointerException();

        this.text = text;
    }

    public Map<String, Integer> topTenWords() {
        Map<String, Integer> topTen = new HashMap<>();

        for (String word : text.split(" ")) {
            if (word.length() <= 5)
                continue;

            Integer count = topTen.getOrDefault(word, 0);
            topTen.put(word, count + 1);
        }

        topTen = findAndSortTenMostPopularWords(topTen);
        
        return topTen;
    }

    private Map<String, Integer> findAndSortTenMostPopularWords(Map<String, Integer> words) {
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(words.entrySet());
        wordList.sort(Map.Entry.comparingByValue());

        Map<String, Integer> popularWords = new LinkedHashMap<>();
        int wordsToAdd = wordList.size() > 10 ? 10 : wordList.size();

        for (int i = 0; i < wordsToAdd; ++i)
            popularWords.put(wordList.get(i).getKey(), wordList.get(i).getValue());

        return popularWords;
    }
}
