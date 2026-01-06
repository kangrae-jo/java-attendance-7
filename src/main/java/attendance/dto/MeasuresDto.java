package attendance.dto;

import attendance.domain.ArrivalInformation;
import attendance.domain.State;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MeasuresDto {

    private final Map<State, Integer> measures;

    public MeasuresDto(Map<State, Integer> measures) {
        this.measures = measures;
    }

    public static MeasuresDto from(Map<LocalDate, ArrivalInformation> information) {
        LocalDate start = LocalDate.of(2024, 12, 1);
        Map<State, Integer> measures = new HashMap<>();
        measures.put(State.ATTENDANCE, 0);
        measures.put(State.LATE, 0);
        measures.put(State.ABSENCE, 0);
        for (int i = 1; i < DateTimes.now().getDayOfMonth(); i++) {
            ArrivalInformation arrivalInformation = information.get(start.plusDays(i));
            if (arrivalInformation == null) {
                measures.put(State.ABSENCE, measures.get(State.ABSENCE) + 1);
                continue;
            }
            State state = arrivalInformation.getState();
            measures.put(state, measures.get(state) + 1);
            System.out.println("#### " + state.getName() + measures.get(state));
        }
        return new MeasuresDto(measures);
    }

    public Map<State, Integer> getMeasures() {
        return measures;
    }

}
