package attendance.controller;

import static attendance.config.AppConfig.ATTENDANCES_CSV_DIR;

import attendance.domain.Crew;
import attendance.util.FileReader;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
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

    }

    private List<Crew> readCrews(String filePath) {
        return InputParser.parseNames(FileReader.readFile(filePath));
    }

}
