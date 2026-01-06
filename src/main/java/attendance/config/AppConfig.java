package attendance.config;

import attendance.controller.Controller;
import attendance.service.FileReaderService;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;

public class AppConfig {

    public static String ATTENDANCES_CSV_DIR = "src/main/resources/attendances.csv";
    public static LocalDateTime NOW = LocalDateTime.of(2024, 12, 13, 0, 0);

    private InputView inputView;
    private OutputView outputView;
    private FileReaderService fileReaderService;
    private Controller controller;

    public AppConfig() {
    }

    private InputView inputView() {
        if (inputView == null) {
            return inputView = new InputView(outputView());
        }
        return inputView;
    }

    private OutputView outputView() {
        if (outputView == null) {
            return outputView = new OutputView();
        }
        return outputView;
    }

    private FileReaderService fileReaderService() {
        if (fileReaderService == null) {
            return fileReaderService = new FileReaderService();
        }
        return fileReaderService;
    }

    public Controller controller() {
        if (controller == null) {
            return controller = new Controller(inputView(), outputView(), fileReaderService());
        }
        return controller;
    }

}
