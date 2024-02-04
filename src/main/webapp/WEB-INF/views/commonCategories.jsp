<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Common Categories">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</t:head>
<t:body>
    <jsp:include page="parts/_header.jsp" />

    <div class="main-form user-form">
        <h1>Income categories:</h1>
        <ul>
            <c:forEach var="c" items="${incomeCategories}">
                <li class="category-list"><c:out value="${c.name}" /></li>
            </c:forEach>
        </ul>
    </div>

    <div class="main-form user-form">
        <h1>Expense categories:</h1>
        <ul>
            <c:forEach var="c" items="${expenseCategories}">
                <li class="category-list"><c:out value="${c.name}" /></li>
            </c:forEach>
        </ul>
    </div>

    <a class="main-form user-form user-form__a" href="${s:mvcUrl("AC#addCommonCategoryGet").build()}">Add common category</a>

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
