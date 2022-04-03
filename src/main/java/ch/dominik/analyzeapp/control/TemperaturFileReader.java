package ch.dominik.analyzeapp.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class TemperaturFileReader {
    private static final Logger LOG = LogManager.getLogger(TemperaturFileReader.class);
    private static List<String> stringList = new ArrayList<>();
    private static String lineWhichWasRead;
    private static BufferedReader br;

    public static List<String> readTemperaturFile(final File file) throws FileNotFoundException {
        prepareForReading(file);
        if (file.exists()) {
            tryToReadFile(file);
        } else {
            throw new FileNotFoundException();
        }
        return stringList;
    }

    private static void prepareForReading(File file) {
        stringList.clear();
    }

    private static void tryToReadFile(File file) {
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(
                             new FileInputStream(file), Charset.forName("UTF-8")))) {
            while ((lineWhichWasRead = br.readLine()) != null) {
                addLineWhichWasReadOnList();
            }
        } catch (IOException exception) {
            LOG.error("error while reading", exception);
        }
    }

    private static void addLineWhichWasReadOnList() {
        if (lineWhichWasRead != null) {
            stringList.add(lineWhichWasRead);
        }
    }
}
