<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<t:head title="Login">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/submitButton.css">
</t:head>

<t:body>
    <div class="main-form user-form">
        <h1>Login</h1>
        <form:form method="POST" modelAttribute="loginDto">
            <form:label path="email">Email</form:label>
            <form:input path="email"/>
            <c:if test="${showErrors == true}">
                <form:errors path="email" />
            </c:if>
            <br>

            <form:label path="password">Password</form:label>
            <form:password path="password"/>
            <c:if test="${showErrors == true}">
                <form:errors path="password" />
            </c:if>
            <br>
            <input type="submit" value="Log In" />
        </form:form>
    </div>
    <a class="user-form user-form__a" href="${s:mvcUrl("UC#registerGet").build()}">To registration</a>

</t:body>
</html>
