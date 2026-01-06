package attendance.domain;

import java.time.LocalDateTime;

public class ArrivalInformation {

    private final LocalDateTime arrivalTime;

    private ArrivalInformation(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


}
