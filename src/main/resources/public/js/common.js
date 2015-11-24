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
        alert(e.message);
    }
});