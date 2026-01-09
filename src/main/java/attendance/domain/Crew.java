package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Crew {

    private final String name;
    private Map<LocalDate, LocalTime> attendanceInfo;

    public Crew(String name) {
        this.name = name;
        this.attendanceInfo = init();
    }

    public void addAttend(LocalDate date, LocalTime time) {
        attendanceInfo.put(date, time);
        // 이미 있는 경우 처리하기
    }

    public LocalTime modifyAttend(LocalDate date, LocalTime time) {
        LocalTime prevTime = attendanceInfo.get(date);
        addAttend(date, time);
        return prevTime;
    }

    public String getName() {
        return name;
    }

    private Map<LocalDate, LocalTime> init() {
        attendanceInfo = new TreeMap<>();
        for (int date = 1; date <= 31; date++) {
            attendanceInfo.put(LocalDate.of(2024, 12, date), null);
        }
        return attendanceInfo;
    }

}
