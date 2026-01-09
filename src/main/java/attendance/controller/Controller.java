package attendance.controller;

import static attendance.config.AppConfig.ATTENDANCES_CSV_DIR;

import attendance.domain.Crew;
import attendance.util.FileReader;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.util.List;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Crew> crews = readCrews(ATTENDANCES_CSV_DIR);

        LocalDateTime now = DateTimes.now();
        while (true) {
            String command = inputView.readCommand(now.toLocalDate());
            if (command.equals("Q")) {
                return;
            }
            if (command.equals("1")) {
                // 출석 확인
            }
            if (command.equals("2")) {
                // 출석 수정
            }
            if (command.equals("3")) {
                // 출석 기록 확인
            }
            if (command.equals("4")) {
                // 제적 위험자 확인
            }
        }
    }

    private List<Crew> readCrews(String filePath) {
        return InputParser.parseNames(FileReader.readFile(filePath));
    }

}
