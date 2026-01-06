package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public enum AttendancePolicy {

    MONDAY(1, LocalTime.of(13, 0), LocalTime.of(18, 0)),
    TUESDAY(2, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    WEDNESDAY(3, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    THURSDAY(4, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    FRIDAY(5, LocalTime.of(10, 0), LocalTime.of(18, 0));

    private final int value;
    private final LocalTime start;
    private final LocalTime end;

    AttendancePolicy(int value, LocalTime start, LocalTime end) {
        this.value = value;
        this.start = start;
        this.end = end;
    }

    public static AttendancePolicy from(LocalDate date) {
        for (AttendancePolicy policy : AttendancePolicy.values()) {
            if (policy.value == date.getDayOfWeek().getValue()) {
                return policy;
            }
        }
        throw new IllegalArgumentException("[ERROR] 없는 요일입니다.");
    }

    public LocalTime getStart() {
        return start;
    }
    
}
