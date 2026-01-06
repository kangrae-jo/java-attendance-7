package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Crews {

    private final List<Crew> crews;

    private Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public static Crews from(Map<String, Crew> crews) {
        return new Crews(crews.values().stream().toList());
    }

    public void attend(String crewName, LocalTime arrivalTime) {
        Crew crew = findCrewByName(crewName);
        crew.attend(DateTimes.now().toLocalDate(), ArrivalInformation.from(arrivalTime));
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
