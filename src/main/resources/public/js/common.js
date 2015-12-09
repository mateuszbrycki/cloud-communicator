/**
 * Created by Mateusz Brycki on 24/11/2015.
 */

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

function showSendMessageForm() {
    $('#send-message-modal').modal({keyboard: true});
    $("#send-message-modal").modal('show');
}

$(document).on('click', '.send-message-button', function(e) {
           e.preventDefault();
           showSendMessageForm();
       });

$(document).ready(function() {
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
