package kang.zero.wifiyeah.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDistance {
    private Float LAT;
    private Float LNT;

    @Builder
    public RequestDistance(Float LAT, Float LNT) {
        this.LAT = LAT;
        this.LNT = LNT;
    }
}
