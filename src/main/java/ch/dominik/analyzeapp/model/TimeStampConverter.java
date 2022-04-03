package ch.dominik.analyzeapp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStampConverter {

    public static LocalDateTime convert(String timestampAsString) {
        LocalDateTime timestamp = LocalDateTime.parse(timestampAsString,
                DateTimeFormatter.ofPattern("\"yyyy/MM/dd HH:mm:ss\""));
        return timestamp;
    }
}
