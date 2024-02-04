<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html lang="en">
<t:head title="Money Operation">
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <link rel="stylesheet" type="text/css" href="/css/userForm.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/submitButton.css">
    <script src="/js/category.js"></script>
    <script src="/js/defaultDate.js"></script>
</t:head>
<t:body>
    <jsp:include page="parts/_header.jsp" />

    <div class="sidebar">
        <button class="create-category-widget__button widget_button user-form__a">Create category</button>
        <button class="delete-category-widget__button widget_button user-form__a">Delete category</button>

        <div class="user-form create-category-widget display-none">
            <p>Create category:</p><br>
            <form:form method="POST" action="${s:mvcUrl('RCC#addCategory').build()}" modelAttribute="addCategoryForm">
                <form:label path="name">Name</form:label>
                <form:input cssClass="category-name" path="name"/>
                <form:errors path="name" />
                <br>
                <form:select path="income" cssClass="income">
                    <form:option value="true" label="Income"/>
                    <form:option value="false" label="Expense"/>
                </form:select>
                <input type="submit" value="Save">
            </form:form>
        </div>

        <div class="user-form delete-category-widget display-none">
            <p>Delete category:</p><br>
            <ul>
                <c:forEach var="c" items="${categories}">
                    <c:if test="${c.author == user}">
                        <li class="category-list">
                            <a class="delete-category-widget__a" data-category-id="${c.id}" href="${s:mvcUrl("RCC#deleteCategory").arg(0, c).build()}">
                                <c:out value="${c.name}" />
                            </a>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="main">
        <div class="user-form">
            <form:form method="POST" modelAttribute="moneyOperation">
                <form:label path="category">Category</form:label>
                <form:select path="category" cssClass="category-select">
                    <c:forEach items="${categories}" var="c">
                        <form:option value="${c.id}" id="${c.id}" label="${c.name}"/>
                    </c:forEach>
                </form:select>
                <br>
                <form:label path="sum">Sum</form:label>
                <form:input type="number" path="sum"/>
                <form:errors path="sum" />
                <br>
                <form:label path="date">Date</form:label>
                <form:input cssClass="date-money-operation" type="date" path="date"/>
                <form:errors path="date" />
                <br>
                <form:label path="description">Description</form:label>
                <form:input type="text" path="description"/>
                <form:errors path="description" />
                <br>

                <br>
                <input type="submit" value="Save">
            </form:form>
            <a class="user-form__a" href="${s:mvcUrl("PC#getProfile").build()}">To profile</a>
        </div>
    </div>
    <jsp:include page="parts/_footer.jsp" />
</t:body>
</html>
