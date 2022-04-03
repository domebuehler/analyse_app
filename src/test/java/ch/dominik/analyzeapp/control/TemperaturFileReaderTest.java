package ch.dominik.analyzeapp.control;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TemperaturFileReaderTest {

    private final static File TEST_FILE = new File("src/test/resources/TemperatureTestFile.csv");
    private final static File NOT_EXISTING_FILE = new File("src/test/resources/File.csv");

    @Test
    void testReadTemperaturFile() {
        List<String> list = new ArrayList<>();
        try {
            list = TemperaturFileReader.readTemperaturFile(TEST_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertThat(list.size()).isEqualTo(10);
    }

    @Test
    void testReadTemperaturFileThrowsException() {
        assertThatThrownBy(() -> TemperaturFileReader.readTemperaturFile(NOT_EXISTING_FILE)).
                isInstanceOf(FileNotFoundException.class);
    }
}