<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Registration">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/submitButton.css">
</t:head>

<t:body>

    <div class="main-form user-form">
        <h1>Registration</h1>

        <c:if test="${not empty errors}">
            <p>WRONG</p>
            <c:forEach items="${errors}" var="error">
                <p>
                    <c:out value="${error}" />
                </p>
            </c:forEach>
        </c:if>

        <form:form method="POST" modelAttribute="registrationDto">
            <form:label path="name">Name</form:label>
            <form:input path="name"/>
            <form:errors path="name" /><br>

            <form:label path="email">Email</form:label>
            <form:input path="email"/>
            <form:errors path="email" /><br>

            <form:label path="password">Password</form:label>
            <form:password path="password"/>
            <form:errors path="password" /><br>

            <form:label path="agreement">I agree to the processing of personal data</form:label>
            <form:checkbox path="agreement"/>
            <form:errors path="agreement"/><br>

            <input type="submit" value="Register" />
        </form:form>
    </div>
    <a class="user-form user-form__a" href="${s:mvcUrl("UC#login").build()}">To login</a>

</t:body>
</html>
