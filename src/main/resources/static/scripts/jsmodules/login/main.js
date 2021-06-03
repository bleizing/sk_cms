var loginAddHandler = {
    response: function (data) {
        if (0 === data.code) {
            bootbox.alert("Login Sukses");
            window.location.href=ctx + '/pages/home?g=' + data.guard;
        } else {
            bootbox.alert(data.code + ' Error');
        }
    },
    error: function (err) {
        bootbox.alert('Error - Request Time Out');
    }
};

$().ready(function () {
    $("#login").submit(function (evt) {
        evt.preventDefault();
        var _isValid = $('#login').valid();
        if (!_isValid) {
            bootbox.alert('Invalid username or password')
            return;
        }

        var _reqObj = {};
        _reqObj.username = $('#username').val();
        _reqObj.password = $('#password').val();
        JSON.post(ctx + '/json/login', _reqObj, loginAddHandler.response, loginAddHandler.error, 30000);
    });

    $('#login').attr('autocomplete', 'off').attr('modelAttribute', 'Login');
    $('#login').validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        }
    });
});