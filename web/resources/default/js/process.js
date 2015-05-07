var processRefreshListJob = function () {
    loadListJob({
        module: 'process',
        idList: '#ddl-process-job-code'
    });
};

var processSave = function () {
    if ($('#ddl-process-job-code').val() != '' && $('#input-process-notes').val() != '') {
        $('#dialog').html('Are you sure?').dialog({
            modal: true,
            title: 'Confirm',
            buttons: {
                'Close': function () {
                    $(this).html('').dialog('close');
                },
                'Save': function () {
                    $("#process-spinner-loading").show();
                    saveData({
                        module: 'process',
                        idTable: '#table-process tbody',
                        data: {
                            id: $('#input-process-id').val(),
                            jobCode: $('#ddl-process-job-code').val(),
                            notes: $('#input-process-notes').val()}
                    });
                    $(this).html('').dialog('close');
                }
            }
        });
    }
};

var processEdit = function (obj) {
    $('#input-process-id').val(obj.id);
    $('#input-process-notes').val(obj.notes);
    $('#ddl-process-job-code').prop('disabled', true);
    $('button[name=cancel]').show();
};

var processChangeActive = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Save': function () {
                changeActiveData({
                    module: 'process',
                    idTable: '#table-process tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var processDelete = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Delete': function () {
                deleteData({
                    module: 'process',
                    idTable: '#table-process tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var processDuplicate = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Duplicate': function () {
                duplicateData({
                    module: 'process',
                    idTable: '#table-process tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var duplicateData = function (obj) {
    $.ajax({
        type: 'POST',
        dataType: 'JSON',
        url: './process/duplicate.json',
        data: obj.data,
        success: function (response) {
            if (response.data != null) {
                loadTable(obj);
            } else {
                alert(response.message);
            }
        },
        error: function () {
            alert("failed!");
        }
    });
};

var processAddRow = function (obj) {
    return '<tr><td>' + obj.job.code + '</td><td>' + obj.version + '</td><td>' + obj.notes + '</td><td>' +
            (obj.active == true ? '<div class="alert alert-success" style="padding-top: 6px; padding-bottom: 7px;text-align: center; margin-bottom: 0px;">Active</div>' : '<div class="alert alert-warning" style="padding-top: 6px; padding-bottom: 7px;text-align: center; margin-bottom: 0px;">Deactive</div>') + '</td><td>' +
            '<button name="btn-model" class="btn btn-primary" onClick="openFormSetModel({processId: \'' + obj.id + '\'})">Set Model</button></td><td>' +
            '<button name="btn-edit" class="btn btn-info" style="width: 100px" onClick="processEdit({id: \'' + obj.id + '\', jobCode: \'' + obj.jobCode + '\', notes: \'' + obj.notes + '\'})">Edit</button> ' +
            (obj.active == true ? '<button name="btn-deactivate" class="btn btn-warning" style="width: 100px" onClick="processChangeActive({id: ' + obj.id + ', jobCode: \'' + obj.jobCode + '\'})">Deactivate</button>' : '<button name="btn-activate" class="btn btn-success" style="width: 100px" onClick="processChangeActive({id: ' + obj.id + ', jobCode: \'' + obj.jobCode + '\'})">Activate</button>') +
            ' <button name="btn-delete" class="btn btn-danger" style="width: 100px" onClick="processDelete({id: ' + obj.id + ', jobCode: \'' + obj.jobCode + '\'})">Delete</button>' +
            ' <button name="btn-duplicate" class="btn btn-default" style="width: 100px" onClick="processDuplicate({id: ' + obj.id + ', jobCode: \'' + obj.jobCode + '\'})">Dupilcate</button>' + '</td></tr>';
};

var processClearForm = function () {
    $('#input-process-id').val('');
    $('#input-process-notes').val('');
    $('#ddl-process-job-code').prop('disabled', false);
    $('button[name=cancel]').hide();

    $("#process-spinner-loading").hide();
};

processRefreshListJob();