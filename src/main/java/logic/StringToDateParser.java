package logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Haharin on 19.12.2017.
 */
public class StringToDateParser {
    private static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy-HH-mm-ss";
    public static LocalDateTime doIt (String stringDate){
        LocalDateTime date = LocalDateTime.parse(stringDate, DateTimeFormatter.ofPattern(DD_MM_YYYY_HH_MM_SS));
        return date;
    }
    public static String doBack (LocalDateTime date){
        String stringDate = date.format(DateTimeFormatter.ofPattern(DD_MM_YYYY_HH_MM_SS));
        return stringDate;
    }
}
