<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Statistic">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/statistic.css">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
</t:head>

<t:body>
    <h1>My all time statistic</h1>
    <a class="user-form__a" href="${s:mvcUrl("PC#getProfile").build()}">To profile</a>
    <a class="user-form__a" href="${s:mvcUrl("PC#showOperationStatistic").build()}">Show biggest money operation</a>
    <div class="donuts">
        <div class="donut">
            <h2>Income</h2>
            <div id="income" style="height: 50vh; "></div>
        </div>
        <div class="donut">
            <h2>Expense</h2>
            <div id="expense" style="height: 50vh;"></div>
        </div>
    </div>
    <script src="/js/statistic.js"></script>
</t:body>
</html>
