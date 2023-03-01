package kang.zero.wifiyeah;

import com.google.gson.*;
import kang.zero.wifiyeah.dto.request.RequestWifi;
import kang.zero.wifiyeah.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;

// http://openapi.seoul.go.kr:8088/6e48566c416b746f363571506a5a76/json/TbPublicWifiInfo/1/5

public class ApiExplorer {
    long start = System.nanoTime();
    Service wifiService = new Service();

    public Integer saveWifi() throws IOException, SQLException, ClassNotFoundException {
        // 초기화
        wifiService.deleteAllWifi();

        int count = 0;
        int i = 1;

        // (인스턴스 개수 / 1000)번
        while (true) {
            // URLEncoder -> StringBuilder -> String -> URL
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" + URLEncoder.encode("6e48566c416b746f363571506a5a76", "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));

            // 한번에 1000개 까지 가능 (1~1000, 1001~2001, ..)
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(i), "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(i + 999), "UTF-8"));
            URL url = new URL(urlBuilder.toString());

            // http connect
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());

            // InputStream -> BufferedReader
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            // BufferedReader -> StringBuilder
            // 헤더 + 바디 (바디의 "row"에 1000개)
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            conn.disconnect();

            // StringBuilder -> split("row")
            // String[0] = http header
            // String[1] = http body ("row" 제거하고 1000개_
            String[] result = sb.toString().split("row");
            // ":[{"":"", ..., "":""}]}}

            // 헤더와 바디 중에 바디 없을때
            if (!(result.length == 2)) {
                break;
            }

            // String[1] -> subString()
            // 맨앞 ": 와 맨뒤 }} 제거 (json 형태로 만들기)
            String jsonStr = result[1].substring(2, result[1].length() - 2);

            //  -> JsonArray (형태 변화는 없음)
            JsonArray jsonArray = (JsonArray) JsonParser.parseString(jsonStr);

            // 1000개를 1개씩
            for (int j = 0; j < jsonArray.size(); j++) {
                count += 1; // 인스턴스 개수

                // JsonArray -> JsonObject
                // [] 제거  -> {"":"", ..., "":""}
                JsonObject object = (JsonObject) jsonArray.get(j);

                // JsonObject -> RequestWifi
                // 키명 -> 필드명
                RequestWifi requestWifi = RequestWifi.builder()
                        .manageNum(object.get("X_SWIFI_MGR_NO").getAsString())
                        .region(object.get("X_SWIFI_WRDOFC").getAsString())
                        .wifiName(object.get("X_SWIFI_MAIN_NM").getAsString())
                        .roadAddress(object.get("X_SWIFI_ADRES1").getAsString())
                        .detailedAddress(object.get("X_SWIFI_ADRES2").getAsString())
                        .floor(object.get("X_SWIFI_INSTL_FLOOR").getAsString())
                        .installationType(object.get("X_SWIFI_INSTL_TY").getAsString())
                        .organization(object.get("X_SWIFI_INSTL_MBY").getAsString())
                        .classifiedService(object.get("X_SWIFI_SVC_SE").getAsString())
                        .networkType(object.get("X_SWIFI_CMCWR").getAsString())
                        .yearOfInstall(object.get("X_SWIFI_CNSTC_YEAR").getAsInt())
                        .inOrOut(object.get("X_SWIFI_INOUT_DOOR").getAsString())
                        .connEnvironment(object.get("X_SWIFI_REMARS3").getAsString())
                        .LAT(object.get("LAT").getAsFloat())
                        .LNT(object.get("LNT").getAsFloat())
                        .workTime(Timestamp.valueOf(object.get("WORK_DTTM").getAsString()))
                        .build();

                // 디비에 저장
                wifiService.saveWifi(requestWifi);
            }

            // 1001로 두번째 턴 돌수 있도록
            i += 1000;
        }

        long end = System.nanoTime();
        double workTime = (end - start) / 1000000000.0;
        System.out.println("수행시간 : " + workTime + "초");

        return count;
    }
}


