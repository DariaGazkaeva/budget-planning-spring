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
        <a href="${s:mvcUrl("AC#addCommonCategoryGet").build()}">Add common category</a>
        <a href="${s:mvcUrl("AC#showCommonCategories").build()}">All common categories</a>
    </div>
</t:body>
</html>
