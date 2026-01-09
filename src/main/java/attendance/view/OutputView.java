package attendance.view;

import attendance.domain.LocalTimeWithState;
import attendance.domain.State;
import attendance.domain.States;
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

    public void printAttendInformation(String name, Map<LocalDate, LocalTimeWithState> information) {
        System.out.println();
        System.out.println("이번 달 " + name + "의 출석 기록입니다.");
        printInformation(information);
        System.out.println();
    }

    public void printStatesByName(States states) {
        Map<State, Integer> info = states.getStates();
        System.out.println(State.ATTENDANCE.getName() + ": " + info.get(State.ATTENDANCE) + "회");
        System.out.println(State.ABSENCE.getName() + ": " + info.get(State.ABSENCE) + "회");
        System.out.println(State.TARDINESS.getName() + ": " + info.get(State.TARDINESS) + "회");
        System.out.println();

        System.out.println(states.getPolicy() + " 대상자입니다.");
        System.out.println();
    }

    public void printStates(List<States> states) {
        System.out.println("제적 위험자 조회 결과");
        for (States info : states) {
            printStateInfo(info);
        }
        System.out.println();
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

    private void printInformation(Map<LocalDate, LocalTimeWithState> information) {
        System.out.println();
        for (LocalDate localDate : information.keySet()) {
            if (localDate.getDayOfWeek().getValue() == 6 || localDate.getDayOfWeek().getValue() == 7) {
                continue;
            }
            System.out.print(getLocalDate(localDate));
            System.out.print(" " + getLocalTime(information.get(localDate).getLocalTime()));
            System.out.print(" " + getState(information.get(localDate).getState()));
            System.out.println();
        }
    }

    private void printStateInfo(States info) {
        System.out.print("- " + info.getName() + ": ");
        Map<State, Integer> states = info.getStates();
        System.out.print(State.ABSENCE.getName() + " " + states.get(State.ABSENCE) + "회, ");
        System.out.print(State.TARDINESS.getName() + " " + states.get(State.TARDINESS) + "회 ");
        System.out.print("(" + info.getPolicy() + ")");
        System.out.println();
    }

}
