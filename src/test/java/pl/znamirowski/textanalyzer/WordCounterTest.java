package pl.znamirowski.textanalyzer;

import org.junit.Test;

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
}
