package attendance.config;


import attendance.controller.Controller;
import attendance.view.InputView;
import attendance.view.OutputView;

public class AppConfig {

    public static String ATTENDANCES_CSV_DIR = "src/main/resources/attendances.csv";

    private InputView inputView;
    private OutputView outputView;
    private Controller controller;

    public AppConfig() {
    }

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView(outputView());
        }
        return inputView;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public Controller controller() {
        if (controller == null) {
            controller = new Controller(inputView(), outputView());
        }
        return controller;
    }

}
