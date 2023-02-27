package kang.zero.wifiyeah.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RequestHistory {
    private Float x;
    private Float y;
    private Timestamp createdTime;

    @Builder
    public RequestHistory(Float x, Float y, Timestamp createdTime) {
        this.x = x;
        this.y = y;
        this.createdTime = createdTime;
    }
}
