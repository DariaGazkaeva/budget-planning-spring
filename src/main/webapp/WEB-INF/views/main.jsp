<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Budget Planning">
    <link rel="stylesheet" type="text/css" href="/css/optionSelection.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</t:head>

<t:body>
    <div class="option-selection">
        <a href="${s:mvcUrl("UC#registerGet").build()}">Register</a>
        <a href="${s:mvcUrl("UC#login").build()}">Log In</a>
    </div>
</t:body>
</html>
