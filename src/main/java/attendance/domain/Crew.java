package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Crew {

    private final String name;
    private Map<LocalDate, LocalTimeWithState> attendanceInfo;

    public Crew(String name) {
        this.name = name;
        this.attendanceInfo = init();
    }

    public void addAbsent() {
        for (int i = 1; i < DateTimes.now().getDayOfMonth() - 1; i++) {
            LocalDate localDate = LocalDate.of(2024, 12, i);
            if (localDate.getDayOfWeek().getValue() == 6 || localDate.getDayOfWeek().getValue() == 7) {
                continue;
            }
            attendanceInfo.computeIfAbsent(localDate, k ->
                    LocalTimeWithState.of(null, State.ABSENCE));
        }
    }

    public LocalTimeWithState addAttend(LocalDate date, LocalTime time) {
        if (attendanceInfo.get(date) != null) {
            throw new IllegalArgumentException("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해주세요.");
        }

        if (time.isAfter(LocalTime.of(23, 0)) || time.isBefore(LocalTime.of(8, 0))) {
            throw new IllegalArgumentException("캠퍼스 운영 시간에만 출석이 가능합니다.");
        }

        LocalTimeWithState localTimeWithState = LocalTimeWithState.from(date, time);
        attendanceInfo.put(date, localTimeWithState);
        return localTimeWithState;
    }

    public List<LocalTimeWithState> modifyAttend(LocalDate date, LocalTime time) {
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        if (localDateTime.isAfter(DateTimes.now())) {
            throw new IllegalArgumentException("아직 수정할 수 없습니다.");
        }
        if (time.isAfter(LocalTime.of(23, 0)) || time.isBefore(LocalTime.of(8, 0))) {
            throw new IllegalArgumentException("캠퍼스 운영 시간에만 출석이 가능합니다.");
        }

        LocalTime prevTime = attendanceInfo.get(date).getLocalTime();
        LocalTimeWithState localTimeWithState = LocalTimeWithState.from(date, time);
        attendanceInfo.put(date, localTimeWithState);

        return List.of(LocalTimeWithState.from(date, prevTime), localTimeWithState);
    }

    public Map<LocalDate, LocalTimeWithState> getAttendInformation() {
        return attendanceInfo;
    }

    public Map<State, Integer> getStates() {
        Map<State, Integer> states = new HashMap<>();
        for (State state : State.values()) {
            states.put(state, 0);
        }

        for (LocalDate localDate : attendanceInfo.keySet()) {
            State state = attendanceInfo.get(localDate).getState();
            states.put(state, states.get(state) + 1);
        }
        return states;
    }

    public String getName() {
        return name;
    }

    private Map<LocalDate, LocalTimeWithState> init() {
        attendanceInfo = new TreeMap<>();
        for (int i = 1; i < DateTimes.now().getDayOfMonth() - 1; i++) {
            LocalDate localDate = LocalDate.of(2024, 12, i);
            if (localDate.getDayOfWeek().getValue() == 6 || localDate.getDayOfWeek().getValue() == 7) {
                continue;
            }
            attendanceInfo.put(localDate, null);
        }
        return attendanceInfo;
    }

}
