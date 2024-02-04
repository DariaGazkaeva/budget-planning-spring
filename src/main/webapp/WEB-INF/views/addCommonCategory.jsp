<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Common Category">
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</t:head>
<t:body>
    <jsp:include page="parts/_header.jsp" />

    <div class="main-form user-form create-category-widget">
        <h1>Create category:</h1><br>
            <form:form method="POST" action="${s:mvcUrl('AC#addCommonCategoryPost').build()}" modelAttribute="addCategoryForm">
                <form:label path="name">Name</form:label>
                <form:input path="name"/>
                <form:errors path="name" />
                <br>
                <form:label path="income">Type</form:label>
                <form:select path="income">
                    <form:option value="true" label="Income"/>
                    <form:option value="false" label="Expense"/>
                </form:select>
                <input type="submit" value="Save">
            </form:form>
    </div>

    <a class="user-form user-form__a" href="${s:mvcUrl("AC#showCommonCategories").build()}">All common categories</a>

    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
