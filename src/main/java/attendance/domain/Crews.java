package attendance.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Crews {

    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public Map<LocalDate, ArrivalInformation> getAttendanceInformationFrom(String crewName) {
        Crew crew = findCrewByName(crewName);
        return crew.getAttendanceInformation();
    }

    public List<Crew> getCrews() {
        return List.copyOf(crews);
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
