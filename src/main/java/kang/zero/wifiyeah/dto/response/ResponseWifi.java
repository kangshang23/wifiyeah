package kang.zero.wifiyeah.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ResponseWifi {
    Float distance; // 와이파이와 사용자 간의 거리
    String manageNum;
    String region;
    String wifiName;
    String roadAddress;
    String detailedAddress;
    String floor;
    String installationType;
    String organization;
    String classifiedService;
    String networkType;
    int yearOfInstall;
    String inOrOut;
    String connEnvironment;
    Float LAT;
    Float LNT;
    Timestamp workTime;

    @Builder
    public ResponseWifi(Float distance, String manageNum, String region, String wifiName, String roadAddress, String detailedAddress, String floor, String installationType, String organization, String classifiedService, String networkType, int yearOfInstall, String inOrOut, String connEnvironment, Float LAT, Float LNT, Timestamp workTime) {
        this.distance = distance;
        this.manageNum = manageNum;
        this.region = region;
        this.wifiName = wifiName;
        this.roadAddress = roadAddress;
        this.detailedAddress = detailedAddress;
        this.floor = floor;
        this.installationType = installationType;
        this.organization = organization;
        this.classifiedService = classifiedService;
        this.networkType = networkType;
        this.yearOfInstall = yearOfInstall;
        this.inOrOut = inOrOut;
        this.connEnvironment = connEnvironment;
        this.LAT = LAT;
        this.LNT = LNT;
        this.workTime = workTime;
    }

}
