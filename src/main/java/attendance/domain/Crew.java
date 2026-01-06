package attendance.domain;

import java.time.LocalDateTime;
import java.util.Map;

public class Crew {

    private final String name;
    private final Map<LocalDateTime, ArrivalTime> attendance;

    private Crew(String name, Map<LocalDateTime, ArrivalTime> attendance) {
        this.name = name;
        this.attendance = attendance;
    }

}
