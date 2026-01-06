package attendance.view;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDateTime;

public class InputView {

    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    public String readFunction(LocalDateTime today) {
        System.out.println("오늘은 " + outputView.getToday(today.toLocalDate()) + "입니다. 기능을 선택해 주세요.");
        outputView.printFunctionList();
        return Console.readLine();
    }

    public String readCrewName() {
        System.out.println();
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public String readArrivalTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }

    public String readModifyTime() {
        System.out.println("언제로 변경하겠습니까?");
        return Console.readLine();
    }

    public String readDate() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        return Console.readLine();
    }

}
