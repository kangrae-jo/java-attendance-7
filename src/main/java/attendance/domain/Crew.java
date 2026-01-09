package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Crew {

    private final String name;
    private Map<LocalDate, LocalTimeWithState> attendanceInfo;

    public Crew(String name) {
        this.name = name;
        this.attendanceInfo = init();
    }

    public LocalTimeWithState addAttend(LocalDate date, LocalTime time) {
        // TODO: 이미 있는 경우 처리하기
        LocalTimeWithState localTimeWithState = new LocalTimeWithState(date, time);
        attendanceInfo.put(date, localTimeWithState);
        return localTimeWithState;
    }

    public List<LocalTimeWithState> modifyAttend(LocalDate date, LocalTime time) {
        LocalTime prevTime = attendanceInfo.get(date).getLocalTime();
        addAttend(date, time);
        return List.of(new LocalTimeWithState(date, prevTime), addAttend(date, time));
    }

    public Map<LocalDate, LocalTime> getAttendInformation() {
        Map<LocalDate, LocalTime> information = new TreeMap<>();
        for (int i = 1; i < DateTimes.now().getDayOfMonth() - 1; i++) {
            LocalDate localDate = LocalDate.of(2024, 12, i);
            LocalTime localTime = attendanceInfo.get(localDate).getLocalTime();
            information.put(localDate, localTime);
        }
        return information;
    }

    public String getName() {
        return name;
    }

    private Map<LocalDate, LocalTimeWithState> init() {
        attendanceInfo = new TreeMap<>();
        for (int date = 1; date <= 31; date++) {
            attendanceInfo.put(LocalDate.of(2024, 12, date), null);
        }
        return attendanceInfo;
    }

}
