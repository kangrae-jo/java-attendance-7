package attendance.domain;

public enum Policy {

    WARNING("경고", 2),
    INTERVIEW("면담", 3),
    EXPULSION("제적", 6);

    private final String name;
    private final int threshold;

    Policy(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

}
