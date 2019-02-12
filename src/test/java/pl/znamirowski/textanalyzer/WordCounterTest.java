package pl.znamirowski.textanalyzer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> wordsToAppend = new HashMap<>();
        wordsToAppend.put("ttttttt", 3);  // too few
        wordsToAppend.put("sssssss", 4);
        wordsToAppend.put("llllllll", 10);
        wordsToAppend.put("cccccc", 11);
        wordsToAppend.put("wwwwwwww", 13);
        wordsToAppend.put("aaaa", 16);  // too short
        wordsToAppend.put("hhhhhh", 17);
        wordsToAppend.put("rrrrrr", 18);
        wordsToAppend.put("xxxxxxx", 19);
        wordsToAppend.put("b", 22);  // too short
        wordsToAppend.put("bbbbbbb", 24);
        wordsToAppend.put("yyyyyyyyy", 26);
        wordsToAppend.put("eeeeeee", 31);

        for (Map.Entry<String, Integer> wordEntry : wordsToAppend.entrySet()) {
            for (int i = 0; i < wordEntry.getValue(); ++i) {
                sb.append(wordEntry.getKey());
                sb.append(" ");
            }
        }

        wordsToAppend.remove("ttttttt");
        wordsToAppend.remove("aaaa");
        wordsToAppend.remove("b");
        Map<String, Integer> result = WordCounter.topTenWords(sb.toString());

        assertEquals(10, result.size());

        for (Map.Entry<String, Integer> wordEntry : wordsToAppend.entrySet()) {
            String word = wordEntry.getKey();
            Integer number = wordEntry.getValue();

            assertTrue(result.containsKey(word));
            assertEquals(number, result.get(word));
        }
    }

}
