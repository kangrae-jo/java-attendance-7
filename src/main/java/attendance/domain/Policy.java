package attendance.domain;

import java.util.Map;

public enum Policy {

    NONE("", 0),
    WARNING("경고", 2),
    INTERVIEW("면담", 3),
    EXPULSION("제적", 6);

    private final String name;
    private final int threshold;

    Policy(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static Policy from(Map<State, Integer> states) {
        int absent = states.get(State.ABSENCE);
        absent += states.get(State.TARDINESS) / State.ABSENCE.getPoint();

        for (Policy policy : Policy.values()) {
            if (policy.threshold <= absent) {
                return policy;
            }
        }
        return NONE;
    }

}
