<%@ page import="com.cloud.communicator.module.message.MessageUrls" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>

<c:choose>
    <c:when test="${fn:length(messages) gt 0}">
        <div class="table-responsive" id="inbox-list">
            <table class="table table-hover table-striped" id="inbox-table">
                <tr>
                    <th></th>
                    <th><spring:message code="message.author" /></th>
                    <th><spring:message code="message.topic" /></th>
                    <th><spring:message code="message.text" /></th>
                    <th></th>
                </tr>

                <c:forEach items="${messages}" var="message">
                    <tr <c:if test="${message.isRead != true}"> style="font-weight: bold; "</c:if>>
                        <td><fmt:formatDate value="${message.sendDate}" pattern="d.MM" /></td>
                        <td>${message.author.username}</td>
                        <td>${message.topic}</td>
                        <td>${message.text}</td>
                        <td>
                            <button type="button" class="message-change-status btn btn-primary" href="${pageContext.request.contextPath}<%=MessageUrls.Api.MESSAGE_CHANGE_READ_STATUS_FULL%>/${message.id}">
                                <span class="glyphicon glyphicon-ok"></span>
                            </button>

                            <button type="button" class="message-delete btn btn-primary" href="${pageContext.request.contextPath}<%=MessageUrls.Api.MESSAGE_DELETE_FULL%>/${message.id}">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-info" role="alert">
            <spring:message code="message.inbox.empty" />
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>