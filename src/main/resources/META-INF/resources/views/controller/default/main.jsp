<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
	<sec:authorize access="isAuthenticated()">
		<c:redirect url="${pageContext.servletContext.contextPath}/board/"/>
	</sec:authorize>

	<sec:authorize access="isAnonymous()">
		<div class="col-sm-5 col-sm-offset-2 col-md-6 col-md-offset-0" style="top: 25%">
			<jsp:include page="../user/form/register.jsp" />
		</div>
	</sec:authorize>
</body>
</html>