<%@ page import="kang.zero.wifiyeah.service.Service" %>
<%@ page import="kang.zero.wifiyeah.dto.response.ResponseHistory" %>
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
<h1>위치 히스토리 목록</h1>
</tr><a href="index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리 목록</a> |
<a href="save.jsp">Open API 와이파이 정보 가져오기</a>
<p></p>

<table>
  <thead>
  <tr bgcolor="#3cb371">
    <th><font color="white">ID</font></th>
    <th><font color="white">X좌표</font></th>
    <th><font color="white">Y좌표</font></th>
    <th><font color="white">조회 일자</font></th>
    <th><font color="white">비고</font></th>
  </tr>
  </thead>

  <tbody>
  <tr>
    <%
      Service service = new Service();
      List<ResponseHistory> historyList = service.getHistoryList();
      for (ResponseHistory history : historyList) {
        out.write("<tr>");
        out.write("<td>" + history.getId() + "</td>");
        out.write("<td>" + history.getX() + "</td>");
        out.write("<td>" + history.getY() + "</td>");
        out.write("<td>" + history.getCreatedTime() + "</td>");
        out.write("<td>" + "<button>삭제</button>" + "</td>");
        out.write("</tr>");
      }
    %>
  </tr>
  </tbody>
</table>
</body>
</html>

