package attendance.controller;

import static attendance.config.AppConfig.ATTENDANCES_CSV_DIR;
import static attendance.config.AppConfig.NOW;

import attendance.domain.ArrivalInformation;
import attendance.domain.Crews;
import attendance.dto.ModifiedDto;
import attendance.service.FileReaderService;
import attendance.service.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
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
        while (!"Q".equals(function = readFunction(NOW))) {
            if ("1".equals(function)) {
                handleAttendanceFunction(crews);
            }
            if ("2".equals(function)) {
                handleAttendanceModifyFunction(crews);
            }
            if ("3".equals(function)) {
                handleAttendanceInformation(crews);
            }
            if ("4".equals(function)) {

            }
        }
    }

    private Crews readCrewsFromFile(String dir) {
        return retryUntilValid(() -> {
            List<String> lines = fileReaderService.readFile(dir);
            return Crews.from(InputParser.parseCrews(lines));
        });
    }

    private String readFunction(LocalDateTime today) {
        return retryUntilValid(
                () -> inputView.readFunction(today)
        );
    }

    private void handleAttendanceFunction(Crews crews) {
        String crewName = inputView.readCrewName();
        LocalTime arrivalTime = readArrivalTime();

        try {
            crews.attend(crewName, arrivalTime);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void handleAttendanceModifyFunction(Crews crews) {
        String crewName = inputView.readCrewName();
        LocalDate date = readDate();
        LocalTime time = readTime();

        try {
            ModifiedDto modifiedDto = crews.modify(crewName, date, time);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void handleAttendanceInformation(Crews crews) {
        String crewName = inputView.readCrewName();
        Map<LocalDate, ArrivalInformation> information = crews.getAttendanceInformationFrom(crewName);
        LocalDate start = LocalDate.of(2024, 12, 1);
        for (int i = NOW.getDayOfMonth(); i < 31; i++) {
            information.remove(start.plusDays(i));
        }
        outputView.printAttendanceInformation(crewName, information);
    }

    private LocalTime readArrivalTime() {
        return retryUntilValid(() -> {
            String time = inputView.readArrivalTime();
            return InputParser.parseTime(time);
        });
    }

    private LocalTime readTime() {
        return retryUntilValid(() -> {
            String time = inputView.readModifyTime();
            return InputParser.parseTime(time);
        });
    }

    private LocalDate readDate() {
        return retryUntilValid(() -> {
            String date = inputView.readDate();
            return InputParser.parseDate(date);
        });
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
