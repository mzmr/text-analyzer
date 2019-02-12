package pl.znamirowski.textanalyzer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextAnalyzerTest {

    private boolean createdMapIsEmpty(String text) {
        TextAnalyzer analyzer = new TextAnalyzer(text);
        return analyzer.topTenWords().isEmpty();
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

    @Test
    public void shouldIgnoreNonAlphabeticalCharacters() {
        // inner "-" and "'" aren't ignored only if they're surrounded by alphabetical characters

        String text = "-aaabbb cccddd. aaabbb, !@#-$%^ aaa*bbb aaa-bbb (cccddd) qwer'ty";
        TextAnalyzer analyzer = new TextAnalyzer(text);
        Map<String, Integer> result = analyzer.topTenWords();

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
        TextAnalyzer analyzer = new TextAnalyzer(sb.toString());
        Map<String, Integer> result = analyzer.topTenWords();

        assertEquals(10, result.size());

        for (Map.Entry<String, Integer> wordEntry : wordsToAppend.entrySet()) {
            String word = wordEntry.getKey();
            Integer number = wordEntry.getValue();

            assertTrue(result.containsKey(word));
            assertEquals(number, result.get(word));
        }
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
