package com.imagen.service.utilities;
import java.time.LocalDateTime;
import java.util.UUID;

public class FileUtility {
    public static String generateFileName(String extension) {
        LocalDateTime now = LocalDateTime.now();
        String randomId = UUID.randomUUID().toString().replace("-", "");
        String fileName = String.format("%04d%02d%02d%02d%02d%02d%s",
                now.getYear(),
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getHour(),
                now.getMinute(),
                now.getSecond(),
                randomId,
                extension);
        return fileName;
    }
}
