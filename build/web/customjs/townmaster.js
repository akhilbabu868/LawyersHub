/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
    
    townDataTable = $("#townDataTable").dataTable();
    display();
    districtList();

    $("#townDataTable").on('click', '.edit', function (e) {

        e.preventDefault();
        $("body").scrollTop(0);
        nRow = $(this).parents('tr')[0];
        var id = $(this).attr('id');

        $.post("edittownMaster.do", {id: id}, function (data, status) {
            console.log("districtid" + data.districtid);
            console.log("town" + data.town);
            console.log("status" + data.status);
            Id = data.id;
            districtID=data.districtid; 
            town = data.town;
            Status = data.status;
            $('#District').val(districtID);
            $("#town").val(town);
            $('#HiddenID').val(Id);
            $('#statusHidden').val(Status);
            $('#save').val("Update");
        });
    });
    
    
    
    
    //Function For Save 
   $('#save').click(function () {

        var town = $('#town').val();
        var district = $('#District').val();
        var hiddenID = $('#HiddenID').val();
        var status  = $('#statusHidden').val();
        $.post("savetown.do",
                {
                    Town: town,
                    District: district,
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

 

//Function For Display Table
function display()
{
    townDataTable = $("#townDataTable").dataTable(
            {
                "bProcessing": true,
                "bDeferRender": true,
                "bSort": false,
                "bDestroy": true,
                "oLanguage": {
                    "sEmptyTable": "No data available "
                },
                "bServerSide": true,
                "sAjaxSource": "getTown.do",
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
                                res.aaData[i][1] = res.aaData[i].Town;
                                res.aaData[i][2] = res.aaData[i].District;
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










function districtList() {
    $.post("districtList.do",{},function(result){
         var html='<option value="0" > --- Select District --- </option>';
        districtArray = new Array();
        for(var i=0; i< result.length; i++)
        {
            html +='<option value="'+result[i][0]+'">'+result[i][1]+'</option>';
            districtArray.push(result[i][0]);
        }
        $('#District').html(html);
        $('#District').select2("val", 0);
    });
}



function activate(id, status) {
    
    $.post("setTownMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Activated sucessfully", "success");
         display();
//        districttable.fnDraw();
    });
}

function deactivate(id, status) {

    $.post("setTownMasterStatus.do", {id: id, status: status}, function (result, status) {
        alert("Deactivated sucessfully", "success");
         display();
//        districttable.fnDraw();

    });

}





function clear(){
  $('#District').val("");
  $('#town').val("");
  
}