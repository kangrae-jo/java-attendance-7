package attendance.domain;

import java.util.List;

public class Crews {

    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public void getAttendanceInformationFrom(String crewName) {

    }

    private Crew findCrewByName(String crewName) {
        for (Crew crew : crews) {
            if (crew.isSameName(crewName)) {
                return crew;
            }
        }
        throw new IllegalArgumentException("등록되지 않은 닉네임입니다.");
    }

}
