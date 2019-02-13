package pl.znamirowski.textanalyzer.analyzer;

import java.util.*;

public class LetterCounter {

    private static final Map<Character, Integer> _letters;

    static {
        _letters = new HashMap<>();

        for (char letter = 'a'; letter <= 'z'; ++letter)
            _letters.put(letter, 0);
    }

    static Map<Character, Integer> countLetters(String text) {
        if (text == null)
            throw new NullPointerException();

        Map<Character, Integer> letters = getLettersMap();

        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));

            Integer number = letters.get(c);

            if (number == null)
                continue;

            letters.put(c, number + 1);
        }

        return sortByValue(letters);
    }

    private static Map<Character, Integer> getLettersMap() {
        return new HashMap<>(_letters);
    }

    private static Map<Character, Integer> sortByValue(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> letterList = new ArrayList<>(map.entrySet());
        letterList.sort(Map.Entry.comparingByValue());
        Collections.reverse(letterList);

        Map<Character, Integer> sortedLetters = new LinkedHashMap<>();
        letterList.forEach(x -> sortedLetters.put(x.getKey(), x.getValue()));
        return sortedLetters;
    }

}
