package pl.znamirowski.textanalyzer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TextAnalyzerTest {

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenTextIsNull() {
        new TextAnalyzer(null);
    }

    @Test
    public void shouldReturnCopiedWordMap() {
        TextAnalyzer analyzer = new TextAnalyzer("");
        Map<String, Integer> resultFirst = analyzer.topTenWords();
        resultFirst.put("x", 2);
        resultFirst.put("y", 3);
        Map<String, Integer> resultSecond = analyzer.topTenWords();

        assertEquals(0, resultSecond.size());
    }

    private int countWhites(String text) {
        TextAnalyzer analyzer = new TextAnalyzer(text);
        return analyzer.countWhitespaceChars();
    }

    @Test
    public void shouldFindNoWhitespaceCharsWhenTextIsEmpty() {
        assertEquals(0, countWhites(""));
    }

    @Test
    public void shouldFindNoWhitespace() {
        String text = "Aab()*!@#$,NJn11?<>.//\\\\";
        assertEquals(0, countWhites(text));
    }

    @Test
    public void shouldFindOneWhitespace() {
        assertEquals(1, countWhites(" "));
    }

    @Test
    public void shouldFindAllWhitespaceChars() {
        String text = "\r\n\r\f \t\txxx xx! \n";
        assertEquals(10, countWhites(text));
    }

    private int countCharacters(String text) {
        TextAnalyzer analyzer = new TextAnalyzer(text);
        return analyzer.countCharacters();
    }

    @Test
    public void shouldFindNoCharactersWhenTextIsEmpty() {
        assertEquals(0, countCharacters(""));
    }

    @Test
    public void shouldFindOneHundredCharacters() {
        String text = StringUtils.repeat("Word ", 20);
        assertEquals(100, countCharacters(text));
    }

    @Test
    public void shouldFindZeroNonAlphabeticCharsWhenTextIsEmpty() {
        TextAnalyzer analyzer = new TextAnalyzer("");
        assertEquals(0, analyzer.countNonAlphaChars());
    }

    @Test
    public void shouldFindZeroNonAlphabeticCharsWhenThereAreOnlyLetters() {
        TextAnalyzer analyzer = new TextAnalyzer("AbbbccXxYyZzqqQ");
        assertEquals(0, analyzer.countNonAlphaChars());
    }

    @Test
    public void shouldFindAllFiveNonAlphabeticChars() {
        TextAnalyzer analyzer = new TextAnalyzer("ZZ 123 abc");
        assertEquals(5, analyzer.countNonAlphaChars());
    }

    @Test
    public void shouldFindThreeNonAlphabericChars() {
        TextAnalyzer analyzer = new TextAnalyzer("_\r?");
        assertEquals(3, analyzer.countNonAlphaChars());
    }
}
