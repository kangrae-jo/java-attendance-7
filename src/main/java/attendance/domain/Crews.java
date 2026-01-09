package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Crews {

    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public void hasThisCrew(String name) {
        findByName(name);
    }

    public LocalTimeWithState addAttendByName(String name, LocalDate arrivalDate, LocalTime arrivalTime) {
        Crew crew = findByName(name);
        return crew.addAttend(arrivalDate, arrivalTime);
    }

    public List<LocalTimeWithState> modifyAttendByName(String name, LocalDate date, LocalTime time) {
        Crew crew = findByName(name);
        return crew.modifyAttend(date, time);
    }

    public Map<LocalDate, LocalTimeWithState> getAttendInformationByName(String name) {
        Crew crew = findByName(name);
        return crew.getAttendInformation();
    }

    private Crew findByName(String name) {
        for (Crew crew : crews) {
            if (crew.getName().equals(name)) {
                return crew;
            }
        }
        throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
    }

}
