<%@ page import="com.cloud.communicator.module.message.MessageUrls" %>
<%@ page import="com.cloud.communicator.module.message.FolderUrls" %>
<%@ page import="com.cloud.communicator.module.user.UserUrls" %>
<%@ page import="com.cloud.communicator.module.contact.UserContact" %>
<%@ page import="com.cloud.communicator.module.contact.UserContactUrls" %>
<%@ page import="com.cloud.communicator.module.search.SearchUrls" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="app.name"/></title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/img/favicon.ico" />"/>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap_3_2_0_min.css" />">
    <link rel="stylesheet" href="<c:url value="/css/msdropdown_dd.css" />">
    <link rel="stylesheet" href="<c:url value="/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-glyphicons.css" />">
    <link rel="stylesheet" href="<c:url value="/css/spinners.css" />">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-colorpicker.min.css" />">
    <link rel="stylesheet" href="<c:url value="/css/select2.min.css" />">

    <script src="<c:url value="/js/lib/jquery-2.1.0.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/bootstrap_3_2_0_min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery_msdropdown_dd.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery_cookie_1_4_1.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery_validate_1_12_0_min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/bootstrap-colorpicker.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/select2.full.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/sidebar.min.js" />" type="text/javascript"></script>

    <script src="<c:url value="/js/common.js" />" type="text/javascript"></script>

    <script>
        var translations = {
            'request-failed': "<spring:message code="request.failed" />",
            'author': "<spring:message code="message.author" />",
            'topic': "<spring:message code="message.topic" />",
            'text': "<spring:message code="message.text" />",
            'message-folder-empty': "<spring:message code="message.folder.empty" />",
            'folder-add': "<spring:message code="folder.add" />",
            'response-message': "<spring:message code="message.in.response.to" />",
            'search-result': "<spring:message code="search.result" />"
        };

        var url = {
            'api_message_change_status': "<%=MessageUrls.Api.MESSAGE_CHANGE_READ_STATUS_FULL%>",
            'api_message_delete': "<%=MessageUrls.Api.MESSAGE_DELETE_FULL%>",
            'api_message': "<%=MessageUrls.Api.MESSAGE_FULL%>",
            'api_messages': "<%=MessageUrls.Api.MESSAGES%>",
            'api_folders': "<%=FolderUrls.Api.FOLDERS%>",
            'api_folder_delete': "<%=FolderUrls.Api.FOLDER_DELETE_FULL%>",
            'api_folder': "<%=FolderUrls.Api.FOLDER_FULL%>",
            'api_messages_folder': "<%=MessageUrls.Api.MESSAGES_FOLDER_FULL%>",
            'api_user_username':  "<%=UserUrls.Api.USER%>",
            'user_logout': "<%=UserUrls.USER_LOGOUT_FULL%>",
            'api_usercontact_delete': "<%=UserContactUrls.Api.USER_CONTACT_DELETE_FULL%>",
            'api_search': "<%=SearchUrls.Api.SEARCH%>"
        };

        var ctx = "${pageContext.request.contextPath}";
        var languageCookieName = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE";

        var currentFolder = null;

        var menuSelector = "#contextMenu";
    </script>

</head>

<body>