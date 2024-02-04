<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Error ${statusCode}</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/profile.css">
</head>
<body>
<c:if test="${not empty statusCode}">
    <p class="error">Error <c:out value="${statusCode}"/>!</p>
    <img class="displayed" src="https://http.cat/${statusCode}" alt="${statusCode}" />
</c:if>
<c:if test="${empty statusCode}">
    <h1>Bad request!</h1>
</c:if>
</body>
</html>
