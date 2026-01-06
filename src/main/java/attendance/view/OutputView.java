package attendance.view;

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

}
