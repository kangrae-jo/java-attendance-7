package attendance.domain;

import attendance.dto.ModifiedDto;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Crews {

    private final List<Crew> crews;

    private Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public static Crews from(List<Crew> crews) {
        return new Crews(new ArrayList<>(crews));
    }

    public ArrivalInformation attend(String crewName, LocalTime arrivalTime) {
        Crew crew = findCrewByName(crewName);
        ArrivalInformation arrivalInformation = ArrivalInformation.from(arrivalTime);
        crew.attend(DateTimes.now().toLocalDate(), arrivalInformation);
        return arrivalInformation;
    }

    public ModifiedDto modify(String crewName, LocalDate date, LocalTime time) {
        Crew crew = findCrewByName(crewName);
        return crew.modify(date, ArrivalInformation.from(time));
    }

    public Map<LocalDate, ArrivalInformation> getAttendanceInformationFrom(String crewName) {
        Crew crew = findCrewByName(crewName);
        return crew.getAttendanceInformation();
    }

    public void isExist(String crewName) {
        findCrewByName(crewName);
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
        throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
    }

}
