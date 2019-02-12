package pl.znamirowski.textanalyzer.analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceCounter {

    private final static String regex = "[a-zA-Z]+[^.?!]*[.?!]";
    private final static Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    static int countSentences(String text) {
        final Matcher matcher = pattern.matcher(text);
        int count = 0;

        while (matcher.find())
            ++count;

        return count;
    }

}
