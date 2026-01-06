package attendance.domain;

public enum State {

    ATTENDANCE("출석"),
    LATE("지각"),
    ABSENCE("결석");

    private final String name;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
