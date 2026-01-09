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

    private Map<LocalDate, LocalTime> init() {
        attendanceInfo = new TreeMap<>();
        for (int date = 1; date <= 31; date++) {
            attendanceInfo.put(LocalDate.of(2024, 12, date), null);
        }
        return attendanceInfo;
    }

}
