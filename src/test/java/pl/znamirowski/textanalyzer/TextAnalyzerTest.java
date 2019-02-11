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

        assertTrue(analyzer.topTenWords().isEmpty());
    }

    @Test
    public void shouldReturnEmptyMapWhenNoWordIsLongerThanFive() {
        String text = "this is some text with short words";
        TextAnalyzer analyzer = new TextAnalyzer(text);

        assertTrue(analyzer.topTenWords().isEmpty());
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

    @Test
    public void shouldReturnTenWords() {
        String text = "aaaaaa bbbbbb cccccc dddddd eeeeee ffffff gggggg "+
                "hhhhhh iiiiii jjjjjj kkkkkk llllll mmmmmm nnnnnn";
        TextAnalyzer analyzer = new TextAnalyzer(text);

        assertEquals(10, analyzer.topTenWords().size());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenTextIsNull() {
        new TextAnalyzer(null);
    }
}
