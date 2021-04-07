/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


    lawyertypetable = $("#lawyertypeDataTable").dataTable();
    display();

//    //FUNCTION FOR EDIT Datatable
    $("#lawyertypeDataTable").on('click', '.edit', function (e) {
        
        e.preventDefault();
        $("body").scrollTop(0);
        nRow = $(this).parents('tr')[0];
        var id = $(this).attr('id');

        $.post("editlawyertypeMaster.do", {id: id}, function (data, status) {
            Id = data.id;
            name = data.lawyertype;
            description = data.description;
            hiddenstatus =data.status;
            $("#lawyertype").val(name);
            $("#description").val(description);
            $('#HiddenID').val(Id);
            $('#statusHidden').val(hiddenstatus);
           $('#save').val("Update");
        });
    });


    $('#save').click(function () {

        var lawyertype = $('#lawyertype').val();
        var description = $('#description').val();
        var hiddenID = $('#HiddenID').val();
        var status = $('#statusHidden').val();

        $.post("savelawyertype.do",
                {
                    lawyer: lawyertype,
                    Description: description,
                    id: hiddenID,
                    Status: status,
                },
                function (data, status) {

                    var Hid = data.flag;
                    if (Hid == 2) {
                        alert(" Updated sucessfully");
                        lawyertypetable.fnDraw();
                        $('#save').val("Save");
                        clear();
                    } else {
                        alert(" Saved sucessfully");
                        lawyertypetable.fnDraw();
                        clear();
                    }
                });

    });


});





function display()
{
//   producttable.clear()

    lawyertypetable = $("#lawyertypeDataTable").dataTable(
            {
                "bProcessing": true,
                "bDeferRender": true,
                "bSort": false,
                "bDestroy": true,
                "oLanguage": {
                    "sEmptyTable": "No data available "
                },
                "bServerSide": true,
                "sAjaxSource": "getlawyertype.do",
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
                                var status = res.aaData[i].Status;
                                var id1 = res.aaData[i].id;

                                if (res.aaData[i].Status == 1) {
                                    res.aaData[i][3] = "<button type=\"button\" class=\"btn btn-danger\"  onclick=\"deactivate('" + id1+ "','" + status + "')\">Deactivate</button>";
                                } else {
                                    res.aaData[i][3] = "<button type=\"button\" class=\"btn btn-success\"  onclick=\"activate('" + id1 + "','" + status + "')\">Activate</button>";
                                }
                                res.aaData[i][4] = '<div class="btn-group"><input type="button" form="' + i + '"  class="btn btn-info btn-small edit " id="' + id1 + '" value="Edit" >';
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
    
    $.post("setLawyertypeMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Activated sucessfully", "success");
        lawyertypetable.fnDraw();
    });
}

function deactivate(id, status) {

    $.post("setLawyertypeMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Deactivated sucessfully", "success");
        lawyertypetable.fnDraw();

    });

}


function clear() {

    $('#lawyertype').val("");
    $('#description').val("");
    $('#statusHidden').val("");
}