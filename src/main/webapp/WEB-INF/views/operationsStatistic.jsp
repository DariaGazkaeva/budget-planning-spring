<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<t:head title="Statistic">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/statistic.css">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/tables.css">
    <link rel="stylesheet" type="text/css" href="/css/columns.css">
</t:head>

<t:body>
    <h1>My all time statistic</h1>
    <a class="main-form user-form user-form__a" href="${s:mvcUrl("PC#getProfile").build()}">To profile</a>
    <a class="main-form user-form user-form__a" href="${s:mvcUrl("PC#showCategoryStatistic").build()}">Show category statistic</a>
    <div class="columns">
        <div class="columns__half">
            <div class="user-form">
                <h1>Biggest income:</h1>
                <table class="money-operation">
                    <tr>
                        <th>Category</th>
                        <th>Sum</th>
                        <th>Date</th>
                        <th>Description</th>
                    </tr>
                    <c:forEach items="${income}" var="operation">
                        <tr>
                            <th><c:out value="${operation.category}"/></th>
                            <th><c:out value="${operation.sum}"/></th>
                            <th><c:out value="${operation.date}"/></th>
                            <th><c:out value="${operation.description}"/></th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="columns__half">
            <div class="user-form">
                <h1>Biggest expenses:</h1>
                <table class="money-operation">
                    <tr>
                        <th>Category</th>
                        <th>Sum</th>
                        <th>Date</th>
                        <th>Description</th>
                    </tr>
                    <c:forEach items="${expenses}" var="operation">
                        <tr>
                            <th><c:out value="${operation.category}"/></th>
                            <th><c:out value="${operation.sum}"/></th>
                            <th><c:out value="${operation.date}"/></th>
                            <th><c:out value="${operation.description}"/></th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</t:body>
</html>
