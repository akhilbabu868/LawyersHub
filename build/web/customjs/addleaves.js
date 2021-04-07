/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {


    $('#frmdate').datepicker({
        autoclose: true,
        todayHighlight: true,
        startDate:  Date(),
        minViewMode: 1,
        format:'dd-mm-yyyy',
      
    });
    $('#todate').datepicker({
        autoclose: true,
        todayHighlight: true,
        startDate:  Date(),
        minViewMode: 1,
        format:'dd-mm-yyyy',
      
    });
    
    leaveDataTable = $("#leaveDataTable").dataTable();
    display();

       $("#leaveDataTable").on('click', '.edit', function (e) {
        e.preventDefault();
        $("body").scrollTop(0);
        nRow = $(this).parents('tr')[0];
        var id = $(this).attr('id');

        $.post("editleaves.do", {id: id}, function (data, status) {
            Id = data.id;
            frmDate = data.fromdate;
            toDate = data.todate;
            $("#frmdate").val(frmDate);
            $("#todate").val(toDate);
            $('#HiddenID').val(Id);
           $('#save').val("Update");
        });
    });
   
      $("#leaveDataTable").on('click', '.delete', function (e) {
        e.preventDefault();
        $("body").scrollTop(0);
        nRow = $(this).parents('tr')[0];
        var id = $(this).attr('id');
        console.log("id"+id);
        $.post("deleteleaves.do", {id: id}, function (data, status) {
            console.log("data"+data);
            if(data ==1)
            {
                alert("Successfully Deleted !");
                display();
            }
        });
    });
   
   

    $('#save').click(function () {

        var frmDate = $('#frmdate').val();
        var toDate = $('#todate').val();
        var hiddenID = $('#HiddenID').val();
        var username = $('#hiddenUsername').val();
        $.post("saveleaves.do",
                {
                    fromDate: frmDate,
                    toDate: toDate,
                    Uname: username,
                    id: hiddenID
                },
        function (data, status) {
            var flag = data.flag;
            if (flag == 1) {

                alert("Saved Successfull !");
                display();

            } else {
                alert("Updated Successfull !");
                display();
            }

        })
    })

});


//Function List Data Table
function display()
{
//   producttable.clear()
     var Uname = $('#hiddenUsername').val();
    leaveDataTable = $("#leaveDataTable").dataTable(
            {
                "bProcessing": true,
                "bDeferRender": true,
                "bSort": false,
                "bDestroy": true,
                "oLanguage": {
                    "sEmptyTable": "No data available "
                },
                "bServerSide": true,
                "sAjaxSource": "getleaves.do",
                "sServerMethod": "POST",
                "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                                aoData.push( { "name": "username", "value":Uname} );
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
                                res.aaData[i][1] = res.aaData[i].FromDate;
                                res.aaData[i][2] = res.aaData[i].ToDate;
                                var id1 = res.aaData[i].id;
                                res.aaData[i][3] = '<div class="btn-group"><input type="button" form="' + i + '"  class="btn btn-danger btn-small delete " id="' + id1 + '" value="Delete" >';
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
