package attendance.domain;

import java.util.List;

public class Crews {

    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public void hasThisCrew(String name) {
        for (Crew crew : crews) {
            if (crew.getName().equals(name)) {
                return;
            }
        }
        throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
    }
}
