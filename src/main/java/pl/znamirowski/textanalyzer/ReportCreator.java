package pl.znamirowski.textanalyzer;

import pl.znamirowski.textanalyzer.analyzer.TextAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class ReportCreator {

    private final File file;

    public ReportCreator(File file) {
        this.file = file;
    }


    public String createReport() throws IOException {
        String text = readFileToString();
        TextAnalyzer analyzer = new TextAnalyzer(text);

        Map<String, Integer> topTen = analyzer.topTenWords();
        int sentencesNumber = analyzer.countSentences();
        int whitespaceCharsNumber = analyzer.countWhitespaceChars();
        int allCharsNumber = analyzer.countCharacters();
        int nonAlphaCharsNumber = analyzer.countNonAlphaChars();
        Map<Character, Integer> lettersNumber = analyzer.countLetters();

        StringBuilder sb = new StringBuilder();

        sb.append("Most popular words longer than 5:\n");
        int i = 1;
        for (Map.Entry<String, Integer> word : topTen.entrySet()){
            sb.append(i++);
            sb.append(". ");
            sb.append(word.getKey());
            sb.append("\t-\t");
            sb.append(word.getValue());
            sb.append("\n");
        }

        sb.append("\nNumber of sentences: ");
        sb.append(sentencesNumber);

        sb.append("\nNumber of whitespace characters: ");
        sb.append(whitespaceCharsNumber);

        sb.append("\nTotal number of characters: ");
        sb.append(allCharsNumber);

        sb.append("\nNumber of non-alphabetic characters: ");
        sb.append(nonAlphaCharsNumber);

        sb.append("\n\nFrequency of appearing of letters:\n");
        for (Map.Entry<Character, Integer> letter : lettersNumber.entrySet()){
            sb.append(letter.getKey());
            sb.append("\t-\t");
            sb.append(letter.getValue());
            sb.append("\n");
        }

        return sb.toString();
    }

    private String readFileToString() throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
