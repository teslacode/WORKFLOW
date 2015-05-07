<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/default/js/job.js" />"></script>
        <script>
            $(function () {
                $('#btn-job-save').click(function () {
                    jobSave();
                });
                $('#btn-job-cancel').click(function () {
                    jobClearForm();
                });
                $('#input-job-description').keyup(function (e) {
                    if (e.keyCode == 13)
                    {
                        jobSave();
                    }
                });
            });
        </script>
    </head>
    <body>
        <h1>JOB</h1>
        <div class="form-inline">
            <div class="form-group">
                <label class="sr-only" for="input-job-code">Code</label>
                <input type="text" id="input-job-code" class="form-control" name="code" placeholder="Code" maxlength="10"/>
            </div>
            <div class="form-group">
                <label class="sr-only" for="input-job-description">Description</label>
                <input type="text" id="input-job-description" class="form-control" name="description" placeholder="Description" maxlength="256"/>
            </div>
            <button type="submit" name="save" id="btn-job-save" class="btn btn-primary">Save</button>
            <button type="submit" name="cancel" id="btn-job-cancel" class="btn btn-default" style="display: none;">Cancel</button>
            <div id="job-spinner-loading" class="form-group" style="display: none;"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Loading...</div>
        </div>
        <table id="table-job" class="table table-striped" width="100%">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Function</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </body>
</html>
