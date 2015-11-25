$(document).ready(function() {

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