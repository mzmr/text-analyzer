package pl.znamirowski.textanalyzer.analyzer;

import java.util.LinkedHashMap;
import java.util.Map;

public class LetterCounter {

    private static final Map<Character, Integer> _letters;

    static {
        _letters = new LinkedHashMap<>();

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

        return letters;
    }

    private static Map<Character, Integer> getLettersMap() {
        return new LinkedHashMap<>(_letters);
    }

}
