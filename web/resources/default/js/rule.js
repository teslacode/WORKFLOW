var activityRules = function (obj) {
    //activityRulesLoadModel(obj);
    activityGetRules(obj);
    $('#form-set-rules').dialog({
        modal: true,
        width: 0.56 * window.innerWidth,
        height: 0.75 * window.innerHeight,
        buttons: {
            'Save': function () {
                activityRulesSave({
                    data: {
                        id: obj.id,
                        rules: $('#textarea-activity-rules').val()
                    }
                });
            },
            'Close': function () {
                $('#textarea-activity-rules').val('');
                $(this).dialog('close');
            }
        }
    });
};

var activityGetRules = function (obj) {
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: './activity/get.json',
        data: {id: obj.id},
        success: function (response) {
            $('#textarea-activity-rules').val(response.rules);
        },
        error: function () {
            alert("failed!");
        }
    });
};

var activityRulesLoadModel = function (obj) {
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: './model/get.json',
        data: obj,
        success: function (response) {
            var trHTML = '';
            $('#ddl-rules-model').html(trHTML);
            $.each(response, function (i, item) {
                trHTML += '<option value="' + item.id + '">' + item.label + '</option>';
            });
            $('#ddl-rules-model').append(trHTML);
        },
        error: function () {
            alert("failed!");
        }
    });
};

var activityRulesSave = function (obj) {
    $.ajax({
        type: 'POST',
        dataType: 'JSON',
        url: './activity/save.json',
        data: obj.data,
        success: function (response) {
            alert(response.message);
        },
        error: function () {
            alert("failed!");
        }
    });
};