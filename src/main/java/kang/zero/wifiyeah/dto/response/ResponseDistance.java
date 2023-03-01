package kang.zero.wifiyeah.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDistance {
    private Double distance;
    private String manageNum;

    @Builder
    public ResponseDistance(Double distance, String manageNum) {
        this.distance = distance;
        this.manageNum = manageNum;
    }

    @Override
    public String toString() {
        return "ResponseDistance{" +
                "distance=" + distance +
                ", manageNum='" + manageNum + '\'' +
                '}';
    }
}
