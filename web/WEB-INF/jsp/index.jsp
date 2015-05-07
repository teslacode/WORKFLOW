<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Workflow Designer</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/datatables/css/jquery.dataTables_themeroller.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/default/css/index.css" />">
        <script src="<c:url value="/resources/jquery/js/jquery-1.9.1.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/jquery-ui/js/jquery-ui.js" />"></script>
        <script src="<c:url value="/resources/datatables/js/jquery.dataTables.min.js" />"></script>
        <script src="<c:url value="/resources/default/js/index.js" />"></script>
    </head>
    <body>
        <div style="margin: 20px;">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#job">Job</a></li>
                <li><a data-toggle="tab" href="#process">Process</a></li>
                <li><a data-toggle="tab" href="#activity">Activity</a></li>
                <li><a data-toggle="tab" href="#nextflag">Next Flag</a></li>
            </ul>
            <div class="tab-content">
                <div id="job" class="tab-pane fade in active">
                    <%@include file="job.jsp" %>
                </div>
                <div id="process" class="tab-pane fade">
                    <%@include file="process.jsp" %>
                </div>
                <div id="activity" class="tab-pane fade">
                    <%@include file="activity.jsp" %>
                </div>
                <div id="nextflag" class="tab-pane fade">
                    <%@include file="nextflag.jsp" %>
                </div>
            </div>
        </div>
        <div id="dialog"></div>
    </body>
</html>
