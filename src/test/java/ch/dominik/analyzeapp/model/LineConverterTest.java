package ch.dominik.analyzeapp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.zip.DataFormatException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LineConverterTest {

    @Test
    void testConvertLineToMeasurementPoint() {
        Temperatur temperatur = Temperatur.createFromCelsius(6.5f);
        LocalDateTime time = TimeStampConverter.convert("\"2018/01/27 00:04:03\"");
        MeasurementPoint exceptedMeasurementPoint = new MeasurementPoint(time, temperatur);
        MeasurementPoint actualMeasurementPoint = null;
        String line = "1517007843;\"2018/01/27 00:04:03\";6.5;78";
        try {
            actualMeasurementPoint = LineConverter.convertLineToMeasurementPoint(line);
        } catch (DataFormatException e) {
            e.printStackTrace();
        }

        assertThat(actualMeasurementPoint).isEqualTo(exceptedMeasurementPoint);
    }

    @Test
    void testConvertLineToMeasurementPointToFewArgumentsThrowsDataFormatException() {
        String line = "1517007843;\"2018/01/27 00:04:03\";6.5;";
        assertThatThrownBy(() -> LineConverter.convertLineToMeasurementPoint(line)).
                isInstanceOf(DataFormatException.class).
                hasMessage("invalid number of arguments");
    }

    @Test
    void testConvertLineToMeasurementPointToFewLettersThrowsDataFormatException() {
        String line = "17843;\"2018/01/27 00:04:03\";6.5;78";
        assertThatThrownBy(() -> LineConverter.convertLineToMeasurementPoint(line)).
                isInstanceOf(DataFormatException.class).
                hasMessage("invalid number of chars: MAX_LENGTH = " + 40 + "; MIN_LENGTH = " + 37);
    }

    @Test
    void testConvertLineToMeasurementPointMissingDelimiterThrowsDataFormatException() {
        String line = "1517007843\"2018/01/27 00:04:03\"6.578";
        assertThatThrownBy(() -> LineConverter.convertLineToMeasurementPoint(line)).
                isInstanceOf(DataFormatException.class).
                hasMessage("delimiter is missing");
    }

}