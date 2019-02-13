package pl.znamirowski.textanalyzer;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Input file was not provided.");
            return;
        }

        if (args.length > 1) {
            System.out.println("Too many input arguments. Only the first one will be considered.");
        }

        String filePath = args[0];
        File file = new File(filePath);

        if (!file.isFile()) {
            System.out.println("The given file " + filePath + " is not valid.");
            return;
        }

        try {
            System.out.println(new ReportCreator(file).createReport());
        } catch (IOException e) {
            System.out.println("Unable to read file " + filePath + ".");
        }
    }
}
