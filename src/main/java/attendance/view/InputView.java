package attendance.view;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class InputView {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일", Locale.KOREAN);

    public String readCommand(LocalDate localDate) {
        System.out.println("오늘은 " + getLocalDate(localDate) + "입니다. 기능을 선택해 주세요.");
        return Console.readLine();
    }

    private String getLocalDate(LocalDate localDate) {
        return localDate.format(dateTimeFormatter) + " "
                + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
    }

}
