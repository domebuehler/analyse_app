package ch.dominik.analyzeapp.control;

import ch.dominik.analyzeapp.model.Temperatur;
import ch.dominik.analyzeapp.model.TemperatureFileStatistic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class TemperaturFileAnalyzerTest {

    private final static File TEST_FILE = new File("src/test/resources/TemperatureTestFile.csv");

    private static TemperaturFileAnalyzer analyzer = new TemperaturFileAnalyzer();
    private static PropertyChangeListener listener = evt -> {
    };
    private static TemperatureFileStatistic statistic;

    @BeforeAll
    static void beforeAll() {
        statistic = analyzer.analyzeFile(TEST_FILE, "Test", listener);
    }

    @Test
    void testIfMaximaIsCorrect() {
        Temperatur expectedTemperature = Temperatur.createFromCelsius(28f);
        assertThat(statistic.getMaximalMeasurementPoint().getTemperatur()).isEqualTo(expectedTemperature);
    }

    @Test
    void testIfMinimaIsCorrect() {
        Temperatur expectedTemperature = Temperatur.createFromCelsius(0.5f);
        assertThat(statistic.getMinimalMeasurementPoint().getTemperatur()).isEqualTo(expectedTemperature);
    }

    @Test
    void testIfAverageIsCorrect() {
        Temperatur expectedTemperature = Temperatur.createFromCelsius(12.5f);
        assertThat(statistic.getAverageTemperature()).isEqualTo(expectedTemperature);
    }

    @Test
    void testIfNumOfValuesIsCorrect() {
        assertThat(statistic.getNumOfValues()).isEqualTo(10);
    }

    @Test
    void testIfNumOfConvertionsIsCorrect() {
        assertThat(statistic.getNumOfConvertions()).isEqualTo(8);
    }

    @Test
    void testIfNumOfDataExceptionsIsCorrect() {
        assertThat(statistic.getNumOfDataExceptions()).isEqualTo(2);
    }


}