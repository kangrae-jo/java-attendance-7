package attendance.view;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;

public class InputView {

    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    public String readCommand(LocalDate localDate) {
        System.out.println("오늘은 " + outputView.getLocalDate(localDate) + "입니다. 기능을 선택해 주세요.");
        return Console.readLine();
    }

    public String readName() {
        System.out.println();
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String readArrivalTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }

    public String readNameToModify() {
        System.out.println();
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String readDateToModify() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        return Console.readLine();
    }

    public String readArrivalTimeToModify() {
        System.out.println("언제로 변경하겠습니까?");
        return Console.readLine();
    }

}
