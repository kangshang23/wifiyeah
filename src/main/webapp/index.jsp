<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>kang shang</title>
    <style>
        .divbox {display: inline-flex;}
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
<div class="divbox">
<form action="list.jsp" method="post">
    LAT : <input type="text" id="lat", name="lat"> ,
    LNT : <input type="text" id="lnt", name="lnt">
    <input onclick="getWifi();" type="submit" value="근처 WIFI 정보 보기">
</form>

<button onclick="getLocation();">내 위치 가져오기</button>
</div>

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

    function getWifi() {
        let lat = document.getElementById("lat").value;
        let lnt = document.getElementById("lnt").value;
        if (lat == null || lat === "") {
            alert("LAT값을 입력해주세요.");
            document.getElementById("x").focus();
            return;
        } else if (lnt == null || lnt === "") {
            alert("LNT값을 입력해주세요.");
            document.getElementById("y").focus();
            return;
        }
    }
</script>
<p></p>

<%-- thead : 스키마 --%>
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

    <%-- tbody : 인스턴스  --%>
    <tbody>
    <tr>
    </tr>
    </tbody>
</table>

</body>
</html>