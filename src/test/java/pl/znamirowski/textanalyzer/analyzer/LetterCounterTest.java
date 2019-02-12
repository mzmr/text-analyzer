package pl.znamirowski.textanalyzer.analyzer;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LetterCounterTest {

    @Test
    public void shouldReturnMapWithAllLettersWithZeroValues() {
        Map<Character, Integer> result = LetterCounter.countLetters("");

        assertEquals(26, result.size());

        for (char letter = 'a'; letter <= 'z'; ++letter) {
            assertTrue(result.containsKey(letter));
            assertEquals(0, result.get(letter).intValue());
        }
    }

    @Test
    public void shouldCountLettersAndPutResultIntoMap() {
        Map<Character, Integer> result = LetterCounter.countLetters("rar");

        assertEquals(26, result.size());
        assertTrue(result.containsKey('r'));
        assertEquals(2, result.get('r').intValue());
        assertTrue(result.containsKey('a'));
        assertEquals(1, result.get('a').intValue());
    }

    @Test
    public void shouldConvertLettersToLowerCase() {
        Map<Character, Integer> result = LetterCounter.countLetters("Bbb Z abzABZ");

        assertEquals(26, result.size());
        assertTrue(result.containsKey('a'));
        assertEquals(2, result.get('a').intValue());
        assertTrue(result.containsKey('b'));
        assertEquals(5, result.get('b').intValue());
        assertTrue(result.containsKey('z'));
        assertEquals(3, result.get('z').intValue());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenTextIsNull() {
        LetterCounter.countLetters(null);
    }
}
