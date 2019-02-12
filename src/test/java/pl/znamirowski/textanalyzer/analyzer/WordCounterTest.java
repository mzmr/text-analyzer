package pl.znamirowski.textanalyzer.analyzer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordCounterTest {

    private boolean createdMapIsEmpty(String text) {
        return WordCounter.topTenWords(text).isEmpty();
    }

    @Test
    public void shouldReturnEmptyMapWhenTextIsEmpty() {
        assertTrue(createdMapIsEmpty(""));
    }

    @Test
    public void shouldReturnEmptyMapWhenNoWordIsLongerThanFive() {
        assertTrue(createdMapIsEmpty("this is some text with short words"));
    }

    @Test
    public void shouldReturnWordsWithCounter() {
        String text = "a ababa abcabc b abcabc abcdefabcdef abcabc";
        Map<String, Integer> result = WordCounter.topTenWords(text);

        assertEquals(2, result.size());
        assertTrue(result.containsKey("abcabc"));
        assertEquals(3, result.get("abcabc").intValue());
        assertTrue(result.containsKey("abcdefabcdef"));
        assertEquals(1, result.get("abcdefabcdef").intValue());
    }

    @Test
    public void shouldReturnTenWords() {
        String text = "aaaaaa bbbbbb cccccc dddddd eeeeee ffffff gggggg "+
                "hhhhhh iiiiii jjjjjj kkkkkk llllll mmmmmm nnnnnn";

        assertEquals(10, WordCounter.topTenWords(text).size());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenTextIsNull() {
        WordCounter.topTenWords(null);
    }

    @Test
    public void shouldIgnoreNonAlphabeticalCharacters() {
        // inner "-" and "'" aren't ignored only if they're surrounded by alphabetical characters

        String text = "-aaabbb cccddd. aaabbb, !@#-$%^ aaa*bbb aaa-bbb (cccddd) qwer'ty";
        Map<String, Integer> result = WordCounter.topTenWords(text);

        assertEquals(4, result.size());
        assertTrue(result.containsKey("aaabbb"));
        assertEquals(2, result.get("aaabbb").intValue());
        assertTrue(result.containsKey("cccddd"));
        assertEquals(2, result.get("cccddd").intValue());
        assertTrue(result.containsKey("qwer'ty"));
        assertEquals(1, result.get("qwer'ty").intValue());
    }

    @Test
    public void shouldReturnTenMostPopularWords() {
        Map<String, Integer> wordsToAppend = createSampleMapOfWords();
        String text = createSampleText(wordsToAppend);

        Stream.of("ttttttt", "aaaa", "b").forEach(wordsToAppend::remove);
        Map<String, Integer> result = WordCounter.topTenWords(text);

        assertEquals(10, result.size());

        for (Map.Entry<String, Integer> wordEntry : wordsToAppend.entrySet()) {
            String word = wordEntry.getKey();
            Integer number = wordEntry.getValue();

            assertTrue(result.containsKey(word));
            assertEquals(number, result.get(word));
        }
    }

    private Map<String, Integer> createSampleMapOfWords() {
        Map<String, Integer> words = new HashMap<>();
        words.put("ttttttt", 3);  // too few
        words.put("sssssss", 4);
        words.put("llllllll", 10);
        words.put("cccccc", 11);
        words.put("wwwwwwww", 13);
        words.put("aaaa", 16);  // too short
        words.put("hhhhhh", 17);
        words.put("rrrrrr", 18);
        words.put("xxxxxxx", 19);
        words.put("b", 22);  // too short
        words.put("bbbbbbb", 24);
        words.put("yyyyyyyyy", 26);
        words.put("eeeeeee", 31);
        return words;
    }

    private String createSampleText(Map<String,Integer> wordsToAppend) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> wordEntry : wordsToAppend.entrySet()) {
            sb.append(StringUtils.repeat(wordEntry.getKey()," ", wordEntry.getValue()));
            sb.append(' ');
        }

        return sb.toString();
    }

    @Test
    public void shouldConvertToLowerCase() {
        String text = "LONGWORD longword LoNgWoRd";
        Map<String, Integer> result = WordCounter.topTenWords(text);

        assertEquals(1, result.size());
        assertTrue(result.containsKey("longword"));
        assertEquals(3, result.get("longword").intValue());
    }
}
