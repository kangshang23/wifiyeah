<%@ page import="kang.zero.wifiya.ApiExplorer" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <title>kang shang</title>
</head>
<body>
<%
  ApiExplorer apiExplorer = new ApiExplorer();
  Integer numOfWifi = apiExplorer.SaveWifi();
%>
<p></p>
<div style="text-align: center"><h1><%=numOfWifi%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1></div>
<p></p>
<div style="text-align: center"><a href="index.jsp">홈으로 가기</a></div>
</body>
</html>
