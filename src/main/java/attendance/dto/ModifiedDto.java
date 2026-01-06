package attendance.dto;

import attendance.domain.ArrivalInformation;

public class ModifiedDto {

    private final ArrivalInformation before;
    private final ArrivalInformation after;

    public ModifiedDto(ArrivalInformation before, ArrivalInformation after) {
        this.before = before;
        this.after = after;
    }

    public ArrivalInformation getBefore() {
        return before;
    }

    public ArrivalInformation getAfter() {
        return after;
    }

}
