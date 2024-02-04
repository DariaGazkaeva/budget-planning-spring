<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<header>
    <img class="logo" src="<c:url value="/img/logo.png" />" alt="Logo">
    <nav class="header__nav">
        <ul class="nav__ul">
            <li class="nav__li"><a href="${s:mvcUrl("PC#getProfile").build()}" class="nav__a">
                Profile
            </a></li>
            <li class="nav__li"><a href="${s:mvcUrl("PC#addMoneyOperationGet").arg(0, true).build()}" class="nav__a">
                Add income
            </a></li>
            <li class="nav__li"><a href="${s:mvcUrl("PC#addMoneyOperationGet").arg(0, false).build()}" class="nav__a">
                Add expense
            </a></li>
            <li class="nav__li"><a href="${s:mvcUrl("PC#addCashSavingGet").build()}" class="nav__a">
                Add cash saving
            </a></li>
            <li class="nav__li"><a href="${s:mvcUrl("PC#showHistory").arg(0, true).build()}" class="nav__a">
                History of income
            </a></li>
            <li class="nav__li"><a href="${s:mvcUrl("PC#showHistory").arg(0, false).build()}" class="nav__a">
                History of expenses
            </a></li>
            <li class="nav__li"><a href="${s:mvcUrl("UC#logout").build()}" class="nav__logout">
                Logout
            </a></li>
        </ul>

    </nav>
</header>
