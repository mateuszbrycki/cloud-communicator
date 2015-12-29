<%@ page import="com.cloud.communicator.module.message.MessageUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="send-message-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close send-message-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="send-message-modal-title"><spring:message code="message.title.send"/></h4>
            </div>
            <div class="modal-body">
                <form method="PUT" id="send-message-form"
                      action="${pageContext.request.contextPath}<%=MessageUrls.Api.MESSAGE%>/" class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="receivers"><spring:message
                                code="message.modal.receivers"/>:</label>

                        <div class="col-sm-5">
                            <select name="receivers" class="form-control receivers-select">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="topic"><spring:message
                                code="message.modal.topic"/>:</label>

                        <div class="col-sm-5">
                            <input type="text" name="topic" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="text"><spring:message
                                code="message.modal.text"/>:</label>

                        <div class="col-sm-5">
                            <textarea type="text" name="text" class="form-control" rows="6"></textarea>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default send-message-form-close" data-dismiss="modal">
                            <spring:message code="button.close"/></button>
                        <input type="submit" id="send-message-submit"
                               value="<spring:message code="message.button.send" />" class="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>