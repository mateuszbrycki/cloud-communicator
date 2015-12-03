<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><spring:message code="app.name" /></a>
        </div>

        <a href="<spring:eval expression="@environment.getProperty('pac4j.application.logout')" />" class="btn btn-primary" style="float: right; margin-top: 10px;">
            <spring:message code="button.logout" />
        </a>
    </div>
</nav>