package attendance.view;

import attendance.domain.ArrivalInformation;
import java.time.LocalDate;
import java.util.Map;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
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
        System.out.print(date.getMonth() + "월");
        System.out.print(date.getDayOfMonth() + "일 ");
        System.out.print(date.getDayOfWeek() + " ");
        System.out.print(information.getArrivalTime() + " ");
        System.out.print("(" + information.getState().getName() + ")");
    }

}
