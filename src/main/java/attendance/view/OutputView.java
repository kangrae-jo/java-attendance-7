package attendance.view;

import attendance.domain.LocalTimeWithState;
import attendance.domain.State;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일", Locale.KOREAN);
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:ss", Locale.KOREAN);

    public void printError(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public void printAddSuccessMsg(LocalDate localDate, LocalTimeWithState localTimeWithState) {
        System.out.print(getLocalDate(localDate));
        System.out.print(" " + getLocalTime(localTimeWithState.getLocalTime()));
        System.out.println(" " + getState(localTimeWithState.getState()));
    }

    public void printModifySuccessMsg(LocalDate date, List<LocalTimeWithState> information) {
        LocalTimeWithState prev = information.getFirst();
        LocalTimeWithState now = information.getLast();

        System.out.println();
        System.out.print(getLocalDate(date) + " "
                + getLocalTime(prev.getLocalTime()) + " "
                + getState(prev.getState())
        );
        System.out.print(" -> "
                + getLocalTime(now.getLocalTime()) + " "
                + getState(now.getState())
        );
        System.out.println();
    }

    public void printAttendInformation(String name, Map<LocalDate, LocalTime> information) {
        System.out.println();
        System.out.println("이번 달 " + name + "의 출석 기록입니다.");
        printInformation(information);
        // 지각등 상태 출력
    }

    public String getLocalTime(LocalTime localTime) {
        if (localTime == null) {
            return "--:--";
        }
        return localTime.format(timeFormatter);
    }

    public String getLocalDate(LocalDate localDate) {
        return localDate.format(dateTimeFormatter) + " "
                + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
    }

    private String getState(State state) {
        return "(" + state.getName() + ")";
    }

    private void printInformation(Map<LocalDate, LocalTime> information) {
        System.out.println();
        for (LocalDate localDate : information.keySet()) {
            if (localDate.getDayOfWeek().getValue() == 6 || localDate.getDayOfWeek().getValue() == 7) {
                continue;
            }
            System.out.print(getLocalDate(localDate));
            System.out.println(getLocalTime(information.get(localDate)));
        }
    }

}
