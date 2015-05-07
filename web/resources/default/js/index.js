var loadTable = function (obj) {
    var module = obj.module;
    var idTable = obj.idTable;
    
    $(idTable).html('');
    
    $.ajax({
        type: 'GET', 
        dataType: 'JSON',
        url: './'+module+'/get.json',
        data: obj.data, 
        success: function (response) {
            var fn = window[module+'AddRow'];
            
            var trHTML = '';
            $.each(response, function (i, item) {
                trHTML += fn(item);
            });
            
            $(idTable).append(trHTML);
        },
        error: function () {
            alert("failed!");
        }
    });
};

var saveData = function(obj){
    var module = obj.module;
    
    $.ajax({
        type: 'POST', 
        dataType: 'JSON',
        url: './'+module+'/save.json',
        data: obj.data, 
        success: function (response) {
            if(response.data != null){
                var fnClearForm = window[module+'ClearForm'];
                fnClearForm();
                
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

var deleteData = function(obj){
    var module = obj.module;
    
    $.ajax({
        type: 'POST', 
        dataType: 'JSON',
        url: './'+module+'/delete.json',
        data: obj.data, 
        success: function (response) {
            if(response.data != null){
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

var changeActiveData = function(obj){
    var module = obj.module;
    
    $.ajax({
        type: 'POST', 
        dataType: 'JSON',
        url: './'+module+'/changeActive.json',
        data: obj.data, 
        success: function (response) {
            if(response.data != null){
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

var loadListJob = function(obj){
    var module = obj.module;
    var idList = obj.idList;
    
    $(idList).html('');
    
    $.ajax({
        type: 'GET', 
        dataType: 'JSON',
        url: './job/get.json',
        success: function (response) {
            var trHTML = '<option value="">-</option>';
            $.each(response, function (i, item) {
                trHTML += '<option value="' + item.code + '">' + item.description + '</option>';
            });
            
            $(idList).append(trHTML);
        },
        error: function () {
            alert("failed!");
        }
    });
};

var loadListVersion = function(obj){
    var module = obj.module;
    var idList = obj.idList;
    
    $(idList).html('');
    
    $.ajax({
        type: 'GET', 
        dataType: 'JSON',
        url: './process/get.json',
        data: obj.data, 
        success: function (response) {
            var trHTML = '<option value="">-</option>';
            $.each(response, function (i, item) {
                trHTML += '<option value="' + item.id + '">' + item.version + (item.active == true ? ' (Active)' : '') + '</option>';
            });
            
            $(idList).append(trHTML);
        },
        error: function () {
            alert("failed!");
        }
    });
};

var loadListActivity = function(obj){
    var module = obj.module;
    var idList = obj.idList;
    
    $(idList).html('');
    
    $.ajax({
        type: 'GET', 
        dataType: 'JSON',
        url: './activity/get.json',
        data: obj.data, 
        success: function (response) {
            var trHTML = '<option value="">-</option>';
            $.each(response, function (i, item) {
                trHTML += '<option value="' + item.id + '">' + item.path + '</option>';
            });
            
            $(idList).append(trHTML);
        },
        error: function () {
            alert("failed!");
        }
    });
};