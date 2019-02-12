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

    private int countSentences(String text) {
        TextAnalyzer analyzer = new TextAnalyzer(text);
        return analyzer.countSentences();
    }

    @Test
    public void shouldFindNoSentencesWhenTextIsEmpty() {
        assertEquals(0, countSentences(""));
    }

    @Test
    public void shouldFindNoSentencesWhenThereAreNoLetters() {
        assertEquals(0, countSentences("  .. .,. !  ? , . ()"));
    }

    @Test
    public void shouldFindNoSentenceWhenThereIsNoStopChar() {
        String text = "aa bb cccccccc dd, eee; ffff'rr pppp-eeee (aaax)";
        assertEquals(0, countSentences(text));
    }

    @Test
    public void shouldFindOneSentence() {
        String text = "aaa beee, z pppp uuuu\n\r.rrrr tttt";
        assertEquals(1, countSentences(text));
    }

    @Test
    public void shouldFindThreeSentences() {
        String text = "a.bb1234?! ppp uuu! &%$ ( ,).";
        assertEquals(3, countSentences(text));
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

}
