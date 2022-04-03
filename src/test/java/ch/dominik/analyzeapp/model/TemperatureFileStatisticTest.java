package ch.dominik.analyzeapp.model;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TemperatureFileStatisticTest {
    private int testCounter;

    @Test
    void testConstructor() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        assertThat(statistic).isInstanceOf(TemperatureFileStatistic.class);
    }

    @Test
    void testAddPropertyChangeListener() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        statistic.addPropertyChangeListener(evt -> {});
        assertThat(statistic.getNumOfPropertyChangeListeners()).isEqualTo(1);
    }

    @Test
    void testRemovePropertyChangeListener() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        PropertyChangeListener listener = evt -> {};
        statistic.addPropertyChangeListener(listener);
        statistic.removePropertyChangeListener(listener);
        assertThat(statistic.getNumOfPropertyChangeListeners()).isEqualTo(0);
    }

    @Test
    void testAddPropertyChangeListenerThrowsException() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        assertThatThrownBy(() -> statistic.addPropertyChangeListener(null)).
                isInstanceOf(NullPointerException.class).
                hasMessage("cannot add null");
    }

    @Test
    void testRemovePropertyChangeListenerThrowsException() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        assertThatThrownBy(() -> statistic.removePropertyChangeListener(null)).
                isInstanceOf(NullPointerException.class).
                hasMessage("cannot remove null");
    }

    @Test
    void testFirePropertyChangeEventWhenNumOfValuesChanged() {
        testCounter = 0;
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        statistic.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                testCounter++;
            }
        });

        statistic.setNumOfValues(200);
        assertThat(testCounter).isEqualTo(1);
    }

    @Test
    void testFirePropertyChangeEventWhenNumOfConvertionsChanged() {
        testCounter = 0;
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        statistic.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                testCounter++;
            }
        });

        statistic.incrementNumOfConvertions();
        assertThat(testCounter).isEqualTo(1);
    }

    @Test
    void testFirePropertyChangeEventWhenNumOfDataExceptionsChanged() {
        testCounter = 0;
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        statistic.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                testCounter++;
            }
        });

        statistic.incrementNumOfDataExceptions();
        assertThat(testCounter).isEqualTo(1);
    }

    @Test
    void testToString() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        statistic.setNumOfValues(2000);
        statistic.setNumOfConvertions(1900);
        statistic.setNumOfDataExceptions(100);
        statistic.setAverageTemperature(Temperatur.createFromCelsius(25.5f));
        LocalDateTime time = LocalDateTime.parse("2025-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(-30f);
        MeasurementPoint minimal = new MeasurementPoint(time, temperatur);
        statistic.setMinimalMeasurementPoint(minimal);
        LocalDateTime timeTwo = LocalDateTime.parse("2025-02-09T21:15:30");
        Temperatur temperaturTwo = Temperatur.createFromCelsius(30f);
        MeasurementPoint maximal = new MeasurementPoint(timeTwo, temperaturTwo);
        statistic.setMaximalMeasurementPoint(maximal);

        String excepted = "TemperatureFileStatistic{statisticName='Test', " +
                "maximalMeasurementPoint=[Temperatur = 30,00°C] measured on 2025-02-09T21:15:30, " +
                "minimalMeasurementPoint=[Temperatur = -30,00°C] measured on 2025-02-09T21:15:30, " +
                "averageTemperature=[Temperatur = 25,50°C], " +
                "numOfConvertions=1900, " +
                "numOfDataExceptions=100, " +
                "numOfValues=2000}";

        assertThat(statistic.toString()).isEqualTo(excepted);
    }

    @Test
    void testIncrementNumOfConvertions() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        statistic.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        });
        statistic.setNumOfConvertions(100);
        statistic.incrementNumOfConvertions();
        assertThat(statistic.getNumOfConvertions()).isEqualTo(101);
    }

    @Test
    void testIncrementNumOfDataExceptions() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        statistic.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        });
        statistic.setNumOfDataExceptions(100);
        statistic.incrementNumOfDataExceptions();
        assertThat(statistic.getNumOfDataExceptions()).isEqualTo(101);
    }

    @Test
    void testSetAndGetMaximalMeasurementPoint() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        LocalDateTime time = LocalDateTime.parse("2025-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        statistic.setMaximalMeasurementPoint(measurementPoint);
        assertThat(statistic.getMaximalMeasurementPoint()).isEqualTo(measurementPoint);
    }

    @Test
    void testSetAndGetMinimalMeasurementPoint() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        LocalDateTime time = LocalDateTime.parse("2025-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(-30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        statistic.setMinimalMeasurementPoint(measurementPoint);
        assertThat(statistic.getMinimalMeasurementPoint()).isEqualTo(measurementPoint);
    }

    @Test
    void testSetAndGetAverageTemperature() {
        TemperatureFileStatistic statistic = new TemperatureFileStatistic("Test");
        Temperatur average = Temperatur.createFromCelsius(30f);
        statistic.setAverageTemperature(average);
        assertThat(statistic.getAverageTemperature()).isEqualTo(average);
    }

}