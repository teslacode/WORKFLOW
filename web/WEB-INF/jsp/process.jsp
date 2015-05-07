<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/default/js/process.js" />"></script>
        <script src="<c:url value="/resources/default/js/model.js" />"></script>
        <script>
            $(function () {
                $('#btn-process-save').click(function () {
                    processSave();
                });
                $('#btn-process-cancel').click(function () {
                    processClearForm();
                });
                $('#input-process-notes').keyup(function (e) {
                    if (e.keyCode == 13)
                    {
                        processSave();
                    }
                });
                $('#ddl-process-job-code').change(function (e) {
                    loadTable({
                        module: 'process',
                        idTable: '#table-process tbody',
                        data: {jobCode: $(this).val()}
                    });
                });
            });
        </script>
    </head>
    <body>
        <h1>PROCESS</h1>
        <div class="form-inline">
            <div class="form-group col-md-3" style="padding: 0 2px 0 0;">
                <label class="sr-only" for="ddl-process-job-code">Job Code</label>
                <input type="hidden" id="input-process-id" class="form-control" name="id" placeholder="Id"/>
                <div class="input-group">
                    <select id="ddl-process-job-code" class="form-control" name="jobCode"></select>
                    <span class="input-group-btn">
                        <button class="btn btn-default" onClick="processRefreshListJob();">
                            Refresh
                        </button>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <label class="sr-only" for="input-process-notes">Notes</label>
                <input type="text" id="input-process-notes" class="form-control" name="notes" placeholder="Notes"/>
            </div>
            <button type="submit" name="save" id="btn-process-save" class="btn btn-primary">Save</button>
            <button type="submit" name="cancel" id="btn-process-cancel" class="btn btn-default" style="display: none;">Cancel</button>
            <div id="process-spinner-loading" class="form-group" style="display: none;"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Loading...</div>
        </div>
        <table id="table-process" class="table table-striped" width="100%">
            <thead>
                <tr>
                    <th>Job Code</th>
                    <th>Version</th>
                    <th>Notes</th>
                    <th>Status</th>
                    <th>Model</th>
                    <th>Function</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
        <div id="form-set-model" title="Set Model" hidden>
            <div class="form-inline">
                <div class="form-group">
                    <label class="sr-only" for="inputLabelModel">Label</label>
                    <input id="input-model-label" class="form-control" name="label" placeholder="Label"/>
                </div>
                <div class="form-group">
                    <label class="sr-only" for="inputTypeData">Type Data</label>
                    <select id="ddl-model-type-data" class="form-control" name="typeData">
                        <option value="double">Number</option>
                        <option value="String">Text</option>
                    </select>
                </div>
                <button type="submit" name="save" id="btn-model-save" class="btn btn-primary">Save</button>
                <div id="model-spinner-loading" class="form-group" style="display: none;"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Loading...</div>
            </div>
            <table id="table-model" class="table table-striped" width="100%">
                <thead>
                    <tr>
                        <th>Label</th>
                        <th>Type Data</th>
                        <th>Function</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </body>
</html>
