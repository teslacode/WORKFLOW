<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/default/js/nextflag.js" />"></script>
        <script>
            $(function () {
                $('#btn-nextflag-save').click(function () {
                    nextflagSave();
                });
                $('#btn-nextflag-cancel').click(function () {
                    nextflagClearForm();
                });
                $('#input-nextflag-flag').keyup(function (e) {
                    if (e.keyCode == 13)
                    {
                        nextflagSave();
                    }
                });
                $('#ddl-nextflag-job-code').change(function (e) {
                    nextflagRefreshListVersion();
                    $("#table-nextflag tbody").html('');
                });
                $('#ddl-nextflag-version').change(function (e) {
                    loadTable({
                        module: 'nextflag',
                        idTable: '#table-nextflag tbody',
                        data: {processId: $(this).val()}
                    });
                    nextflagRefreshListActivity();
                });
            });
        </script>
    </head>
    <body>
        <h1>NEXT FLAG</h1>
        <div class="form-inline">
            <div class="form-group col-md-3" style="padding: 0 2px 0 0;">
                <label class="sr-only" for="ddl-nextflag-job-code">Job Code</label>
                <input type="hidden" id="input-nextflag-id" class="form-control" name="id" placeholder="Id"/>
                <div class="input-group">
                    <select id="ddl-nextflag-job-code" class="form-control" name="jobCode"></select>
                    <span class="input-group-btn">
                        <button class="btn btn-default" onClick="nextflagRefreshListJob();">
                            Refresh
                        </button>
                    </span>
                </div>
            </div>
            <div class="form-group col-md-2" style="padding: 0 2px 0 0;">
                <label class="sr-only" for="ddl-nextflag-version">Version</label>
                <div class="input-group">
                    <select id="ddl-nextflag-version" class="form-control" name="version"></select>
                    <span class="input-group-btn">
                        <button class="btn btn-default" onClick="nextflagRefreshListVersion();">
                            Refresh
                        </button>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <label class="sr-only" for="ddl-nextflag-from-activity">From Activity</label>
                <select id="ddl-nextflag-from-activity" class="form-control" name="fromActivityId"></select>
            </div>
            <div class="form-group">
                <label class="sr-only" for="ddl-nextflag-to-activity">To Activity</label>
                <select id="ddl-nextflag-to-activity" class="form-control" name="toActivityId"></select>
            </div>
            <div class="form-group">
                <label class="sr-only" for="input-nextflag-flag">Flag</label>
                <input type="text" id="input-nextflag-flag" class="form-control" name="flag" placeholder="Flag" maxlength="256"/>
            </div>
            <div class="form-group">
                <label class="sr-only" for="input-nextflag-order">Order</label>
                <input type="text" id="input-nextflag-order" class="form-control" name="order" placeholder="Order" maxlength="256"/>
            </div>
            <div class="form-group">
                <label class="sr-only" for="input-nextflag-hide">Hide</label>
                <input type="checkbox" id="cb-nextflag-hide" class="form-control" name="hide"/>Hide
            </div>
            <button type="submit" name="save" id="btn-nextflag-save" class="btn btn-primary">Save</button>
            <button type="submit" name="cancel" id="btn-nextflag-cancel" class="btn btn-default" style="display: none;">Cancel</button>
            <div id="nextflag-spinner-loading" class="form-group" style="display: none;"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Loading...</div>
        </div>
        <table id="table-nextflag" class="table table-striped" width="100%">
            <thead>
                <tr>
                    <th>Job Code</th>
                    <th>Version</th>
                    <th>From Activity</th>
                    <th>To Activity</th>
                    <th>Flag</th>
                    <th>Order</th>
                    <th>Hide</th>
                    <th>Status</th>
                    <th>Function</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </body>
</html>
