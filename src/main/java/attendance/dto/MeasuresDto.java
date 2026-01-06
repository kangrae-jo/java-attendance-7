package attendance.dto;

import attendance.domain.ArrivalInformation;
import attendance.domain.State;
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
        for (int i = 0; i < 31; i++) {
            ArrivalInformation arrivalInformation = information.get(start.plusDays(i));
            State state = arrivalInformation.getState();
            measures.putIfAbsent(state, measures.getOrDefault(state, 0) + 1);
        }

        return new MeasuresDto(measures);
    }

}
