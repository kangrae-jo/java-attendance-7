package attendance.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일", Locale.KOREAN);
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:ss", Locale.KOREAN);

    public void printError(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public void printModifySuccessMsg(LocalDate date, LocalTime prev, LocalTime time) {
        System.out.println();
        System.out.print(getLocalDate(date));
        System.out.println(getLocalTime(prev) + " -> " + getLocalTime(time));
    }

    public String getLocalTime(LocalTime localTime) {
        return localTime.format(timeFormatter);
    }

    public String getLocalDate(LocalDate localDate) {
        return localDate.format(dateTimeFormatter) + " "
                + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
    }

}
