
function renderInboxList(data) {
    if (!$('#inbox-list').length) {
        return;
    }

    var oldPanelGroup = document.getElementById('inbox-list');
    var newPanelGroup = document.createElement('div');
    newPanelGroup.id = 'inbox-list';
    newPanelGroup.className = 'table-responsive';

    var tableElement = document.createElement('table');
    var tableBody = document.createElement("tbody");

    if(data.length == 0) {
        var alertDiv = getEmptyAlert(translations['message-inbox-empty']);
        newPanelGroup.appendChild(alertDiv);
    } else {

        tableElement.className = "table table-hover table-striped";
        tableElement.id = "inbox-table";

        var tableHeader = document.createElement('tr');

        var firstColumn = document.createElement('th');

        var secondColumn = document.createElement('th');
        secondColumn.appendChild(document.createTextNode(translations['author']));

        var thirdColumn = document.createElement('th');
        thirdColumn.appendChild(document.createTextNode(translations['topic']));

        var fourthColumn = document.createElement('th');
        fourthColumn.appendChild(document.createTextNode(translations['text']));

        var fifthColumn = document.createElement('th');

        tableHeader.appendChild(firstColumn);
        tableHeader.appendChild(secondColumn);
        tableHeader.appendChild(thirdColumn);
        tableHeader.appendChild(fourthColumn);
        tableHeader.appendChild(fifthColumn);

        tableBody.appendChild(tableHeader);
    }

    for(var i = 0; i < data.length; i++) {
        var tableRow = document.createElement('tr');

        var statusIcon = "glyphicon glyphicon-eye-close";
        if(data[i].isRead != true) {
            tableRow.setAttribute("style", "font-weight: bold");
            statusIcon = "glyphicon glyphicon-eye-open";
        }

        var firstColumn = document.createElement('td');
        firstColumn.appendChild(document.createTextNode(data[i].sendDate));

        var secondColumn = document.createElement('td');
        secondColumn.appendChild(document.createTextNode(data[i].author.username));

        var thirdColumn = document.createElement('td');
        thirdColumn.appendChild(document.createTextNode(data[i].topic));

        var fourthColumn = document.createElement('td');
        fourthColumn.appendChild(document.createTextNode(data[i].text));

        var fifthColumn = document.createElement('td');

        var changeStatusButton = document.createElement('button');
        changeStatusButton.className = 'message-change-status btn btn-primary';
        changeStatusButton.type = 'button';
        changeStatusButton.setAttribute('href', ctx + url['api_message_change_status'] + '/' + data[i].id);

        var changeStatusGlyphicon = document.createElement('span');
        changeStatusGlyphicon.className = statusIcon;

        var deleteButton = document.createElement('button');
        deleteButton.className = 'message-delete btn btn-warning';
        deleteButton.type = 'button';
        deleteButton.setAttribute('href', ctx + url['api_message_delete'] + '/' + data[i].id);

        var deleteGlyphicon = document.createElement('span');
        deleteGlyphicon.className = 'glyphicon glyphicon-remove';

        changeStatusButton.appendChild(changeStatusGlyphicon);
        deleteButton.appendChild(deleteGlyphicon);

        fifthColumn.appendChild(changeStatusButton);
        fifthColumn.appendChild(deleteButton);

        tableRow.appendChild(firstColumn);
        tableRow.appendChild(secondColumn);
        tableRow.appendChild(thirdColumn);
        tableRow.appendChild(fourthColumn);
        tableRow.appendChild(fifthColumn);

        tableBody.appendChild(tableRow);
    }

    tableElement.appendChild(tableBody);
    newPanelGroup.appendChild(tableElement);

    oldPanelGroup.parentElement.replaceChild(newPanelGroup, oldPanelGroup);
}

function getEmptyAlert(element) {
    var alertDiv = document.createElement('div');
    alertDiv.className = 'alert alert-info';
    alertDiv.setAttribute('role', 'alert');

    alertDiv.appendChild(document.createTextNode(element));

    return alertDiv;
}


function changeLanguage(data) {

    $.ajax({
        contentType: "application/text; charset=utf-8",
        type: "GET",
        url: ctx + "?language=" + data,
        success: function(callback) {
            location.reload();
        },
        error: function (callback) {
            console.log(callback);
        }
    });
}

function changeLoadingOverlay(flag) {
    if(flag) {
        document.getElementById('loading-overlay').style.display = 'block';
    } else {
        document.getElementById('loading-overlay').style.display = 'none';
    }
}

function reloadInboxList() {

    changeLoadingOverlay(true);
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        type: "GET",
        url:  ctx + url['api_messages'] + "/",
        success : function(callback) {
            renderInboxList(callback);

            changeLoadingOverlay(false);
        },
        error : function (callback) {
            console.log(translations['request-failed']);

            changeLoadingOverlay(false);
        }
    });
}

$(document).ready(function() {
    //language select
    if($.cookie(languageCookieName)) {
        $('#language-select').val($.cookie(languageCookieName));
    } else {
        $("#language-select").val($("#language-select").val());
    }
    try {
        $("#language-select").msDropDown();
        $("#language-select_msdd").width(80);
    } catch(e) {
        console.log(e.message);
    }

    $(document).on('click', '.message-change-status', function(e) {
        e.preventDefault();
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: "GET",
            url:  ctx + $(this).attr('href'),
            success : function(callback) {
                reloadInboxList();
            },
            error : function (callback) {
                console.log(translations['request-failed']);
            }
        });
    });

    $(document).on('click', '.message-delete', function(e) {
        e.preventDefault();
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: "DELETE",
            url:  ctx + $(this).attr('href'),
            success : function(callback) {
                reloadInboxList();
            },
            error : function (callback) {
                console.log(translations['request-failed']);
            }
        });
    });

    $(document).on('click', '.reload-inbox', function(e) {
        e.preventDefault();
        reloadInboxList();
    });


    //form validation
    $('#user-register-form').validate({
        rules: {
            username: {
                required: true,
                minlength: 3
            },
            mail: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 3
            },
            password_repeat: {
                required: true,
                minlength: 3
            }
        }
    });
});
