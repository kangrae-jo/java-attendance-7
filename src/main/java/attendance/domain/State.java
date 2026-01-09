package attendance.domain;

public enum State {

    TARDINESS("지각"),
    ABSENCE("결석");

    private final String name;

    State(String name) {
        this.name = name;
    }
    
}
