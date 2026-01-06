package attendance.service;

import attendance.domain.Crew;
import attendance.domain.Crews;
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

}
