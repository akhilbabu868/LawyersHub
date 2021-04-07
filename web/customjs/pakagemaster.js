/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    packageDataTable = $("#packageDataTable").dataTable();
    display();
    //FUNCTION FOR EDIT Datatable
    $("#packageDataTable").on('click', '.edit', function (e) {

        e.preventDefault();
        $("body").scrollTop(0);
        nRow = $(this).parents('tr')[0];
        var id = $(this).attr('id');
        $.post("editPackageMaster.do", {id: id}, function (data, status) {
            console.log("ID" + data.id);
            Id = data.id;
            name = data.packagename;
            Description = data.description;
            Rate = data.rate;
            Status =data.status;
            $("#package").val(name);
            $('#Description').val(Description);
            $('#rate').val(Rate);
            $('#HiddenID').val(Id);
            $('#statusHidden').val(Status);
            $('#btnsave').val("Update");
              display();
  

        });
    });

$('#btnsave').click(function () {

    var pakage = $('#package').val();
    var Description = $('#Description').val();
    var Rate = $('#rate').val();
    var hiddenID  = $('#HiddenID').val();
    var status  = $('#statusHidden').val();
    console.log("sts"+status);           

    $.post("savepakage.do",
            {
                packagename: pakage,
                description:Description,
                rate:Rate,
                id: hiddenID,
                Status:status
            },
    function (data, status) {

        var Hid = data.flag;
        if (Hid == 2) {
            alert(" Updated sucessfully");
            display();
            $('#btnsave').val("Save");
            
            clear();
        } else {
            alert(" Saved sucessfully");
            display();
            clear();
        }



    });

});







});

function display()
{
//   producttable.clear()

    packageDataTable = $("#packageDataTable").dataTable(
            {
                "bProcessing": true,
                "bDeferRender": true,
                "bSort": false,
                "bDestroy": true,
                "oLanguage": {
                    "sEmptyTable": "No data available "
                },
                "bServerSide": true,
                "sAjaxSource": "getPackage.do",
                "sServerMethod": "POST",
                "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                    oSettings.jqXHR = $.ajax({
                        "dataType": 'json',
                        "type": "POST",
                        "url": sSource,
                        "data": aoData,
                        "success": function (res, stat)
                        {
                            option = "";
                            console.log(res);
                            for (var i = 0; i < res.aaData.length; i++)
                            {
                                res.aaData[i][0] = i + 1;
                                res.aaData[i][1] = res.aaData[i].name;
                                res.aaData[i][2] = res.aaData[i].Description;
                                res.aaData[i][3] = res.aaData[i].Rate;
                                var status = res.aaData[i].Status;
                                var id1 = res.aaData[i].id;

                                if (res.aaData[i].Status == 1) {
                                    res.aaData[i][4] = "<button type=\"button\" class=\"btn btn-danger\"  onclick=\"deactivate('" + id1+ "','" + status + "')\">Deactivate</button>";
                                } else {
                                    res.aaData[i][4] = "<button type=\"button\" class=\"btn btn-success\"  onclick=\"activate('" + id1 + "','" + status + "')\">Activate</button>";
                                }
                                res.aaData[i][5] = '<div class="btn-group"><input type="button" form="' + i + '"  class="btn btn-info btn-small edit " id="' + id1 + '" value="Edit" >';
                                '</div>';
                            }
                            fnCallback(res);
                        }

                    });
                },
                "sPaginationType": "full_numbers"
            });
}




function activate(id, status) {
    
    $.post("setpackageMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Activated sucessfully", "success");
        packageDataTable.fnDraw();
    });
}

function deactivate(id, status) {

    $.post("setpackageMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Deactivated sucessfully", "success");
        display();
        packageDataTable.fnDraw();

    });

}


function clear(){
    
  $('#package').val("");
  $('#Description').val("");
  $('#rate').val("");
}
