package attendance.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public enum State {

    ATTENDANCE("출석", 0, 0),
    TARDINESS("지각", 5, 1),
    ABSENCE("결석", 30, 3);

    private final String name;
    private final int minute;
    private final int point;

    State(String name, int minute, int point) {
        this.name = name;
        this.minute = minute;
        this.point = point;
    }

    public static State from(LocalDate localDate, LocalTime arrivalTime) {
        LocalTime due = LocalTime.of(10, 0, 0);
        if (localDate.getDayOfWeek().getValue() == 1) {
            due = LocalTime.of(13, 0, 0);
        }

        Duration duration = Duration.between(due, arrivalTime);
        for (State state : State.values()) {
            if (state.minute < duration.toMinutes()) {
                return state;
            }
        }
        return ATTENDANCE;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }
    
}
