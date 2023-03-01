<%@ page import="java.time.LocalDateTime" %>
<%@ page import="kang.zero.wifiyeah.dto.request.RequestHistory" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="kang.zero.wifiyeah.service.Service" %>
<%@ page import="kang.zero.wifiyeah.dto.request.RequestDistance" %>
<%@ page import="kang.zero.wifiyeah.dto.response.ResponseWifi" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>kang shang</title>
  <style>

  </style>
</head>

<body>
<%-- home, history, migration --%>
<h1>와이파이 정보 구하기</h1>
</tr><a href="index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리 목록</a> |
<a href="save.jsp">Open API 와이파이 정보 가져오기</a>
<p></p>

<%-- 내 위치 가져오기, 근처 와이파이 20개 조회 --%>
<form action="list.jsp" method="post">
  LAT : <input type="text" id="lat", name="lat"> ,
  LNT : <input type="text" id="lnt", name="lnt">
  <input type="submit" value="근처 WIFI 정보 보기">
</form>

<button onclick="getLocation();">내 위치 가져오기</button>

<p></p>
<script>
  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function (position) {
        // string
        // 라이브러리로 구해서
        const latitude = position.coords.latitude.toString();
        const longitude = position.coords.longitude.toString();

        // document.getElementById("tag's id").innerHTML
        // input에 출력
        document.getElementById("lat").value = latitude;
        document.getElementById("lnt").value = longitude;

        console.log("위도 : " + latitude);
        console.log("경도 : " + longitude);
      });
    } else {
      window.alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
    }
  }
</script>
<%
  // request.getParameter("input 태그의 name 값")
  String lat = request.getParameter("lat");
  String lnt = request.getParameter("lnt");

  System.out.println("lat, lnt = " + lat + ", " + lnt);

  // RequestHistory
  RequestHistory requestHistory = RequestHistory.builder()
          .x(Float.parseFloat(lat))
          .y(Float.parseFloat(lnt))
          .createdTime(new Timestamp(System.currentTimeMillis()))
          .build();

  System.out.println("requestHistory = " + requestHistory);

  // saveHistory()
  Service service = new Service();
  service.saveHistory(requestHistory); // 확인

  // RequestDistance
  RequestDistance requestDistance = RequestDistance.builder()
          .LAT(Float.valueOf(lat))
          .LNT(Float.valueOf(lnt))
          .build();

  System.out.println("requestDistance = " + requestDistance); // 확인

  // calculateAndSaveDistance()
  service.calculateAndSaveDistance(requestDistance);
%>
<p></p>
<table>
  <thead>
  <tr bgcolor="#3cb371">
    <th><font color="white">거리(km)</font></th>
    <th><font color="white">관리번호</font></th>
    <th><font color="white">자치구</font></th>
    <th><font color="white">와이파이명</font></th>
    <th><font color="white">도로명주소</font></th>
    <th><font color="white">상세주소</font></th>
    <th><font color="white">설치위치(층)</font></th>
    <th><font color="white">설치유형</font></th>
    <th><font color="white">설치기관</font></th>
    <th><font color="white">서비스구분</font></th>
    <th><font color="white">망종류</font></th>
    <th><font color="white">설치년도</font></th>
    <th><font color="white">실내외구분</font></th>
    <th><font color="white">WIFI접속환경</font></th>
    <th><font color="white">X좌표</font></th>
    <th><font color="white">Y좌표</font></th>
    <th><font color="white">작업일자</font></th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <%
      // getWifi20()
      List<ResponseWifi> responseWifi20 = service.getWifi20();

      for (ResponseWifi responseWifi : responseWifi20) {
        System.out.println("responseWifi 리스트" + responseWifi);
      }

      for (ResponseWifi responseWifi : responseWifi20) {
        out.write("<tr>");
        out.write("<td>" + responseWifi.getDistance() + "</td>");
        out.write("<td>" + responseWifi.getManageNum() + "</td>");
        out.write("<td>" + responseWifi.getRegion() + "</td>");
        out.write("<td>" + responseWifi.getWifiName() + "</td>");
        out.write("<td>" + responseWifi.getRoadAddress() + "</td>");
        out.write("<td>" + responseWifi.getDetailedAddress() + "</td>");
        out.write("<td>" + responseWifi.getFloor() + "</td>");
        out.write("<td>" + responseWifi.getInstallationType() + "</td>");
        out.write("<td>" + responseWifi.getOrganization() + "</td>");
        out.write("<td>" + responseWifi.getClassifiedService() + "</td>");
        out.write("<td>" + responseWifi.getNetworkType() + "</td>");
        out.write("<td>" + responseWifi.getYearOfInstall() + "</td>");
        out.write("<td>" + responseWifi.getInOrOut() + "</td>");
        out.write("<td>" + responseWifi.getConnEnvironment() + "</td>");
        out.write("<td>" + responseWifi.getLAT() + "</td>");
        out.write("<td>" + responseWifi.getLNT() + "</td>");
        out.write("<td>" + responseWifi.getWorkTime() + "</td>");
        out.write("</tr>");
      }
    %>
  </tr>
  </tbody>
</table>
</body>
</html>
