package ch.dominik.analyzeapp.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MeasurementPointTest {

    @Test
    public void testEqualsContract() {
        EqualsVerifier.forClass(MeasurementPoint.class).verify();
    }

    @Test
    public void testCompareToIfEquals() {
        LocalDateTime time = LocalDateTime.parse("2022-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        assertThat(measurementPoint.compareTo(measurementPoint)).isEqualTo(0);
    }

    @Test
    public void testCompareToSecondTemperatureBigger() {
        LocalDateTime time = LocalDateTime.parse("2022-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        LocalDateTime secondTime = LocalDateTime.parse("2022-02-09T21:15:30");
        Temperatur secondTemperatur = Temperatur.createFromCelsius(50f);
        MeasurementPoint secondMeasurementPoint = new MeasurementPoint(secondTime, secondTemperatur);
        assertThat(measurementPoint.compareTo(secondMeasurementPoint)).isNegative();
    }

    @Test
    public void testCompareToTemperatureBigger() {
        LocalDateTime time = LocalDateTime.parse("2022-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(50f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        LocalDateTime secondTime = LocalDateTime.parse("2022-02-09T21:15:30");
        Temperatur secondTemperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint secondMeasurementPoint = new MeasurementPoint(secondTime, secondTemperatur);
        assertThat(measurementPoint.compareTo(secondMeasurementPoint)).isPositive();
    }

    @Test
    public void testCompareToSecondTimeBigger() {
        LocalDateTime time = LocalDateTime.parse("2022-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        LocalDateTime secondTime = LocalDateTime.parse("2023-02-09T21:15:30");
        Temperatur secondTemperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint secondMeasurementPoint = new MeasurementPoint(secondTime, secondTemperatur);
        assertThat(measurementPoint.compareTo(secondMeasurementPoint)).isNegative();
    }

    @Test
    public void testCompareToTimeBigger() {
        LocalDateTime time = LocalDateTime.parse("2025-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        LocalDateTime secondTime = LocalDateTime.parse("2023-02-09T21:15:30");
        Temperatur secondTemperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint secondMeasurementPoint = new MeasurementPoint(secondTime, secondTemperatur);
        assertThat(measurementPoint.compareTo(secondMeasurementPoint)).isPositive();
    }

    @Test
    public void testCompareToNullThrowsException() {
        LocalDateTime time = LocalDateTime.parse("2025-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        assertThatThrownBy(() -> measurementPoint.compareTo(null)).
                isInstanceOf(NullPointerException.class).
                hasMessage("cannot compare to null");
    }

    @Test
    public void testToString() {
        LocalDateTime time = LocalDateTime.parse("2025-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        String testString = "[Temperatur = 30,00°C] measured on 2025-02-09T21:15:30";
        assertThat(testString).isEqualTo(measurementPoint.toString());
    }

    @Test
    public void testGetTemperatur() {
        LocalDateTime time = LocalDateTime.parse("2025-02-09T21:15:30");
        Temperatur temperatur = Temperatur.createFromCelsius(30f);
        MeasurementPoint measurementPoint = new MeasurementPoint(time, temperatur);
        Temperatur testTemperature = measurementPoint.getTemperatur();
        assertThat(testTemperature).isEqualTo(testTemperature);
    }
}