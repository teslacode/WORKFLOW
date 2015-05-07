var loadTableModel = function (obj) {
    $('#table-model tbody').html('');
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: './model/get.json',
        data: obj,
        success: function (response) {
            var trHTML = '';
            $.each(response, function (i, item) {
                trHTML += '<tr>' +
                        '<td>' + item.label + '</td>' +
                        '<td>' + item.typeData + '</td>' +
                        '<td>Function</td>' +
                        '</tr>';
            });
            $('#table-model tbody').append(trHTML);
            $("#model-spinner-loading").hide();
        },
        error: function () {
            alert("failed!");
            $("#model-spinner-loading").hide();
        }
    });
};

var openFormSetModel = function (obj) {
    $("#model-spinner-loading").show();
    loadTableModel(obj);
    $('#form-set-model').dialog({
        modal: true,
        width: 0.36 * window.innerWidth,
        height: 0.75 * window.innerHeight,
        buttons: {
            'Close': function () {
                $(this).dialog('close');
            }
        }
    });
};