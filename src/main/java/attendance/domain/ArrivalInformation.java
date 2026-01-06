package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.Duration;
import java.time.LocalDateTime;

public class ArrivalInformation {

    private final LocalDateTime arrivalTime;
    private final State state;

    private ArrivalInformation(LocalDateTime arrivalTime, State state) {
        this.arrivalTime = arrivalTime;
        this.state = state;
    }

    public static ArrivalInformation from(LocalDateTime arrivalTime) {
        Duration duration = Duration.between(DateTimes.now(), arrivalTime);
        long minute = duration.toMinutes();
        if (minute < 5) {
            return new ArrivalInformation(arrivalTime, State.ATTENDANCE);
        }
        if (minute < 30) {
            return new ArrivalInformation(arrivalTime, State.LATE);
        }
        return new ArrivalInformation(arrivalTime, State.ABSENCE);
    }

    public State getState() {
        return state;
    }

}
