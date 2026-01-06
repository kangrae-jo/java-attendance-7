package attendance.domain;

public enum Measure {

    NONE(""),
    WARING("경고"),
    CONSUL("상담"),
    EXPEL("제적");

    private final String name;

    Measure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
