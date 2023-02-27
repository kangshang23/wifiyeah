package kang.zero.wifiyeah.dto.request;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
public class RequestWifi {
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
    public RequestWifi(String manageNum, String region, String wifiName, String roadAddress, String detailedAddress, String floor, String installationType, String organization, String classifiedService, String networkType, int yearOfInstall, String inOrOut, String connEnvironment, Float LAT, Float LNT, Timestamp workTime) {
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

    public RequestWifi() {

    }

    @Override
    public String toString() {
        return "RequestWifi{" +
                "manageNum='" + manageNum + '\'' +
                ", region='" + region + '\'' +
                ", wifiName='" + wifiName + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", floor='" + floor + '\'' +
                ", installationType='" + installationType + '\'' +
                ", organization='" + organization + '\'' +
                ", classifiedService='" + classifiedService + '\'' +
                ", networkType='" + networkType + '\'' +
                ", yearOfInstall=" + yearOfInstall +
                ", inOrOut='" + inOrOut + '\'' +
                ", connEnvironment='" + connEnvironment + '\'' +
                ", LAT=" + LAT +
                ", LNT=" + LNT +
                ", workTime=" + workTime +
                '}';
    }
}
