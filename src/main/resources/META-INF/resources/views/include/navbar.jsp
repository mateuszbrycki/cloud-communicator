<%@ page import="com.cloud.communicator.module.user.UserUrls" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><spring:message code="app.name" /></a>
        </div>


        <div style="float: right;">
            <ul class="nav navbar-nav">
                <li type="button"  data-dismiss="modal" style="margin: 2px;">
                    <a href="<%=UserUrls.USER_MANAGEMENT_FULL%>" id="show-add-project-form">
                        <span class="glyphicon glyphicon-cog"></span>
                        <spring:message code="user.account.management" />
                    </a>
                </li>
            </ul>

            <a href="<%=UserUrls.USER_LOGOUT_FULL%>" class="btn btn-primary" style="margin-top: 10px;">
                <spring:message code="button.logout" />
            </a>

        </div>
    </div>
</nav>