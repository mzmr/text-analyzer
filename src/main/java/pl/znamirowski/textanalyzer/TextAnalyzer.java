package pl.znamirowski.textanalyzer;

import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer {

    private final String text;

    public TextAnalyzer(String text) {
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
        
        return topTen;
    }
}
