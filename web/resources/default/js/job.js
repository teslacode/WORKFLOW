loadTable({
    module: 'job',
    idTable: '#table-job tbody'
});

var jobSave = function () {
    if ($('#input-job-code').val() != '' && $('#input-job-description').val() != '') {
        $('#dialog').html('Are you sure?').dialog({
            modal: true,
            title: 'Confirm',
            buttons: {
                'Close': function () {
                    $(this).html('').dialog('close');
                },
                'Save': function () {
                    $("#job-spinner-loading").show();
                    saveData({
                        module: 'job',
                        idTable: '#table-job tbody',
                        data: {code: $('#input-job-code').val(),
                            description: $('#input-job-description').val()}
                    });
                    $(this).html('').dialog('close');
                }
            }
        });
    }
};

var jobEdit = function (obj) {
    $('#input-job-code').val(obj.code);
    $('#input-job-description').val(obj.description);
    $('#input-job-code').prop('disabled', true);
    $('button[name=cancel]').show();
};

var jobChangeActive = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Save': function () {
                changeActiveData({
                    module: 'job',
                    idTable: '#table-job tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var jobDelete = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Delete': function () {
                deleteData({
                    module: 'job',
                    idTable: '#table-job tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var jobAddRow = function (obj) {
    return '<tr><td>' + obj.code + '</td><td>' + obj.description + '</td><td>' +
            (obj.active == true ? '<div class="alert alert-success" style="padding-top: 6px; padding-bottom: 7px;text-align: center; margin-bottom: 0px;">Active</div>' : '<div class="alert alert-warning" style="padding-top: 6px; padding-bottom: 7px;text-align: center; margin-bottom: 0px;">Deactive</div>') + '</td><td>' +
            '<button name="btn-edit" class="btn btn-info" style="width: 100px" onClick="jobEdit({code: \'' + obj.code + '\', description: \'' + obj.description + '\'})">Edit</button> ' +
            (obj.active == true ? '<button name="btn-deactivate" class="btn btn-warning" style="width: 100px" onClick="jobChangeActive({code: \'' + obj.code + '\'})">Deactivate</button>' : '<button name="btn-activate" class="btn btn-success" style="width: 100px" onClick="jobChangeActive({code: \'' + obj.code + '\'})">Activate</button>') +
            ' <button name="btn-delete" class="btn btn-danger" style="width: 100px" onClick="jobDelete({code: \'' + obj.code + '\'})">Delete</button>' + '</td></tr>';
};

var jobClearForm = function () {
    $('#input-job-code').val('');
    $('#input-job-description').val('');
    $('#input-job-code').prop('disabled', false);
    $('button[name=cancel]').hide();

    $("#job-spinner-loading").hide();
};