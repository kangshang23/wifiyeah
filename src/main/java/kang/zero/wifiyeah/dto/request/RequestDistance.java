package kang.zero.wifiyeah.dto.request;

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

    @Override
    public String toString() {
        return "RequestDistance{" +
                "LAT=" + LAT +
                ", LNT=" + LNT +
                '}';
    }
}
