package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Crews {

    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public void hasThisCrew(String name) {
        findByName(name);
    }

    public void addAttendByName(String name, LocalDate arrivalDate, LocalTime arrivalTime) {
        Crew crew = findByName(name);
        crew.addAttend(arrivalDate, arrivalTime);
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
