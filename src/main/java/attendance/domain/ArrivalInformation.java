package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.Duration;
import java.time.LocalTime;

public class ArrivalInformation {

    private final LocalTime arrivalTime;
    private final State state;

    private ArrivalInformation(LocalTime arrivalTime, State state) {
        this.arrivalTime = arrivalTime;
        this.state = state;
    }

    public static ArrivalInformation from(LocalTime arrivalTime) {
        Duration duration = Duration.between(arrivalTime, DateTimes.now().toLocalTime());
        long minute = duration.toMinutes();
        if (minute < 5) {
            return new ArrivalInformation(arrivalTime, State.ATTENDANCE);
        }
        if (minute < 30) {
            return new ArrivalInformation(arrivalTime, State.LATE);
        }
        return new ArrivalInformation(arrivalTime, State.ABSENCE);
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public State getState() {
        return state;
    }

}
