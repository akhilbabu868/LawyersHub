/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


    districttable = $("#distDataTable").dataTable();
    display();

    //FUNCTION FOR EDIT Datatable
    $("#distDataTable").on('click', '.edit', function (e) {
        
        e.preventDefault();
        $("body").scrollTop(0);
        nRow = $(this).parents('tr')[0];
        var id = $(this).attr('id');

        $.post("editdistrictMaster.do", {id: id}, function (data, status) {
            console.log("data" + data.district);
            console.log("ID" + data.id);
            Id = data.id;
            name = data.district;
            Status = data.status;
            $("#district").val(name);
            $('#HiddenID').val(Id);
            $('#statusHidden').val(Status);
           $('#save').val("Update");
        });
    });


    $('#save').click(function () {

        var district = $('#district').val();
        var hiddenID = $('#HiddenID').val();
        var status  = $('#statusHidden').val();
        $.post("savedistrict.do",
                {
                    districtname: district,
                    id: hiddenID,
                    Status:status
                },
        function (data, status) {

            var Hid = data.flag;
            if (Hid == 2) {
                alert(" Updated sucessfully");
                display();
                clear();
                $('#save').val("Save");
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
    districttable = $("#distDataTable").dataTable(
            {
                "bProcessing": true,
                "bDeferRender": true,
                "bSort": false,
                "bDestroy": true,
                "oLanguage": {
                    "sEmptyTable": "No data available "
                },
                "bServerSide": true,
                "sAjaxSource": "getDistrict.do",
                "sServerMethod": "POST",
                "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                    oSettings.jqXHR = $.ajax({
                        "dataType": 'json',
                        "type": "POST",
                        "url": sSource,
                        "data": aoData,
                        "success": function (res, stat)
                        {
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
    
    $.post("setDistrictMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Activated sucessfully", "success");
         display();
//        districttable.fnDraw();
    });
}

function deactivate(id, status) {

    $.post("setDistrictMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Deactivated sucessfully", "success");
         display();
//        districttable.fnDraw();

    });

}


function clear(){
  $('#HiddenID').val(0);  
  $('#district').val("");
  
}