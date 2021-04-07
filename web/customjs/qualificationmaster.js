/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {


    qualificationDataTable = $("#qualificationDataTable").dataTable();
    display();

    //FUNCTION FOR EDIT Datatable
    $("#qualificationDataTable").on('click', '.edit', function (e) {
        
        e.preventDefault();
        $("body").scrollTop(0);
        nRow = $(this).parents('tr')[0];
        var id = $(this).attr('id');

        $.post("editqualificationMaster.do", {id: id}, function (data, status) {
            Id = data.id;
            name = data.qualification;
            Status =data.status;
            $("#qualification").val(name);
            $('#HiddenID').val(Id);
            $('#statusHidden').val(Status);
           $('#btnsave').val("Update");
        });
    });


$('#btnsave').click(function () {

    var Qualification = $('#qualification').val();
    var hiddenID  = $('#HiddenID').val();
    var StatusHidden = $('#statusHidden').val();
                  

    $.post("savequalification.do",
            {
                qualification: Qualification,
                id: hiddenID,
                Status:StatusHidden
            },
    function (data, status) {

        var Hid = data.flag;
        if (Hid == 2) {
            alert(" Updated sucessfully");
            display();
            clear();
            $('#btnsave').val("Save");
            
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

    qualificationDataTable = $("#qualificationDataTable").dataTable(
            {
                "bProcessing": true,
                "bDeferRender": true,
                "bSort": false,
                "bDestroy": true,
                "oLanguage": {
                    "sEmptyTable": "No data available "
                },
                "bServerSide": true,
                "sAjaxSource": "getQualification.do",
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
                                var status = res.aaData[i].Status;
                                var id1 = res.aaData[i].id;

                                if (res.aaData[i].Status == 1) {
                                    res.aaData[i][2] = "<button type=\"button\" class=\"btn btn-danger\"  onclick=\"deactivate('" + id1+ "','" + status + "')\">Deactivate</button>";
                                } else {
                                    res.aaData[i][2] = "<button type=\"button\" class=\"btn btn-success\"  onclick=\"activate('" + id1 + "','" + status + "')\">Activate</button>";
                                }
                                res.aaData[i][3] = '<div class="btn-group"><input type="button" form="' + i + '"  class="btn btn-info btn-small edit " id="' + id1 + '" value="Edit" >';
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
    
    $.post("setQualificationMasterStatus.do", {id: id, status: status}, function (result, status) {
    alert("Activated sucessfully", "success");
    display();
    });
}

function deactivate(id, status) {

    $.post("setQualificationMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Deactivated sucessfully", "success");
        display();
    });

}


function clear() {

    $('#qualification').val("");

}