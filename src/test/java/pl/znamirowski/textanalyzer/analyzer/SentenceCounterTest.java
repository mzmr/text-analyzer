package pl.znamirowski.textanalyzer.analyzer;

import org.junit.Test;
import pl.znamirowski.textanalyzer.analyzer.SentenceCounter;

import static org.junit.Assert.assertEquals;

public class SentenceCounterTest {

    @Test
    public void shouldFindNoSentencesWhenTextIsEmpty() {
        assertEquals(0, SentenceCounter.countSentences(""));
    }

    @Test
    public void shouldFindNoSentencesWhenThereAreNoLetters() {
        assertEquals(0, SentenceCounter.countSentences("  .. .,. !  ? , . ()"));
    }

    @Test
    public void shouldFindNoSentenceWhenThereIsNoStopChar() {
        String text = "aa bb cccccccc dd, eee; ffff'rr pppp-eeee (aaax)";
        assertEquals(0, SentenceCounter.countSentences(text));
    }

    @Test
    public void shouldFindOneSentence() {
        String text = "aaa beee, z pppp uuuu\n\r.rrrr tttt";
        assertEquals(1, SentenceCounter.countSentences(text));
    }

    @Test
    public void shouldFindThreeSentences() {
        String text = "a.bb1234?! ppp uuu! &%$ ( ,).";
        assertEquals(3, SentenceCounter.countSentences(text));
    }

}
