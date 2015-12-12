<%@ page import="com.cloud.communicator.module.message.MessageUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="received-message-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close received-message-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="received-message-modal-title"><spring:message
                        code="message.title.received"/></h4>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <spring:message code="message.author"/>
                </div>
                <div class="col-md-8 receiver-message-modal-author">

                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <spring:message code="message.topic"/>
                </div>
                <div class="col-md-8 receiver-message-modal-topic">

                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <spring:message code="message.text"/>
                </div>
                <div class="col-md-8 receiver-message-modal-text">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default send-message-form-close" data-dismiss="modal">
                    <spring:message code="button.close"/></button>
                <input type="submit" id="response-message-submit"
                       value="<spring:message code="message.button.response" />" class="btn btn-primary"/>
            </div>


        </div>
    </div>
</div>