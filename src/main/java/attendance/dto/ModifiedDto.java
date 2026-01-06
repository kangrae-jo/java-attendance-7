package attendance.dto;

import attendance.domain.ArrivalTime;

public class ModifiedDto {

    private final ArrivalTime before;
    private final ArrivalTime after;

    public ModifiedDto(ArrivalTime before, ArrivalTime after) {
        this.before = before;
        this.after = after;
    }

}
