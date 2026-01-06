package attendance.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ArrivalInformation {

    private final LocalTime arrivalTime;
    private final State state;

    private ArrivalInformation(LocalTime arrivalTime, State state) {
        this.arrivalTime = arrivalTime;
        this.state = state;
    }

    public static ArrivalInformation from(LocalDate date, LocalTime arrivalTime) {
        LocalTime time = AttendancePolicy.from(date).getStart();
        Duration duration = Duration.between(time, arrivalTime);
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
