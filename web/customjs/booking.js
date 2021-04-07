/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var lawyerID;
$(document).ready(function (){
    
    var username =$("#hiddenUsername").val();
    console.log("username" +username);
    getID(username);
    
      $('#bookingdate').datepicker({
        autoclose: true,
        todayHighlight: true,
        startDate:  Date(),
        minViewMode: 1,
        maxViewMode: 1,
        format:'dd-mm-yyyy',
     });  
      
  
    
  
    
   
    var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);
    var queries = queryString.split("&");
    console.log("val" + queries);
    for (var i = 0; i < queries.length; i++)
    {
//  document.write(queries[i] + "<br>");
        lawyerID = queries[i];
        $("#hiddenLawyerID").val(lawyerID);
        getLawyerUsername(lawyerID);
    }

    
    
    
    $("#booking").click(function () {
        
         var UserID = document.getElementById("hiddenUserID").value;
         var LawyerID = document.getElementById("hiddenLawyerID").value;
         var bookDate = document.getElementById("bookingdate").value;
         var title = document.getElementById("title").value;
         var description = document.getElementById("description").value;
         console.log("UserID"+UserID)
         console.log("LawyerID"+LawyerID);
         console.log("bookDate"+bookDate);
         console.log("title"+title);
         console.log("description"+description);
          
        $.post("saveBooking.do", {
            User: UserID,
            Lawyer:LawyerID,
            BookDate:bookDate,
            Title:title,
            Description:description}, function (result) {
            console.log("rs" + result);
            var flag = result.flag;
            if(flag == 1){
                alert("Booking Sucessfull !");
                ClearField();
            }else{
                 alert("Todays Booking is Over !");
            }
           
           
        });
        
    });
    
});


function getLawyerUsername(Id)
{
    $.post("getLawyerID.do", {ID: Id}, function (result) {
        console.log("rs" + result);
        $("#hiddenLawyer").val(result);
    });
}


  $('#bookingdate').change(function () {
      
    var bookdate = $('#bookingdate').val();
    var username = $("#hiddenLawyer").val();
    console.log("uss"+username);
    var d = new Date(bookdate.split("-").reverse().join("-"));
    var dd = d.getDate();
    var mm = d.getMonth() + 1;
    var yy = d.getFullYear();
    var Bdate = yy + "-" + mm + "-" + dd;
    console.log("Bdate" +Bdate);
    
    
    
    $.post("listdates.do", {user: username, bookingdate: Bdate}, function (result, status) {
        var re = JSON.parse(JSON.stringify(result))
        console.log("//re" + re);
        if (re.length > 0)
        {
            alert("This Booking Date is Not Avaialble !");
            $('#bookingdate').val("");
            
        }
    });
});

 function getID(username)
{
     $.post("getuser.do", {user: username}, function (result, status) {
      var re = JSON.parse(JSON.stringify(result))
      console.log("//re"+re);
       if(re.length > 0)
       {
           var User = re;
          $("#hiddenUserID").val(User);
       }
    });
}


function ClearField()
{
    $('#bookingdate').val("");
    $("#title").val("");
    $("#description").val("");
}



