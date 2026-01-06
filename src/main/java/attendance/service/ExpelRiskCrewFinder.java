package attendance.service;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.domain.Measure;
import attendance.domain.State;
import attendance.dto.MeasuresDto;
import java.util.HashMap;
import java.util.Map;

public class ExpelRiskCrewFinder {

    private ExpelRiskCrewFinder() {
    }

    public static Map<String, MeasuresDto> find(Crews crews) {
        Map<String, MeasuresDto> result = new HashMap<>();
        for (Crew crew : crews.getCrews()) {
            String name = crew.getName();
            MeasuresDto measuresDto = MeasuresDto.from(crew.getAttendanceInformation());
            result.put(name, measuresDto);
        }
        return result;
    }

    public static Measure findMeasure(String name, Map<String, MeasuresDto> dto) {
        MeasuresDto measuresDto = dto.get(name);
        Map<State, Integer> measures = measuresDto.getMeasures();
        int absent = measures.get(State.ABSENCE);
        int late = measures.get(State.LATE);
        absent += (late / 3);
        if (absent < 2) {
            return Measure.NONE;
        }
        if (absent < 3) {
            return Measure.WARING;
        }
        if (absent < 5) {
            return Measure.CONSUL;
        }
        return Measure.EXPEL;
    }

}
