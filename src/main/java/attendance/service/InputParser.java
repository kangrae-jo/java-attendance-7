package attendance.service;

import attendance.domain.ArrivalInformation;
import attendance.domain.Crew;
import attendance.domain.Crews;
import java.time.LocalDate;
import java.time.LocalTime;
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
            String time = split1[1];
            crew.attend(LocalDate.parse(date), ArrivalInformation.from(LocalTime.parse(time + ":00")));
        }

        return Crews.from(crews);
    }

    public static LocalTime parseTime(String time) {
        try {
            return LocalTime.parse(time + ":00");
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }

    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse("2024-12-" + date);
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }

}
