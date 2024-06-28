package viniciusmiranda.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE_NAME = "log.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        String timestampedMessage = String.format("[%s] %s%n", LocalDateTime.now().format(DATE_TIME_FORMATTER),
                message);
        try {
            Path logFilePath = Path.of(LOG_FILE_NAME);
            if (!Files.exists(logFilePath)) {
                Files.createFile(logFilePath);
            }
            Files.writeString(logFilePath, timestampedMessage, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
