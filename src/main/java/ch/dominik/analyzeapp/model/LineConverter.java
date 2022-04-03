package ch.dominik.analyzeapp.model;

import java.time.LocalDateTime;
import java.util.zip.DataFormatException;

public final class LineConverter {

    private final static int TIMESTAMP_INDEX = 1;
    private final static int TEMPERATUR_INDEX = 2;
    private final static int MAX_LINE_LENGTH = 40;
    private final static int MIN_LINE_LENGTH = 37;
    private final static int LINE_TO_STRING_ARRAY_LENGTH = 4;
    private static String exceptionMessage;

    public static MeasurementPoint convertLineToMeasurementPoint(String line) throws DataFormatException {
        if (validateLine(line)) {
            return createMeasurementPointFromLine(line);
        } else {
            throw new DataFormatException(exceptionMessage);
        }
    }

    private static MeasurementPoint createMeasurementPointFromLine(String line) {
        String[] lineArray = line.split(";");
        Temperatur temperatur = Temperatur.createFromCelsius(Float.valueOf(lineArray[TEMPERATUR_INDEX]));
        LocalDateTime timestamp = TimeStampConverter.convert(lineArray[TIMESTAMP_INDEX]);
        return new MeasurementPoint(timestamp, temperatur);
    }

    private static boolean validateLine(String line) {
        if (isDelimiterMissing(line)) {
            exceptionMessage = "delimiter is missing";
            return false;
        } else if (isTheNumbersOfArgumentsInvalid(line)) {
            exceptionMessage = "invalid number of arguments";
            return false;
        } else if (isTheNumberOfCharsInvalid(line)) {
            exceptionMessage = "invalid number of chars: " +
                    "MAX_LENGTH = " + MAX_LINE_LENGTH +
                    "; " +
                    "MIN_LENGTH = " + MIN_LINE_LENGTH;
            return false;
        } else {
            return true;
        }
    }

    private static boolean isDelimiterMissing(String line) {
        return !line.contains(";");
    }

    private static boolean isTheNumbersOfArgumentsInvalid(String line) {
        return line.split(";").length != LINE_TO_STRING_ARRAY_LENGTH;
    }

    private static boolean isTheNumberOfCharsInvalid(String line) {
        return !(line.length() >= MIN_LINE_LENGTH && line.length() <= MAX_LINE_LENGTH);
    }

}
