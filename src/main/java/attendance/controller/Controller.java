package attendance.controller;

import static attendance.config.AppConfig.ATTENDANCES_CSV_DIR;

import attendance.domain.ArrivalInformation;
import attendance.domain.Crews;
import attendance.dto.ModifiedDto;
import attendance.service.FileReaderService;
import attendance.service.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
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
        while (!"Q".equals(function = readFunction(DateTimes.now()))) {
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
        return trys(() -> {
            List<String> lines = fileReaderService.readFile(dir);
            return Crews.from(InputParser.parseCrews(lines));
        });
    }

    private String readFunction(LocalDateTime today) {
        return trys(
                () -> inputView.readFunction(today)
        );
    }

    private void handleAttendanceFunction(Crews crews) {
        if (DateTimes.now().getDayOfWeek().getValue() == 6 || DateTimes.now().getDayOfWeek().getValue() == 7) {
            throw new IllegalArgumentException("[ERROR] 12월 14일 토요일은 등교일이 아닙니다.");
        }
        String crewName = inputView.readCrewName();
        crews.isExist(crewName);
        LocalTime arrivalTime = readArrivalTime();

        trys(() -> {
            crews.attend(crewName, arrivalTime);
            return null;
        });
    }

    private void handleAttendanceModifyFunction(Crews crews) {
        String crewName = inputView.readCrewName();
        LocalDate date = readDate();
        LocalTime time = readTime();

        trys(() -> {
            ModifiedDto modifiedDto = crews.modify(crewName, date, time);
            return null;
        });
    }

    private void handleAttendanceInformation(Crews crews) {
        String crewName = inputView.readCrewName();
        Map<LocalDate, ArrivalInformation> information = crews.getAttendanceInformationFrom(crewName);
        LocalDate start = LocalDate.of(2024, 12, 1);
        for (int i = DateTimes.now().getDayOfMonth(); i < 31; i++) {
            information.remove(start.plusDays(i));
        }
        outputView.printAttendanceInformation(crewName, information);
    }

    private LocalTime readArrivalTime() {
        return trys(() -> {
            String time = inputView.readArrivalTime();
            return InputParser.parseTime(time);
        });
    }

    private LocalTime readTime() {
        return trys(() -> {
            String time = inputView.readModifyTime();
            return InputParser.parseTime(time);
        });
    }

    private LocalDate readDate() {
        return trys(() -> {
            String date = inputView.readDate();
            return InputParser.parseDate(date);
        });
    }

    private <T> T trys(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] " + e.getMessage());
        }
    }

}
