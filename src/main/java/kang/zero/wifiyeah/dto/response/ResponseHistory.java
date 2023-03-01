package kang.zero.wifiyeah.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ResponseHistory {
    private Integer id;
    private Float x;
    private Float y;
    private Timestamp createdTime;

    @Builder
    public ResponseHistory(Integer id, Float x, Float y, Timestamp createdTime) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "ResponseHistory{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", createdTime=" + createdTime +
                '}';
    }
}
