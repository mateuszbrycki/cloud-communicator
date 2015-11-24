<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>
<html>
<head>

</head>
<body>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <span class="sr-only"><spring:message code="error" />:</span>
                ${error}
        </div>
    </c:if>

    <div class="col-sm-5 col-md-6">
        <jsp:include page="form/login.jsp" />
    </div>
</body>
</html>