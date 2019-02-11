package pl.znamirowski.textanalyzer;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextAnalyzerTest {

    @Test
    public void shouldReturnEmptyMapWhenTextIsEmpty() {
        String text = "";
        TextAnalyzer analyzer = new TextAnalyzer(text);
        Map<String, Integer> result = analyzer.topTenWords();

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyMapWhenNoWordIsLongerThanFive() {
        String text = "this is some text with short words";
        TextAnalyzer analyzer = new TextAnalyzer(text);
        Map<String, Integer> result = analyzer.topTenWords();

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnWordsWithCounter() {
        String text = "a ababa abcabc b abcabc abcdefabcdef abcabc";
        TextAnalyzer analyzer = new TextAnalyzer(text);
        Map<String, Integer> result = analyzer.topTenWords();

        assertEquals(2, result.size());
        assertTrue(result.containsKey("abcabc"));
        assertEquals(3, result.get("abcabc").intValue());
        assertTrue(result.containsKey("abcdefabcdef"));
        assertEquals(1, result.get("abcdefabcdef").intValue());
    }
}
