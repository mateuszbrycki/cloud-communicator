function renderMessagesList(data) {
    if (!$('#inbox-list').length) {
        return;
    }

    var oldPanelGroup = document.getElementById('inbox-list');
    var newPanelGroup = document.createElement('div');
    newPanelGroup.id = 'inbox-list';
    newPanelGroup.className = 'table-responsive col-xs-12 col-md-10';

    var tableElement = document.createElement('table');
    var tableBody = document.createElement("tbody");

    if (data.length == 0) {
        var alertDiv = getEmptyAlert(translations['message-inbox-empty']);
        newPanelGroup.appendChild(alertDiv);
    } else {

        if ($('.inbox-empty-alert').length) {
            $('.inbox-empty-alert').hide();
        }

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

    for (var i = 0; i < data.length; i++) {
        var tableRow = document.createElement('tr');
        tableRow.className = "inbox-element";
        tableRow.setAttribute('message-id', data[i].id);

        var statusIcon = "glyphicon glyphicon-eye-close";
        if (data[i].isRead != true) {
            tableRow.setAttribute("style", "font-weight: bold");
            statusIcon = "glyphicon glyphicon-eye-open";
        }

        var firstColumn = document.createElement('td');
        firstColumn.className = "active-modal";
        firstColumn.appendChild(document.createTextNode(data[i].sendDate));

        var secondColumn = document.createElement('td');
        secondColumn.className = "active-modal";
        secondColumn.appendChild(document.createTextNode(data[i].author.username));

        var thirdColumn = document.createElement('td');
        thirdColumn.className = "active-modal";
        thirdColumn.appendChild(document.createTextNode(data[i].topic));

        var fourthColumn = document.createElement('td');
        fourthColumn.className = "active-modal";
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

function renderFoldersList(data) {
    var oldPanelGroup = document.getElementById('folders-list');
    var newPanelGroup = document.createElement('ul');
    newPanelGroup.id = 'folders-list';
    newPanelGroup.className = 'list-group';

    for (var i = 0; i < data.length; i++) {
        var liElement = document.createElement('li');
        liElement.className = 'list-group-item folder-element';
        liElement.setAttribute('folder-id', data[i].id);
        liElement.appendChild(document.createTextNode(data[i].name));

        if(data[i].unreadMessages > 0) {
            var spanElement = document.createElement('span');
            spanElement.className = 'badge';
            spanElement.appendChild(document.createTextNode(data[i].unreadMessages));
            liElement.appendChild(spanElement)
        }

        newPanelGroup.appendChild(liElement);
    }

    var liElement = document.createElement('li');
    liElement.className = 'list-group-item add-new-folder';
    liElement.appendChild(document.createTextNode(translations['folder-add']));

    var spanElement = document.createElement('span');
    spanElement.className = 'glyphicon glyphicon-plus';

    liElement.appendChild(spanElement);
    newPanelGroup.appendChild(liElement);

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
        success: function (callback) {
            location.reload();
        },
        error: function (callback) {
            console.log(callback);
        }
    });
}

function showSendMessageForm() {
    $('#send-message-modal').modal({keyboard: true});
    $("#send-message-modal").modal('show');
}

function showNewFolderForm() {
    $('#add-folder-modal').modal({keyboard: true});
    $("#add-folder-modal").modal('show');
}

function changeLoadingOverlay(flag) {
    if (flag) {
        document.getElementById('loading-overlay').style.display = 'block';
    } else {
        document.getElementById('loading-overlay').style.display = 'none';
    }
}

function reloadMessagesList() {

    var folderUrl = ctx + url['api_messages'] + "/";
    if (currentFolder != null) {
        folderUrl = folderUrl + currentFolder;
    }

    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        type: "GET",
        url: folderUrl,
        success: function (callback) {
            renderMessagesList(callback);
        },
        error: function (callback) {
            console.log(translations['request-failed']);
            location.reload();
        }
    });
}

function reloadFoldersList() {
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        type: "GET",
        url: ctx + url['api_folders'] + "/",
        success: function (callback) {
            renderFoldersList(callback);
        },
        error: function (callback) {
            console.log(translations['request-failed']);
            location.reload();
        }
    });
}


function renderFolderMessages(folderId) {

    $(document).ajaxStop(function () {
        $(this).unbind("ajaxStop");
        changeLoadingOverlay(false);
    });

    changeLoadingOverlay(true);

    currentFolder = folderId;
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        type: "GET",
        url: ctx + url['api_messages_folder'] + "/" + folderId,
        success: function (callback) {
            renderMessagesList(callback);
        },
        error: function (callback) {
            console.log(translations['request-failed']);
            location.reload();
        }
    });
}

//refreshing whole dashboard
function refreshDashboard() {
    $(document).ajaxStop(function () {
        $(this).unbind("ajaxStop");
        changeLoadingOverlay(false);
    });
    changeLoadingOverlay(true);

    reloadMessagesList();
    reloadFoldersList();
}

function refreshForm(form) {
    //przy dodwaniu nowej karty z poziomu projektu usuwane było id projektu z pola hidden, jeżeli będzie powodowało
    //problemy czyszczenie należy rozbić na różne metody lub ifować
    //form.find("input[type=hidden]").val("");
    form.trigger("reset");
    form.validate().resetForm();
}

function renderMessageModal(data) {

    $(".receiver-message-modal-author").html("<p>" + data.author.username + "</p>");
    $(".receiver-message-modal-topic").html("<p>" + data.topic + "</p>");
    $(".receiver-message-modal-text").html("<p>" + data.text + "</p>");

    $('#received-message-modal').modal({keyboard: true});
    $("#received-message-modal").modal('show');
}

function showMessageModal(messageId) {

    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        type: "GET",
        url: ctx + url['api_message'] + messageId,
        success: function (callback) {
            renderMessageModal(callback);
        },
        error: function (callback) {
            console.log(translations['request-failed']);
        }
    });
}

function hideMessageModal() {
    $("#received-message-modal").modal('hide');
    refreshDashboard();
}

function deleteFolder(folderId) {

    console.log('deleteFolder');

    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        type: "DELETE",
        url: ctx + url['api_folder_delete'] + '/' + folderId,
        success: function (callback) {
            renderFoldersList(callback);
        },
        error: function (callback) {
            console.log(translations['request-failed']);
        }
    });
}

function contextMenu(e, settings) {
    // Open context menu
    // return native menu if pressing control
    if (e.ctrlKey) return;

    //open menu
    var $menu = $(settings.menuSelector)
        .data("invokedOn", $(e.target))
        .show()
        .css({
            position: "absolute",
            left: getMenuPosition(e.clientX, 'width', 'scrollLeft', settings.menuSelector),
            top: getMenuPosition(e.clientY, 'height', 'scrollTop', settings.menuSelector)
        })
        .off('click')
        .on('click', 'a', function (e) {
            $menu.hide();

            var $invokedOn = $menu.data("invokedOn");
            var $selectedMenu = $(e.target);

            settings.menuSelected.call(this, $invokedOn, $selectedMenu);
        });

    return false;
}

function getMenuPosition(mouse, direction, scrollDir) {
    var win = $(window)[direction](),
        scroll = $(window)[scrollDir](),
        menu = $(menuSelector)[direction](),
        position = mouse + scroll;

    // opening menu would pass the side of the page
    if (mouse + menu > win && menu < mouse)
        position -= menu;

    return position;
}

$(document).ready(function () {
    //language select
    if ($.cookie(languageCookieName)) {
        $('#language-select').val($.cookie(languageCookieName));
    } else {
        $("#language-select").val($("#language-select").val());
    }
    try {
        $("#language-select").msDropDown();
        $("#language-select_msdd").width(80);
    } catch (e) {
        console.log(e.message);
    }

    $(document).on('click', '.send-message-button', function () {
        showSendMessageForm();
    });

    $(document).on('click', '.add-new-folder', function () {
        showNewFolderForm();
    });

    $(document).on('click', '.message-change-status', function (e) {
        $(document).ajaxStop(function () {
            $(this).unbind("ajaxStop");
            changeLoadingOverlay(false);
        });

        changeLoadingOverlay(true);
        e.preventDefault();
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: "GET",
            url: ctx + $(this).attr('href'),
            success: function (callback) {
                refreshDashboard();
            },
            error: function (callback) {
                console.log(translations['request-failed']);
            }
        });
    });

    $(document).on('click', '.message-delete', function (e) {
        $(document).ajaxStop(function () {
            $(this).unbind("ajaxStop");
            changeLoadingOverlay(false);
        });

        changeLoadingOverlay(true);
        e.preventDefault();
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: "DELETE",
            url: ctx + $(this).attr('href'),
            success: function (callback) {
                refreshDashboard();
            },
            error: function (callback) {
                console.log(translations['request-failed']);
            }
        });
    });

    $(document).on('submit', '#send-message-form', function (e) {
        var frm = $('#send-message-form');
        e.preventDefault();

        var data = {};

        $.each(this, function (i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        console.log(JSON.stringify(data));

        if (frm.valid()) {
            $.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: frm.attr('method'),
                url: frm.attr('action'),
                data: JSON.stringify(data),
                success: function (callback) {
                    console.log(callback);
                },
                error: function (callback) {
                    console.log(callback);
                }
            });

            refreshForm(frm);
            $("#send-message-modal").modal('hide');
        }
    });

    $(document).on('submit', '#add-folder-form', function (e) {
        var frm = $('#add-folder-form');
        e.preventDefault();

        var data = {};

        $.each(this, function (i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        if (frm.valid()) {
            $.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: frm.attr('method'),
                url: frm.attr('action'),
                data: JSON.stringify(data),
                success: function (callback) {
                    refreshDashboard();
                },
                error: function (callback) {
                    console.log(callback);
                }
            });

            refreshForm(frm);
            $("#add-folder-modal").modal('hide');
        }
    });

    $(document).on('click', '.reload-inbox', function (e) {
        e.preventDefault();
        refreshDashboard();
    });

    $(document).on('click', ".active-modal", function (e) {
        showMessageModal($(this).parent().attr('message-id'));
    });

    $(document).on('click', '.received-message-form-close', function () {
        hideMessageModal();
    });

    $(document).on('click', '.folder-element', function (e) {
        renderFolderMessages($(this).attr('folder-id'));
    });

    $('#send-message-form').validate({
        rules: {
            receivers: {
                required: true,
                minlength: 3
            },
            topic: {
                required: true,
                minlength: 3
            },
            text: {
                required: true,
                minlength: 3
            }
        }
    });

    $('#add-folder-form').validate({
        rules: {
            name: {
                required: true,
                minlength: 3
            }
        }
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

    //make sure menu closes on any click
    $(document).click(function () {
        $(menuSelector).hide();
    });

    $(document).on('contextmenu', '.folder-element', function (e) {
        e.preventDefault();
        contextMenu(e, {
            menuSelector: menuSelector,
            menuSelected: function (invokedOn, selectedMenu) {
                var actionId = selectedMenu.attr('action-id');
                var folderId = invokedOn.attr('folder-id');

                switch (actionId) {
                    case '0': //open
                        renderFolderMessages(folderId);
                        break;
                    case '1': //delete
                        deleteFolder(folderId);
                        break;
                }
            }
        });
    });
});

