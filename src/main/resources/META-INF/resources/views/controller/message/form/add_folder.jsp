<%@ page import="com.cloud.communicator.module.message.FolderUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="add-folder-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close add-folder-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="add-folder-modal-title"><spring:message code="folder.add"/></h4>
            </div>
            <div class="modal-body">
                <form method="PUT" id="add-folder-form"
                      action="${pageContext.request.contextPath}<%=FolderUrls.Api.FOLDER%>/" class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="name"><spring:message code="folder.name"/>:</label>

                        <div class="col-sm-5">
                            <input type="text" name="name" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="label"><spring:message code="folder.label"/>:</label>

                        <div class="col-sm-5">
                            <input type="text" name="label" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="description"><spring:message
                                code="folder.description"/>:</label>

                        <div class="col-sm-5">
                            <textarea type="text" name="description" class="form-control" rows="6"></textarea>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default add-folder-form-close" data-dismiss="modal">
                            <spring:message code="button.close"/></button>
                        <input type="submit" id="add-folder-submit" value="<spring:message code="folder.button.add" />"
                               class="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>