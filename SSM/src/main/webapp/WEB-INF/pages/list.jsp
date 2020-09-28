<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/9/11
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>list</title>
</head>
<body>
    <h3>查询了所有的账户信息</h3>
    ${list}
    <c:forEach items="${list}" var="account">
        ${account.name}
    </c:forEach>
</body>
</html>
