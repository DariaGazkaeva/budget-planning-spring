<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="History">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/tables.css">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/submitButton.css">
    <script src="/js/defaultDate.js"></script>
</t:head>

<t:body>
    <jsp:include page="parts/_header.jsp" />

    <div class="main-form user-form">
        <h1>Look for another period:</h1>
        <form:form method="POST" modelAttribute="periodDto">
            <form:input cssClass="first-date-money-operation" type="date" path="begin"/>
            <form:errors path="begin" />
            <br>
            <form:input cssClass="date-money-operation" type="date" path="end"/>
            <form:errors path="end" />
            <input type="submit" value="Look">
        </form:form>
    </div>

    <c:if test="${empty errors}">
        <table class="money-operation">
            <tr>
                <th>Category</th>
                <th>Sum</th>
                <th>Date</th>
                <th>Description</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${moneyOperations}" var="operation">
                <tr>
                    <th><c:out value="${operation.category}"/></th>
                    <th><c:out value="${operation.sum}"/></th>
                    <th><c:out value="${operation.date}"/></th>
                    <th><c:out value="${operation.description}"/></th>
                    <th>
                        <a href="${s:mvcUrl("PC#editMoneyOperationGet").arg(0, operation.id).build()}" class="history-ctrl-btn">Edit</a>
                    </th>
                    <th>
                        <a href="${s:mvcUrl("PC#deleteMoneyOperation").arg(0, operation.id).build()}" class="history-ctrl-btn">Delete</a>
                    </th>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a class="user-form user-form__a" href="${s:mvcUrl("PC#getProfile").build()}">To profile</a>

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
