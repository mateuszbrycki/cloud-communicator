<%@ page import="com.cloud.communicator.module.message.FolderUrls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script>
    $(function(){
        $('.color-picker').colorpicker({
            format: 'hex',
            customClass: 'colorpicker-2x',
            sliders: {
                saturation: {
                    maxLeft: 200,
                    maxTop: 200
                },
                hue: {
                    maxTop: 200
                },
                alpha: {
                    maxTop: 200
                }
            }
        });
    });
</script>
<div id="edit-folder-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close edit-folder-form-close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="edit-folder-modal-title"><spring:message code="folder.edit"/></h4>
            </div>
            <div class="modal-body">
                <form method="POST" id="edit-folder-form"
                      action="${pageContext.request.contextPath}<%=FolderUrls.Api.FOLDER%>/" class="form-horizontal">

                    <input type="hidden" name="id" value=""/>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="name"><spring:message code="folder.name"/>:</label>

                        <div class="col-sm-5">
                            <input type="text" name="name" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="label"><spring:message code="folder.label"/>:</label>

                        <div class="col-sm-5">
                            <input type="text" name="label" class="form-control color-picker"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="description"><spring:message code="folder.description"/>:</label>

                        <div class="col-sm-5">
                            <textarea type="text" name="description" class="form-control" rows="6"></textarea>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default edit-folder-form-close" data-dismiss="modal">
                            <spring:message code="button.close"/></button>
                        <input type="submit" id="edit-folder-submit" value="<spring:message code="button.edit" />"
                               class="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>