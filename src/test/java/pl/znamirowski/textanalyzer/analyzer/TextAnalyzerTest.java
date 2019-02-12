package pl.znamirowski.textanalyzer.analyzer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import pl.znamirowski.textanalyzer.analyzer.TextAnalyzer;

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
        return new TextAnalyzer(text).countCharacters();
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

    private int countNonAlphaChars(String text) {
        return new TextAnalyzer(text).countNonAlphaChars();
    }

    @Test
    public void shouldFindZeroNonAlphabeticCharsWhenTextIsEmpty() {
        assertEquals(0, countNonAlphaChars(""));
    }

    @Test
    public void shouldFindZeroNonAlphabeticCharsWhenThereAreOnlyLetters() {
        assertEquals(0, countNonAlphaChars("AbbbccXxYyZzqqQ"));
    }

    @Test
    public void shouldFindAllFiveNonAlphabeticChars() {
        assertEquals(5, countNonAlphaChars("ZZ 123 abc"));
    }

    @Test
    public void shouldFindThreeNonAlphabeticChars() {
        assertEquals(3, countNonAlphaChars("_\r?"));
    }

    @Test
    public void shouldAlwaysReturnEqualNumber() {
        TextAnalyzer analyzer = new TextAnalyzer("Bbb pp. Aa ii, eee - ii? O!");
        int number = analyzer.countSentences();

        for (int i = 0; i < 100; i++)
            assertEquals(number, analyzer.countSentences());
    }
}
