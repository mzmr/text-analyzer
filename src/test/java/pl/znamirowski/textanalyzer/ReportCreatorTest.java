package pl.znamirowski.textanalyzer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ReportCreatorTest {

    @Test
    public void createReportFromFiles() throws IOException {
        String[] fileNames = { "a_tale_of_two_cities.txt", "pride_and_prejudice.txt",
                "the_adventures_of_sherlock_holmes.txt" };
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        for (String fileName : fileNames) {
            File file = new File(classLoader.getResource(fileName).getFile());
            new ReportCreator(file).createReport();
        }
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionWhenFileIsWrong() throws IOException {
        new ReportCreator(new File("somewrongpath")).createReport();
    }
}
