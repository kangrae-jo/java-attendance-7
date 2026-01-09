package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimeWithState {

    private final LocalTime localTime;
    private final State state;

    public LocalTimeWithState(LocalDate localDate, LocalTime localTime) {
        this.localTime = localTime;
        this.state = State.from(localDate, localTime);
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public State getState() {
        return state;
    }
    
}
