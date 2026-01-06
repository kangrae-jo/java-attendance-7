package attendance.domain;

public enum Time {

    FROM_1000(10, 0),
    FROM_1300(13, 0),
    TO_1800(18, 0);

    private final int hour;
    private final int minute;

    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

}
