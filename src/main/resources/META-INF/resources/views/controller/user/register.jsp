<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Register</title>
</head>
<body>

  <c:if test="${not empty error}">
    <div class="col-sm-5 col-md-6">
     <div class="alert alert-danger" role="alert">
      <span class="sr-only"><spring:message code="error" />:</span>
      ${error}
    </div>
  </c:if>

  <c:if test="${not empty success}">
    <div class="alert alert-success" role="alert">
      <span class="sr-only"><spring:message code="success" />:</span>
      ${success}
    </div>
  </c:if>

    <jsp:include page="form/register.jsp" />
  </div>


</body>
</html>
