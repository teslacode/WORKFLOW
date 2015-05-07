var nextflagRefreshListJob = function () {
    loadListJob({
        module: 'nextflag',
        idList: '#ddl-nextflag-job-code'
    });
};

var nextflagRefreshListVersion = function () {
    loadListVersion({
        module: 'nextflag',
        idList: '#ddl-nextflag-version',
        data: {jobCode:  $('#ddl-nextflag-job-code').val()}
    });
};

var nextflagRefreshListActivity = function () {
    loadListActivity({
        module: 'nextflag',
        idList: '#ddl-nextflag-from-activity',
        data: {processId:  $('#ddl-nextflag-version').val()}
    });
    loadListActivity({
        module: 'nextflag',
        idList: '#ddl-nextflag-to-activity',
        data: {processId:  $('#ddl-nextflag-version').val()}
    });
};

var nextflagSave = function () {
    if ($('#ddl-nextflag-job-code').val() != '' && $('#ddl-nextflag-version').val() != '' && 
            $('#input-nextflag-from-activity').val() != '' && $('#input-nextflag-to-activity').val() != '') {
        $('#dialog').html('Are you sure?').dialog({
            modal: true,
            title: 'Confirm',
            buttons: {
                'Close': function () {
                    $(this).html('').dialog('close');
                },
                'Save': function () {
                    $("#nextflag-spinner-loading").show();
                    saveData({
                        module: 'nextflag',
                        idTable: '#table-nextflag tbody',
                        data: {
                            id: $('#input-nextflag-id').val(),
                            fromActivityId: $('#ddl-nextflag-from-activity').val(),
                            toActivityId: $('#ddl-nextflag-to-activity').val(),
                            flag: $('#input-nextflag-flag').val(),
                            order: $('#input-nextflag-order').val(),
                            hide: ($('#cb-nextflag-hide').is(":checked")?true:false),
                            processId: $('#ddl-nextflag-version').val()
                        }
                    });
                    $(this).html('').dialog('close');
                }
            }
        });
    }
};

var nextflagEdit = function (obj) {
    $('#input-nextflag-id').val(obj.id);
    $('#ddl-nextflag-from-activity').val(obj.fromActivityId);
    $('#ddl-nextflag-to-activity').val(obj.toActivityId);
    $('#input-nextflag-flag').val(obj.flag);
    $('#input-nextflag-order').val(obj.order);
    $('#cb-nextflag-hide').prop('checked', obj.hide);
    $('#ddl-nextflag-job-code').prop('disabled', true);
    $('#ddl-nextflag-version').prop('disabled', true);
    $('button[name=cancel]').show();
};

var nextflagChangeActive = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Save': function () {
                changeActiveData({
                    module: 'nextflag',
                    idTable: '#table-nextflag tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var nextflagDelete = function (obj) {
    $('#dialog').html('Are you sure?').dialog({
        modal: true,
        title: 'Confirm',
        buttons: {
            'Close': function () {
                $(this).html('').dialog('close');
            },
            'Delete': function () {
                deleteData({
                    module: 'nextflag',
                    idTable: '#table-nextflag tbody',
                    data: obj
                });
                $(this).html('').dialog('close');
            }
        }
    });
};

var nextflagAddRow = function (obj) {
    return '<tr><td>' + obj.fromActivity.process.job.code + '</td><td>' + 
            obj.fromActivity.process.version + '</td><td>' + 
            obj.fromActivity.path + '</td><td>' + 
            obj.toActivity.path + '</td><td>' +
            obj.flag + '</td><td>' +
            obj.order + '</td><td>' +
            obj.hide + '</td><td>' +
            (obj.active == true ? '<div class="alert alert-success" style="padding-top: 6px; padding-bottom: 7px;text-align: center; margin-bottom: 0px;">Active</div>' : '<div class="alert alert-warning" style="padding-top: 6px; padding-bottom: 7px;text-align: center; margin-bottom: 0px;">Deactive</div>') + '</td><td>' +
            '<button name="btn-edit" class="btn btn-info" style="width: 100px" onClick="nextflagEdit({id: \'' + obj.id + '\', fromActivityId: \'' + obj.fromActivityId + '\', toActivityId: \'' + obj.toActivityId + '\', flag: \'' + obj.flag + '\', order: \'' + obj.order + '\', hide: ' + obj.hide + '})">Edit</button> ' +
            (obj.active == true ? '<button name="btn-deactivate" class="btn btn-warning" style="width: 100px" onClick="nextflagChangeActive({id: ' + obj.id + ', processId: ' + obj.fromActivity.processId + '})">Deactivate</button>' : '<button name="btn-activate" class="btn btn-success" style="width: 100px" onClick="nextflagChangeActive({id: ' + obj.id + ', processId: ' + obj.fromActivity.processId + '})">Activate</button>') +
            ' <button name="btn-delete" class="btn btn-danger" style="width: 100px" onClick="nextflagDelete({id: ' + obj.id + ', processId: ' + obj.fromActivity.processId + '})">Delete</button>' + '</td></tr>';
};

var nextflagClearForm = function () {
    $('#input-nextflag-id').val('');
    $('#ddl-nextflag-from-activity').val('');
    $('#ddl-nextflag-to-activity').val('');
    $('#input-nextflag-flag').val('');
    $('#input-nextflag-order').val('');
    $('#cb-nextflag-hide').prop('checked', false);
    $('#ddl-nextflag-job-code').prop('disabled', false);
    $('#ddl-nextflag-version').prop('disabled', false);
    $('button[name=cancel]').hide();

    $("#nextflag-spinner-loading").hide();
};

nextflagRefreshListJob();