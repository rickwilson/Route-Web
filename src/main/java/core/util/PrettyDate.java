package core.util;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class PrettyDate {
    // now in utah
    public static String currentDateTimeUtah() {
        return dateTimeAndZone(LocalDateTime.now(),"America/Denver");
    }

    // now in defined timezone
    public static String currentDateTimeInZone(String where) {
        ZoneId requestedZone = ZoneId.of(where);
        LocalDateTime when = LocalDateTime.now();
        ZonedDateTime there = ZonedDateTime.of(when,requestedZone);
        return makeItPretty(there);
    }

    // defined datetime in defined timezone
    public static String dateTimeAndZone(LocalDateTime when, String where) {
        ZoneId requestedZone = ZoneId.of(where);
        ZonedDateTime there = ZonedDateTime.of(when,requestedZone);
        return makeItPretty(there);
    }

    // make timedate pretty
    public static String makeItPretty(ZonedDateTime when) {
        String prettyString = null;
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
            prettyString = when.format(format);
        } catch (DateTimeException ex) {
            ex.printStackTrace();
        }
        return prettyString;
    }

    // defined timestamp
    public static String makeItPretty(Timestamp time) {
        try {
            LocalDateTime when = time.toLocalDateTime();
            return dateTimeAndZone(when,"America/Denver");
        } catch (Exception e) {
            return null;
        }
    }

    // defined datetime in defined timezone
    public static String dateAndZone(LocalDateTime when, String where) {
        ZoneId requestedZone = ZoneId.of(where);
        ZonedDateTime there = ZonedDateTime.of(when,requestedZone);
        return makeItPrettyDateOnly(there);
    }

    // make timedate pretty
    public static String makeItPrettyDateOnly(ZonedDateTime when) {
        String prettyString = null;
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
            prettyString = when.format(format);
        } catch (DateTimeException ex) {
            ex.printStackTrace();
        }
        return prettyString;
    }

    // defined timestamp
    public static String makeItPrettyDateOnly(Timestamp time) {
        try {
            LocalDateTime when = time.toLocalDateTime();
            return dateAndZone(when,"America/Denver");
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate stringToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        formatter = formatter.withLocale(Locale.US);
        return LocalDate.parse(dateString, formatter);
    }

    public static Timestamp localDateToTimestamp(LocalDate d, boolean startOfDay) {
        if(startOfDay) {
            return Timestamp.valueOf(d.atStartOfDay());
        }
        return Timestamp.valueOf(d.atTime(23,59,59));
    }
}
