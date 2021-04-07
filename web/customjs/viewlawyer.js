/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
    viewlawyertable = $("#viewLawyerDataTable").dataTable();
    displayLawyers();
});


function displayLawyers()
{
//   producttable.clear()

    viewlawyertable = $("#viewLawyerDataTable").dataTable(
            {
                "bProcessing": true,
                "bDeferRender": true,
                "bSort": false,
                "bDestroy": true,
                "oLanguage": {
                    "sEmptyTable": "No data available "
                },
                "bServerSide": true,
                "sAjaxSource": "getLawyerdetails.do",
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
                                res.aaData[i][1] = res.aaData[i].Fname;
                                res.aaData[i][2] = res.aaData[i].Lname;
                                res.aaData[i][3] = res.aaData[i].EmailId;
                                res.aaData[i][4] = res.aaData[i].typeoflawyer;
                                var status = res.aaData[i].loginStatus;
                                var id1 = res.aaData[i].id;
                                var logid1 = res.aaData[i].loginID;
                                console.log("status" + res.aaData[i].loginStatus);
                                console.log("logid1" + res.aaData[i].loginID);
                                console.log("ID" + res.aaData[i].id);
                                if (res.aaData[i].loginStatus == 1) {
                                    res.aaData[i][5] = "<button type=\"button\" class=\"btn btn-danger\"  onclick=\"deactivate('" + logid1 + "','" + status + "')\">Deactivate</button>";
                                } else {
                                    res.aaData[i][5] = "<button type=\"button\" class=\"btn btn-success\"  onclick=\"activate('" + logid1 + "','" + status + "')\">Activate</button>";
                                }
//                                res.aaData[i][5] = '<div class="btn-group"><input type="button" form="' + i + '"  class="btn btn-info btn-small view " id="' + id1 + '" value="View" >';
                                res.aaData[i][6] = "<button type=\"button\" class=\"btn btn-info btn-small view \" data-toggle=\"modal\" data-target=\"#lawyerModalCenter\" onclick=\"view('" + id1 + "')\">View</button>";
                                '</div>';
                            }
                            fnCallback(res);
                        }

                    });
                },
                "sPaginationType": "full_numbers"
            });
}


function view(id) {

    $.post("getLawyerdata.do", {Viewid: id}, function (result, status) {
        console.log("result"+result);
        console.log(JSON.parse(JSON.stringify(result)))   
         var re = JSON.parse(JSON.stringify(result))
        for (var i = 0; i < result.length; i++)
        {
          $("#name").text(re[i]["Fname"] +re[i]["Lname"] );
          $("#dob").text(re[i]["Dob"]);
          $("#gen").text(re[i]["Gender"]);
          $("#addr").text(re[i]["Address"]);
          $("#quali").text(re[i]["Qualification"]);
          $("#type").text(re[i]["Lawyertype"]);
          $('#lawyerImg').prop('src', 'data:image/png;base64,' + re[i]["Image"]);


        }

    });
}



function activate(id, status) {
    console.log("activateID" + id);
    $.post("setUserStatus.do", {id: id, status: status}, function (result, status) {
        alert("Activated sucessfully", "success");
        viewlawyertable.fnDraw();
    });
}

function deactivate(id, status) {
    console.log("DeactivateID" + id);
    $.post("setUserStatus.do", {id: id, status: status}, function (result, status) {
        alert("Deactivated sucessfully", "success");
        viewlawyertable.fnDraw();

});

}
