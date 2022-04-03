package ch.dominik.analyzeapp.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;

@SuppressWarnings("ClassCanBeRecord")
public final class Temperatur implements Comparable<Temperatur> {

    private static final Logger LOG = LogManager.getLogger(Temperatur.class);

    private final float tempCelsius;
    public static final float KELVIN_OFFSET = 273.15f;

    private Temperatur(float tempCelsius) throws IllegalArgumentException {
        if (tempCelsius < -KELVIN_OFFSET) {
            throw new IllegalArgumentException("temperature-values under - " +
                    Temperatur.KELVIN_OFFSET + " °C are not allowed");
        } else {
            this.tempCelsius = tempCelsius;
        }
    }

    public static Temperatur createFromCelsius(float tempCelsius) {
        return new Temperatur(tempCelsius);
    }

    public static Temperatur createFromKelvin(float tempKelvin) {
        return createFromCelsius(Temperatur.convertKelvinToCelsius(tempKelvin));
    }

    public static Temperatur creatFromFahrenheit(float tempFahrenheit) {
        return createFromCelsius(Temperatur.convertFahrenheitToCelsius(tempFahrenheit));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Temperatur)) {
            return false;
        }
        Temperatur other = (Temperatur) object;
        return Float.compare(this.tempCelsius, other.tempCelsius) == 0;
    }

    @Override
    public int hashCode() {
        return Float.hashCode(this.tempCelsius);
    }

    @Override
    public int compareTo(Temperatur other) {
        if (other == null) {
            throw new NullPointerException("cannot compare to null");
        } else if (this == other) {
            return 0;
        }
        return Float.compare(this.tempCelsius, other.tempCelsius);
    }

    public static float convertFahrenheitToCelsius(float tempFahrenheit) {
        return (tempFahrenheit - 32.0f) / 1.8f;
    }

    public static float convertCelsiusToFahrenheit(float tempCelsius) {
        return tempCelsius * 1.8f + 32.0f;
    }

    public static float convertKelvinToCelsius(float tempKelvin) {
        return tempKelvin - KELVIN_OFFSET;
    }

    public static float convertCelsiusToKelvin(float tempCelsius) {
        return tempCelsius + Temperatur.KELVIN_OFFSET;
    }

    @Override
    public String toString() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        return "[Temperatur = " + fmt.format(this.tempCelsius) + "°C]";
    }

    public float getTempCelsius() {
        return this.tempCelsius;
    }

    public float getTempFahrenheit() {
        return Temperatur.convertCelsiusToFahrenheit(this.tempCelsius);
    }

    public float getTempKelvin() {
        return Temperatur.convertCelsiusToKelvin(this.tempCelsius);
    }
}
