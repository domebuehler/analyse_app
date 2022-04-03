package ch.dominik.analyzeapp.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TemperaturTest {

    private static float TOLERANCE = 0.1f;

    @Test
    void testCreateFromCelsius() {
        Temperatur temperatur = Temperatur.createFromCelsius(50f);
        assertThat(temperatur.getTempCelsius()).isEqualTo(50f);
    }

    @Test
    void testCreateFromKelvin() {
        Temperatur temperatur = Temperatur.createFromKelvin(300f);
        assertThat(temperatur.getTempKelvin()).isEqualTo(300f);
    }

    @Test
    void testCreatFromFahrenheit() {
        Temperatur temperatur = Temperatur.creatFromFahrenheit(200f);
        assertThat(temperatur.getTempFahrenheit()).isEqualTo(200f);
    }

    @Test
    void testCreateWithInvalidTemperatureThrowsException() {
        assertThatThrownBy(() -> Temperatur.createFromCelsius(-300f)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessage("temperature-values under - " +
                Temperatur.KELVIN_OFFSET + " °C are not allowed");
    }

    @Test
    void testEqualsContract() {
        EqualsVerifier.forClass(Temperatur.class).verify();
    }

    @Test
    void testCompareToNullThrowsException() {
        Temperatur temperatur = Temperatur.createFromCelsius(20f);
        assertThatThrownBy(() -> temperatur.compareTo(null)).
                isInstanceOf(NullPointerException.class).
                hasMessage("cannot compare to null");
    }

    @Test
    void testCompareToIfEquals() {
        Temperatur temperatur = Temperatur.createFromCelsius(20f);
        assertThat(temperatur.compareTo(temperatur)).isEqualTo(0);
    }

    @Test
    void testCompareToBiggerTemperature() {
        Temperatur biggerTemperature = Temperatur.createFromCelsius(50f);
        Temperatur smallerTemperature = Temperatur.createFromCelsius(20f);
        assertThat(smallerTemperature.compareTo(biggerTemperature)).isNegative();
    }

    @Test
    void testCompareToSmallerTemperature() {
        Temperatur biggerTemperature = Temperatur.createFromCelsius(50f);
        Temperatur smallerTemperature = Temperatur.createFromCelsius(20f);
        assertThat(biggerTemperature.compareTo(smallerTemperature)).isPositive();
    }

    @Test
    void testConvertFahrenheitToCelsius() {
        float converted = Temperatur.convertFahrenheitToCelsius(100f);
        assertThat(converted).isCloseTo(37.78f, within(TOLERANCE));
    }

    @Test
    void testConvertCelsiusToFahrenheit() {
        float converted = Temperatur.convertCelsiusToFahrenheit(40f);
        assertThat(converted).isCloseTo(104f, within(TOLERANCE));
    }

    @Test
    void testConvertKelvinToCelsius() {
        float converted = Temperatur.convertKelvinToCelsius(300f);
        assertThat(converted).isCloseTo(26.85f, within(TOLERANCE));
    }

    @Test
    void testConvertCelsiusToKelvin() {
        float converted = Temperatur.convertCelsiusToKelvin(30f);
        assertThat(converted).isCloseTo(303.15f, within(TOLERANCE));
    }

    @Test
    void testToString() {
        Temperatur temperatur = Temperatur.createFromCelsius(50.555f);
        String testString = temperatur.toString();
        assertThat(testString).isEqualTo("[Temperatur = 50,56°C]");
    }
}