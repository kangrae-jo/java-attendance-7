package attendance.controller;

import static attendance.config.AppConfig.ATTENDANCES_CSV_DIR;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.domain.LocalTimeWithState;
import attendance.domain.States;
import attendance.util.FileReader;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Crews crews = new Crews(readCrews(ATTENDANCES_CSV_DIR));
        LocalDateTime now = DateTimes.now();

        while (true) {
            String command = inputView.readCommand(now.toLocalDate());
            if (command.equals("Q")) {
                return;
            }
            if (command.equals("1")) {
                handleAddAttend(now, crews);
            }
            if (command.equals("2")) {
                handleModifyAttend(crews);
            }
            if (command.equals("3")) {
                handlePrintAttend(crews);
            }
            if (command.equals("4")) {
                handlePrintStates(crews);
            }
        }
    }

    private List<Crew> readCrews(String filePath) {
        return InputParser.parseNames(FileReader.readFile(filePath));
    }

    private void handleAddAttend(LocalDateTime now, Crews crews) {
        validateWeekend(now);

        String name = inputView.readName();
        crews.hasThisCrew(name);

        LocalTime arrivalTime = InputParser.parseLocalTime(inputView.readArrivalTime());
        LocalTimeWithState localTimeWithState = crews.addAttendByName(name, now.toLocalDate(), arrivalTime);
        outputView.printAddSuccessMsg(now.toLocalDate(), localTimeWithState);
    }

    private void handleModifyAttend(Crews crews) {
        String name = inputView.readNameToModify();
        crews.hasThisCrew(name);

        LocalDate date = InputParser.parseLocalDate(inputView.readDateToModify());
        LocalTime time = InputParser.parseLocalTime(inputView.readArrivalTimeToModify());
        List<LocalTimeWithState> localTimeWithStates = crews.modifyAttendByName(name, date, time);
        outputView.printModifySuccessMsg(date, localTimeWithStates);
    }

    private void handlePrintAttend(Crews crews) {
        String name = inputView.readName();
        crews.hasThisCrew(name);

        Map<LocalDate, LocalTimeWithState> information = crews.getAttendInformationByName(name);
        outputView.printAttendInformation(name, information);

        States states = crews.getStatesByName(name);
        outputView.printStatesByName(states);
    }

    private void handlePrintStates(Crews crews) {
        List<States> states = crews.getStates();
        outputView.printStates(states);
    }

    private void validateWeekend(LocalDateTime now) {
        if (now.getDayOfWeek().getValue() == 6 || now.getDayOfWeek().getValue() == 7) {
            throw new IllegalArgumentException(
                    "[ERROR] " + outputView.getLocalDate(now.toLocalDate()) + "은 등교일이 아닙니다.");
        }
    }

}
