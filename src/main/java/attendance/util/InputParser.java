package attendance.util;

import attendance.domain.Crew;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private InputParser() {
    }

    public static List<Crew> parseNames(List<String> lines) {
        try {
            List<Crew> crews = new ArrayList<>();
            for (int i = 1; i < lines.size(); i++) {
                String[] split = lines.get(i).split(",");
                String name = split[0].strip();
                String dateTime = split[1].strip();
                addCrews(crews, name, dateTime);
            }
            return crews;
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 읽을 수 없는 파일입니다.");
        }
    }

    public static LocalTime parseLocalTime(String input) {
        try {
            return LocalTime.parse(input.strip() + ":00");
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    public static LocalDate parseLocalDate(String input) {
        try {
            return LocalDate.of(2024, 12, Integer.parseInt(input.strip()));
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    private static void addCrews(List<Crew> crews, String name, String dateTime) {
        String[] split = dateTime.split(" ");
        LocalDate date = LocalDate.parse(split[0].strip());
        LocalTime time = LocalTime.parse(split[1].strip() + ":00");

        for (Crew crew : crews) {
            if (name.equals(crew.getName())) {
                crew.addAttend(date, time);
                return;
            }
        }
        Crew crew = new Crew(name);
        crew.addAttend(date, time);
        crews.add(crew);
    }

}
