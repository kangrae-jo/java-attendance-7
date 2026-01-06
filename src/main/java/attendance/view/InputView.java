package attendance.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readFunction() {
        System.out.println("오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.");
        return Console.readLine();
    }

}
