<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Profile">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/profile.css">
    <link rel="stylesheet" type="text/css" href="/css/optionSelection.css">
</t:head>

<t:body>
        <jsp:include page="parts/_header.jsp" />

        <h1>Budget Planning</h1>

        <div class="sidebar">
            <p class="greetings">Hello, <c:out value="${username}"/>!</p>

            <a class="stuff-button" href="${s:mvcUrl("PC#showStatistics").build()}">Look statistic</a>
            <a class="stuff-button" href="${s:mvcUrl("PC#checkCurrency").build()}">Check currency rate</a>

            <c:if test="${not empty cashSavings}">
                <div class="cash-savings">
                    <h2>Cash savings</h2>
                    <br>
                    <c:forEach items="${cashSavings}" var="cash">
                        <div class="cash-saving">
                            <h3><c:out value="${cash.goal}"/></h3>
                            <p>
                                <fmt:formatNumber value="${cash.sum}" pattern="#,##0.00" />
                            </p>
                            <a href="${s:mvcUrl("PC#editCashSavingGet").arg(0, cash.id).build()}">Change</a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>

        <div class="main">

            <div class="month-data">
                <a class="main-widget" href="${s:mvcUrl("PC#showHistory").arg(0, false).build()}">My expenses from the 1st day of the month: <c:out value="${monthExpense}"/></a>
                <a class="main-widget" href="${s:mvcUrl("PC#showHistory").arg(0, true).build()}">My income from the 1st day of the month: <c:out value="${monthIncome}"/></a>
            </div>

            <div class="control-buttons">
                <a class="control-button" href="${s:mvcUrl("PC#addMoneyOperationGet").arg(0, true).build()}">Add income</a>
                <a class="control-button" href="${s:mvcUrl("PC#addMoneyOperationGet").arg(0, false).build()}">Add expense</a>
                <a class="control-button" href="${s:mvcUrl("PC#addCashSavingGet").build()}">Add cash saving</a>
            </div>

        </div>

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
