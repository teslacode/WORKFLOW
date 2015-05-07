var activityRefreshListJob = function () {
    loadListJob({
        module: 'activity',
        idList: '#ddl-activity-job-code'
    });
};

var activityRefreshListVersion = function () {
    loadListVersion({
        module: 'activity',
        idList: '#ddl-activity-version',
        data: {jobCode: $('#ddl-activity-job-code').val()}
    });
};

var activitySave = function () {
    if ($('#ddl-activity-job-code').val() != '' && $('#ddl-activity-version').val() != '' &&
            $('#input-activity-path').val() != '' && $('#input-activity-description').val() != '') {
        $('#dialog').html('Are you sure?').dialog({
            modal: true,
            title: 'Confirm',
            buttons: {
                'Close': function () {
                    $(this).html('').dialog('close');
                },
                'Save': function () {
                    $("#activity-spinner-loading").show();
                    saveData({
                        module: 'activity',
                        idTable: '#table-activity tbody',
                        data: {
                            id: $('#input-activity-id').val(),
                            processId: $('#ddl-activity-version').val(),
                            order: $('#input-activity-order').val(),
                            path: $('#input-activity-path').val(),
                            description: $('#input-activity-description').val()
                        }
                    });
                    $(this).html('').dialog('close');
                }
            }
        });
    }
};

var activityEdit = function (obj) {
    $('#input-activity-id').val(obj.id);
    $('#input-activity-order').val(obj.order);
    $('#input-activity-path').val(obj.path);
    $('#input-activity-description').val(obj.description);
    $('#ddl-activity-job-code').prop('disabled', true);
    $('#ddl-activity-version').prop('disabled', true);
    $('button[name=cancel]').show();
};

var activityChangeActive = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Save': function () {
                changeActiveData({
                    module: 'activity',
                    idTable: '#table-activity tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var activityDelete = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Delete': function () {
                deleteData({
                    module: 'activity',
                    idTable: '#table-activity tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var activityAddRow = function (obj) {
    return '<tr><td>' + obj.process.job.code + '</td><td>' +
            obj.process.version + '</td><td>' +
            obj.order + '</td><td>' +
            obj.path + '</td><td>' +
            obj.description + '</td><td>' +
            (obj.active == true ? '<div class="alert alert-success" style="padding-top: 6px; padding-bottom: 7px;text-align: center; margin-bottom: 0px;">Active</div>' : '<div class="alert alert-warning" style="padding-top: 6px; padding-bottom: 7px;text-align: center; margin-bottom: 0px;">Deactive</div>') + '</td><td>' +
            '<button name="btn-rules" class="btn btn-primary" style="width: 100px" onClick="activityRules({id: ' + obj.id + ', processId: ' + obj.processId + '})">Rules</button></td><td>' +
            '<button name="btn-edit" class="btn btn-info" style="width: 100px" onClick="activityEdit({id: \'' + obj.id + '\', order: \'' + obj.order + '\', path: \'' + obj.path + '\', description: \'' + obj.description + '\'})">Edit</button> ' +
            (obj.active == true ? '<button name="btn-deactivate" class="btn btn-warning" style="width: 100px" onClick="activityChangeActive({id: ' + obj.id + ', processId: ' + obj.processId + '})">Deactivate</button>' : '<button name="btn-activate" class="btn btn-success" style="width: 100px" onClick="activityChangeActive({id: ' + obj.id + ', processId: ' + obj.processId + '})">Activate</button>') +
            ' <button name="btn-delete" class="btn btn-danger" style="width: 100px" onClick="activityDelete({id: ' + obj.id + ', processId: ' + obj.processId + '})">Delete</button></td></tr>';
};

var activityClearForm = function () {
    $('#input-activity-id').val('');
    $('#input-activity-order').val('');
    $('#input-activity-path').val('');
    $('#input-activity-description').val('');
    $('#ddl-activity-job-code').prop('disabled', false);
    $('#ddl-activity-version').prop('disabled', false);
    $('button[name=cancel]').hide();

    $("#activity-spinner-loading").hide();
};

activityRefreshListJob();