<%@ page import="com.portablemind.user.UserUrls" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>

  <form id='user-login-form' action="${pageContext.request.contextPath}<%=UserUrls.USER_LOGIN_FORM%>" method='POST' class="form-horizontal">

      <div class="form-group">
        <label class="control-label col-sm-3" for="userLogin"><spring:message code="user.login" />: </label>
        <div class="col-sm-5">
          <input type="text" name="mail" class="form-control"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-3" for="userPassword"><spring:message code="user.password" />: </label>
        <div class="col-sm-5">
          <input type="password" name="password" class="form-control"/>
        </div>
      </div>


    <input type="submit" id="login-user" name="submit" value="<spring:message code="user.login" />" class="btn btn-primary"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

  </form>
