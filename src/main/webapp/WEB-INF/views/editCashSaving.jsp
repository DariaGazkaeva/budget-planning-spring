<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Edit">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/submitButton.css">
</t:head>
<t:body>

    <jsp:include page="parts/_header.jsp" />

    <div class="main-form user-form">

        <form:form method="POST" modelAttribute="cashSaving">
            <form:label path="goal">Goal</form:label>
            <form:input path="goal"/>
            <form:errors path="goal"/><br>

            <form:label path="sum">Sum</form:label>
            <form:input path="sum"/>
            <form:errors path="sum" /><br>

            <input type="submit" value="Save" />
        </form:form>

        <c:if test="${not empty cashSaving.id}">
            <a class="user-form__a" href="${s:mvcUrl("PC#deleteCashSaving").arg(0, cashSaving.id).build()}">Delete</a>
        </c:if>

        <a class="user-form__a" href="${s:mvcUrl("PC#getProfile").build()}">To profile</a>
    </div>

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
