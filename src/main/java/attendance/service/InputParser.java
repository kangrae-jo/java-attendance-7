package attendance.service;

import attendance.domain.ArrivalInformation;
import attendance.domain.Crew;
import attendance.domain.Crews;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParser {

    private InputParser() {
    }

    public static Crews parseCrews(List<String> lines) {
        Map<String, Crew> crews = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split(",");
            String name = split[0];
            Crew crew = crews.getOrDefault(name, Crew.from(name));

            String dateAndTime = split[1];
            String[] split1 = dateAndTime.split(" ");
            String date = split1[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            crew.attend(LocalDate.parse(date), ArrivalInformation.from(LocalDateTime.parse(dateAndTime, formatter)));
        }

        return Crews.from(crews);
    }

}
