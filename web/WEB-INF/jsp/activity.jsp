<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/default/js/activity.js" />"></script>
        <script src="<c:url value="/resources/default/js/rule.js" />"></script>
        <script>
            $(function () {
                $('#btn-activity-save').click(function () {
                    activitySave();
                });
                $('#btn-activity-cancel').click(function () {
                    activityClearForm();
                });
                $('#input-activity-description').keyup(function (e) {
                    if (e.keyCode == 13)
                    {
                        activitySave();
                    }
                });
                $('#ddl-activity-job-code').change(function (e) {
                    activityRefreshListVersion();
                    $("#table-activity tbody").html('');
                });
                $('#ddl-activity-version').change(function (e) {
                    loadTable({
                        module: 'activity',
                        idTable: '#table-activity tbody',
                        data: {processId: $(this).val()}
                    });
                });
                $('#btn-rules-add').click(function (e){
                    activityRulesAdd({
                        data: {
                            conditional: $('#ddl-rules-conditional').val(),
                            modelId: $('#ddl-rules-model').val(),
                            operator: $('#ddl-rules-operator').val(),
                            value: $('#input-rules-value').val(),
                            modelLabel: $("#ddl-rules-model option:selected").text()
                        }
                    });
                });
                $('#ddl-rules-conditional').change(function(){
                    if($('#ddl-rules-conditional').val() === '(' || $('#ddl-rules-conditional').val() === ')'){
                        $('#ddl-rules-model').hide();
                        $('#ddl-rules-operator').hide();
                        $('#input-rules-value').hide();
                    } else {
                        $('#ddl-rules-model').show();
                        $('#ddl-rules-operator').show();
                        $('#input-rules-value').show();
                    }
                });
            });
        </script>
    </head>
    <body>
        <h1>ACTIVITY</h1>
        <div class="form-inline">
            <div class="form-group col-md-3" style="padding: 0 2px 0 0;">
                <label class="sr-only" for="ddl-activity-job-code">Job Code</label>
                <input type="hidden" id="input-activity-id" class="form-control" name="id" placeholder="Id"/>
                <div class="input-group">
                    <select id="ddl-activity-job-code" class="form-control" name="jobCode"></select>
                    <span class="input-group-btn">
                        <button class="btn btn-default" onClick="activityRefreshListJob();">
                            Refresh
                        </button>
                    </span>
                </div>
            </div>
            <div class="form-group col-md-2" style="padding: 0 2px 0 0;">
                <label class="sr-only" for="ddl-activity-version">Version</label>
                <div class="input-group">
                    <select id="ddl-activity-version" class="form-control" name="version"></select>
                    <span class="input-group-btn">
                        <button class="btn btn-default" onClick="activityRefreshListVersion();">
                            Refresh
                        </button>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <label class="sr-only" for="input-activity-order">Order</label>
                <input type="text" id="input-activity-order" class="form-control" name="order" placeholder="Order" maxlength="11"/>
            </div>
            <div class="form-group">
                <label class="sr-only" for="input-activity-path">Path</label>
                <input type="text" id="input-activity-path" class="form-control" name="path" placeholder="Path" maxlength="10"/>
            </div>
            <div class="form-group">
                <label class="sr-only" for="input-activity-description">Description</label>
                <input type="text" id="input-activity-description" class="form-control" name="description" placeholder="Description" maxlength="256"/>
            </div>
            <button type="submit" name="save" id="btn-activity-save" class="btn btn-primary">Save</button>
            <button type="submit" name="cancel" id="btn-activity-cancel" class="btn btn-default" style="display: none;">Cancel</button>
            <div id="activity-spinner-loading" class="form-group" style="display: none;"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Loading...</div>
        </div>
        <table id="table-activity" class="table table-striped" width="100%">
            <thead>
                <tr>
                    <th>Job Code</th>
                    <th>Version</th>
                    <th>Order</th>
                    <th>Path</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Rules</th>
                    <th>Function</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
        <div id="form-set-rules" title="Set Rules" hidden>
            <textarea id="textarea-activity-rules" style="width: 100%; height: 100%;"></textarea>
        </div>
    </body>
</html>
