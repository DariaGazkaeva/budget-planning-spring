<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Currency">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/rates.css">
    <link rel="stylesheet" type="text/css" href="/css/submitButton.css">
</t:head>
<t:body>
    <jsp:include page="parts/_header.jsp" />

    <c:if test="${empty rates}">
        <div class="main-form user-form">
            <h1>Check currency rate:</h1>
            <form:form method="GET" action="${s:mvcUrl('PC#showCurrency').build()}" modelAttribute="currencyRate">
                <form:label path="currency1">Buy</form:label>
                <form:select path="currency1">
                    <form:option value="USD" label="USD"/>
                    <form:option value="EUR" label="EUR"/>
                    <form:option value="RUB" label="RUB"/>
                </form:select>
                <form:label path="currency1">For</form:label>
                <form:select path="currency2">
                    <form:option value="USD" label="USD"/>
                    <form:option value="EUR" label="EUR"/>
                    <form:option value="RUB" label="RUB"/>
                </form:select>
                <input type="submit" value="Check">
            </form:form>
        </div>
        <a class="user-form user-form__a" href="${s:mvcUrl("PC#getProfile").build()}">To profile</a>
    </c:if>

    <c:if test="${not empty rates}">
        <div class="main-form user-form">
            <div class="rates">
                <h2>Currency rate</h2>
                <br>
                <c:forEach items="${rates}" var="rate">
                    <div class="rate">
                        <h3>Buy ${rate.currency1}</h3>
                        <h3>for ${rate.rates} ${rate.currency2}</h3>
                    </div>
                </c:forEach>
            </div>
            <a class="user-form__a" href="${s:mvcUrl("PC#checkCurrency").build()}">Check another currency rate</a>

            <a class="user-form__a" href="${s:mvcUrl("PC#getProfile").build()}">To profile</a>
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="main-form user-form">
            <c:forEach var="e" items="${error}">
                <p>
                    <c:out value="${e}"/>
                </p>
            </c:forEach>
        </div>
    </c:if>

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
