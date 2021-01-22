package platform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    public static String now() {
        return format(LocalDateTime.now());
    }
}