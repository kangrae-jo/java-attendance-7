package attendance.domain;

import java.time.LocalDateTime;

public class ArrivalTime {

    private final LocalDateTime arrivalTime;

    private ArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}
