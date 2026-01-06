package attendance.view;

import attendance.domain.ArrivalInformation;
import attendance.domain.State;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    public void printArrivalInformation(LocalDateTime today, ArrivalInformation information) {
        System.out.println(getToday(today.toLocalDate()) + " " + getTime(information) + " " + getState(information));
    }

    public String getToday(LocalDate today) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M월 d일", Locale.KOREAN);
        return today.format(formatter) + " " + today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
    }

    private String getTime(ArrivalInformation information) {
        if (information == null) {
            return "--:--";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.KOREAN);
        return information.getArrivalTime().format(formatter);
    }

    private String getState(ArrivalInformation information) {
        if (information == null) {
            return "(" + State.ABSENCE.getName() + ")";
        }
        return "(" + information.getState().getName() + ")";
    }

    public void printFunctionList() {
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
    }

    public void printAttendanceInformation(String crewName, Map<LocalDate, ArrivalInformation> information) {
        System.out.println("이번 달 " + crewName + "의 출석 기록입니다.");
        System.out.println();

        for (LocalDate date : information.keySet()) {
            printInformation(date, information.get(date));
        }
        System.out.println();
    }

    private void printInformation(LocalDate date, ArrivalInformation information) {
        System.out.print(getToday(date) + " ");
        System.out.print(getTime(information) + " ");
        System.out.println(getState(information));
    }

}
