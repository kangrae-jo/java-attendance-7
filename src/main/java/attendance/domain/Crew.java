package attendance.domain;

import attendance.dto.ModifiedDto;
import java.time.LocalDate;
import java.util.Map;

public class Crew {

    private final String name;
    private final Map<LocalDate, ArrivalInformation> attendance;

    private Crew(String name, Map<LocalDate, ArrivalInformation> attendance) {
        this.name = name;
        this.attendance = attendance;
    }

    public void attend(LocalDate today, ArrivalInformation arrivalInformation) {
        if (attendance.get(today) != null) {
            throw new IllegalArgumentException("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해주세요.");
        }
        // TODO: 주말 및 공휴일 출석 시도는 오류처리 (이건 Modify에서도 동일하니 메서드로 뽑아내기 또는 Arrival에서 관리하기)
        attendance.put(today, arrivalInformation);
    }

    public ModifiedDto modify(LocalDate today, ArrivalInformation after) {
        ArrivalInformation before = attendance.get(today);
        if (before == null) {
            throw new IllegalArgumentException("출석 기록이 없습니다. 필요한 경우 출석 확인 기능을 이용해주세요.");
        }
        attendance.put(today, after);

        return new ModifiedDto(before, after);
    }

    public Map<LocalDate, ArrivalInformation> getAttendanceInformation() {
        return Map.copyOf(attendance);
    }

    public boolean isSameName(String name) {
        return name.equals(this.name);
    }

}
