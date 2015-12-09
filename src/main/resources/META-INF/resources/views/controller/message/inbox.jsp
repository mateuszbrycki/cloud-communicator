<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<nav id="inbox-toolbar">
    <button type="button" class="send-message-button btn btn-default">
            <span class="glyphicon glyphicon-plus"></span>
    </button>
</nav>

<c:choose>
    <c:when test="${fn:length(messages) gt 0}">
        <div class="table-responsive">
            <table class="table table-hover table-striped">
                <tr>
                    <th>#</th>
                    <th><spring:message code="message.author" /></th>
                    <th><spring:message code="message.topic" /></th>
                    <th><spring:message code="message.text" /></th>
                </tr>

                <c:forEach items="${messages}" var="message">
                    <tr>
                        <td>${message.id}</td>
                        <td>${message.author.username}</td>
                        <td>${message.topic}</td>
                        <td>${message.text}</td>
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