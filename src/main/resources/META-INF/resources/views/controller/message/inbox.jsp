<%@ page import="com.cloud.communicator.module.message.MessageUrls" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<jsp:include page="form/received_message.jsp"/>
<jsp:include page="form/add_folder.jsp"/>
<jsp:include page="form/edit_folder.jsp"/>
<script type="text/javascript">
    //refreshing inbox, 2 minutes interval
    setInterval(refreshDashboard, 120000);
</script>

<!-- Folder context menu -->
<ul id="contextMenu" class="dropdown-menu" role="menu" style="display:none">
    <li><a tabindex="-1" action-id="0"><spring:message code="button.open"/></a></li>
    <li><a tabindex="-1" action-id="1"><spring:message code="button.edit"/></a></li>
    <li class="divider"></li>
    <li><a tabindex="-1" action-id="2"><spring:message code="button.delete"/></a></li>
</ul>

<!-- Toolbar -->
<div class="row">
    <div class="col-xs-6 col-md-2"></div>
    <div class="table-responsive col-xs-12 col-md-10">
        <nav id="inbox-toolbar">
            <button type="button" class="send-message-button btn btn-default">
                <span class="glyphicon glyphicon-plus"></span>
            </button>

            <button type="button" class="reload-inbox btn btn-default">
                <span class="glyphicon glyphicon-refresh"></span>
            </button>
        </nav>
    </div>
</div>

<div class="row">

    <div class="col-xs-6 col-md-2">
        <ul class="list-group" id="folders-list">
            <c:forEach items="${folders}" var="folder">
                <li class="list-group-item folder-element"
                    folder-id="${folder.id}"
                    <c:if test="${not empty folder.labelColor}"> style="border-left: 5px solid ${folder.labelColor}"</c:if>
                >

                        ${folder.name}
                    <c:if test="${folder.unreadMessages gt 0}">
                        <span class="badge">${folder.unreadMessages}</span>
                    </c:if>
                </li>
            </c:forEach>
            <li class="list-group-item add-new-folder">
                <spring:message code="folder.add"/>
                <span class="glyphicon glyphicon-plus"></span>
            </li>
        </ul>
    </div>

    <div class="table-responsive col-xs-12 col-md-10" id="inbox-list">
        <c:choose>
        <c:when test="${fn:length(messages) gt 0}">

        <table class="table table-hover table-striped" id="inbox-table">
            <tr>
                <th></th>
                <th><spring:message code="message.author"/></th>
                <th><spring:message code="message.topic"/></th>
                <th><spring:message code="message.text"/></th>
                <th></th>
            </tr>

            <c:forEach items="${messages}" var="message">
                <tr class="inbox-element" <c:if test="${message.isRead != true}"> style="font-weight: bold; "</c:if>
                    message-id="${message.id}">
                    <td class="active-modal"><fmt:formatDate value="${message.sendDate}" pattern="d.MM"/></td>
                    <td class="active-modal">${message.author.username}</td>
                    <td class="active-modal">${message.topic}</td>
                    <td class="active-modal">${message.text}</td>
                    <td>
                        <button type="button" class="message-change-status btn btn-primary"
                                href="${pageContext.request.contextPath}<%=MessageUrls.Api.MESSAGE_CHANGE_READ_STATUS_FULL%>/${message.id}">

                            <c:choose>
                                <c:when test="${message.isRead != true}">
                                    <span class="glyphicon  glyphicon-eye-open"></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="glyphicon  glyphicon-eye-close"></span>
                                </c:otherwise>
                            </c:choose>

                        </button>

                        <button type="button" class="message-delete btn btn-warning"
                                href="${pageContext.request.contextPath}<%=MessageUrls.Api.MESSAGE_DELETE_FULL%>/${message.id}">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    </c:when>
    <c:otherwise>
        <div class="inbox-empty-alert alert alert-info" role="alert">
            <spring:message code="message.inbox.empty"/>
        </div>
    </c:otherwise>
    </c:choose>


</div>
</body>
</html>