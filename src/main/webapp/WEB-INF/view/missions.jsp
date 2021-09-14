<%--
  Created by IntelliJ IDEA.
  User: Hubert
  Date: 05.09.2021
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
asdas
<c:forEach items="${missions}" var="mission">
    <tr>
        <td>${mission.name}</td>
<%--        <td>${book.name}</td>--%>
<%--        <td>${book.author}</td>--%>
    </tr>
</c:forEach>
</body>
</html>
