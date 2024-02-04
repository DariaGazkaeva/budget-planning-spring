<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Statistics">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/statistic.css">
</t:head>

<t:body>
    <h1>My all time statistic</h1>
    <div class="statistic-options">
        <a class="stuff-button" href="${s:mvcUrl("PC#getProfile").build()}">To profile</a>
        <a class="stuff-button" href="${s:mvcUrl("PC#showCategoryStatistic").build()}">Show category statistic</a>
        <a class="stuff-button" href="${s:mvcUrl("PC#showOperationStatistic").build()}">Show biggest money operation</a>
    </div>
</t:body>
</html>
