package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class RandomDataUtil {

public static String campaignName() {
    return "Campaign_" + System.currentTimeMillis();
}

    public static String message() {

        return "Automation Test Message " +
                UUID.randomUUID().toString().substring(0,5);

    }

    public static String futureDateTime() {

        return LocalDateTime.now()
                .plusDays(1)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

    }

}