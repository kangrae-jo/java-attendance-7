package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimeWithState {

    private final LocalTime localTime;
    private final State state;

    private LocalTimeWithState(LocalTime localTime, State state) {
        this.localTime = localTime;
        this.state = state;
    }

    public static LocalTimeWithState from(LocalDate localDate, LocalTime localTime) {
        return new LocalTimeWithState(localTime, State.from(localDate, localTime));
    }

    public static LocalTimeWithState of(LocalTime localTime, State state) {
        return new LocalTimeWithState(localTime, state);
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public State getState() {
        return state;
    }

}
