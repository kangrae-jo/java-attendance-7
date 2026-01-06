package attendance.controller;

import static attendance.config.AppConfig.ATTENDANCES_CSV_DIR;

import attendance.domain.Crews;
import attendance.service.FileReaderService;
import attendance.service.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final FileReaderService fileReaderService;

    public Controller(InputView inputView, OutputView outputView, FileReaderService fileReaderService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.fileReaderService = fileReaderService;
    }

    public void run() {
        Crews crews = readCrewsFromFile(ATTENDANCES_CSV_DIR);

        String function;
        while (!"Q".equals(function = readFunction())) {
            if ("1".equals(function)) {
            
            }
            if ("2".equals(function)) {

            }
            if ("3".equals(function)) {

            }
            if ("4".equals(function)) {

            }
        }
    }

    private Crews readCrewsFromFile(String dir) {
        return retryUntilValid(() -> {
            List<String> lines = fileReaderService.readFile(dir);
            return InputParser.parseCrews(lines);
        });
    }

    private String readFunction() {
        return retryUntilValid(() ->
                inputView.readFunction()
        );
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
