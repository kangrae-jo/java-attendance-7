package attendance.controller;

import attendance.service.FileReaderService;
import attendance.view.InputView;
import attendance.view.OutputView;

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
        
    }

}
