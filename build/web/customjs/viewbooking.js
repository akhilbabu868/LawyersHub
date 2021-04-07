/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var sessionID;
$(document).ready(function () {
    
    sessionID = $("#sessionlawyerID").val();
    viewbookingtable = $("#viewBookingDataTable").dataTable();
    displayBooking();
    
});


//function Listing DataTable
function displayBooking()
{
    viewbookingtable = $("#viewBookingDataTable").dataTable(
            {
                "bProcessing": true,
                "bDeferRender": true,
                "bSort": false,
                "bDestroy": true,
                "oLanguage": {
                    "sEmptyTable": "No data available "
                },
                "bServerSide": true,
                "sAjaxSource": "getBookingdetails.do",
                "sServerMethod": "POST",
                "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                                          aoData.push( { "name": "lawyerID", "value":sessionID} );
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
                                res.aaData[i][1] = res.aaData[i].Date;
                                res.aaData[i][2] = res.aaData[i].Title;
                                res.aaData[i][3] = res.aaData[i].des;
                                var status = res.aaData[i].Status;
                                var id1 = res.aaData[i].id;
                                var logid1 = res.aaData[i].UserId;
                                 console.log("status"+res.aaData[i].Status);
                                 console.log("logid1"+res.aaData[i].UserId);
                                 console.log("ID"+res.aaData[i].id);
//                                if (res.aaData[i].loginStatus == 1) {
//                                    res.aaData[i][4] = "<button type=\"button\" class=\"btn btn-danger\"  onclick=\"deactivate('" + id1+ "','" + status + "')\">Deactivate</button>";
//                                } else {
//                                    res.aaData[i][4] = "<button type=\"button\" class=\"btn btn-success\"  onclick=\"activate('" + id1 + "','" + status + "')\">Activate</button>";
//                                }
                                 res.aaData[i][4] = "<button type=\"button\" data-toggle=\"modal\" data-target=\"#exampleModalCenter\"class=\"btn btn-info btn-small view \"  onclick=\"view('" + logid1 + "')\">View</button>";
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
    
    $.post("getdata.do", {Viewid: id}, function (result, status) {
        console.log("result"+result);
        console.log(JSON.parse(JSON.stringify(result)))   
         var re = JSON.parse(JSON.stringify(result))
            var html = "";
        for (var i = 0; i < re.length; i++)
        {
          $("#name").text(re[i]["Fname"] +re[i]["Lname"] );
          $("#dist").text(re[i]["District"]);
          $("#tow").text(re[i]["Town"]);
          $("#addr").text(re[i]["Address"]);
          $("#mob").text(re[i]["Mobile"]);
          $('#userImg').prop('src', 'data:image/png;base64,' + re[i]["Image"]);
        
        }
        
    });
}


function activate(id, status) {
    
    $.post("setbookingStatus.do", {id: id, status: status}, function (result, status) {
    alert("Activated sucessfully", "success");
    displayBooking();
    });
}

function deactivate(id, status) {

    $.post("setbookingStatus.do", {id: id, status: status}, function (result, status) {
        alert("Deactivated sucessfully", "success");
        displayBooking();
    });

}
