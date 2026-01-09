package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Crews {

    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = new ArrayList<>(crews);
        for (Crew crew : crews) {
            crew.addAbsent();
        }
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

    public States getStatesByName(String name) {
        Crew crew = findByName(name);
        Map<State, Integer> states = crew.getStates();
        return States.from(crew.getName(), states);
    }

    public List<States> getStates() {
        List<States> result = new ArrayList<>();
        for (Crew crew : crews) {
            Map<State, Integer> states = crew.getStates();
            result.add(States.from(crew.getName(), states));
        }
        return result;
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
