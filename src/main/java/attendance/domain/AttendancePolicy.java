package attendance.domain;

public enum AttendancePolicy {

    MONDAY("월요일", Time.FROM_1300, Time.TO_1800),
    TUESDAY("화요일", Time.FROM_1000, Time.TO_1800),
    WEDNESDAY("수요일", Time.FROM_1000, Time.TO_1800),
    THURSDAY("목요일", Time.FROM_1000, Time.TO_1800),
    FRIDAY("금요일", Time.FROM_1000, Time.TO_1800);

    private final String name;
    private final Time start;
    private final Time end;

    AttendancePolicy(String name, Time start, Time end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

}
