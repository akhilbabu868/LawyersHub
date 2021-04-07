/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
    
  lawyertypeList() ;
  var email = $("#hiddenUsername").val();
  getUserID(email);
   
        
    $('#btnsearch').click(function () {

        $('#lawyerContainer').html("");
        var typeId = $("#lawyertype").val();
        console.log("tyyype"+typeId);
        $.post("ShowLawyersById.do", {type: typeId},
        function (data, status) {
            console.log(JSON.parse(JSON.stringify(data)))   
            var re = JSON.parse(JSON.stringify(data))
            var html = "";
            var forone ='';
            var fortwo ='';
            var forthree='';
            var forfour ='';
            var forfive ='';
            for (var i = 0; i < re.length; i++)
            {
                switch(re[i]['Rateing']){
                    case 1:
                        forone='checked';
                        break;
                     case 2:
                        forone='checked';
                        fortwo='checked';
                        break;
                     case 3:
                        forone='checked';
                        fortwo='checked';
                        forthree='checked';
                        break;
                     case 4:
                        forone='checked';
                        fortwo='checked';
                        forthree='checked';
                        forfour='checked';
                        break;
                     case 5:
                        forone='checked';
                        fortwo='checked';
                        forthree='checked';
                        forfour='checked';
                        forfive='checked';
                        break;
                        
                }
                var Id = re[i]['ID'];
                html += "<div class='col-xs-12 col-md-4' id='layerlist' style='margin-bottom:5px'>"
                        + "<article class = 'card animated fadeInLeft' style = 'padding: 20px' >"
                        + "<img  class ='card-img-top img-responsive' src='data:image/png;base64," + re[i]["Image"] + "'>"
                        + "<div class = 'card-block'>"
                        + "<input type='hidden' value='"+re[i]['ID']+"' id='lawyerID'>"
                        + "<h4 class = 'text-muted'><b>Name:  " + re[i]['Fname'] + " " + re[i]['Lname'] + "</b></h4>"
                        + "<p>Address:  " + re[i]['Address'] + " </p>"
                        + "<p>Email:  " + re[i]['Email'] + " </p>"
                        + "<p>Contact:  " + re[i]['Mobile'] + "</p>"
                        + "<button type='button'  onclick='Booking("+Id+")'  id='book'' class='btn btn-warning'>Book Now</button>"
                        +"&nbsp&nbsp<button type='button'onclick='SetID("+re[i]['ID']+")' id='review'data-toggle='modal' data-target='#RateingModal' class='btn btn-primary'>Add Review</button>"
                        +" <fieldset class='rating' style='margin-left: 60px;margin-right: 100px'>"
                        +"<input type='radio' id='star5' name='rating5' value='5' "+forfive+"/><label class = 'full' for='star5' title='Awesome - 5 stars'></label>"
                        +"<input type='radio' id='star4' name='rating4' value='4' "+forfour+" /><label class = 'full' for='star5' title='Awesome - 4 stars'></label>"
                        +"<input type='radio' id='star3' name='rating3' value='3' "+forthree+" /><label class = 'full' for='star5' title='Awesome - 3 stars'></label>"
                        +"<input type='radio' id='star2' name='rating2' value='2' "+fortwo+" /><label class = 'full' for='star5' title='Awesome - 2 stars'></label>"
                        +"<input type='radio' id='star1' name='rating1' value='1' "+forone+" /><label class = 'full' for='star5' title='Awesome - 1 stars'></label>"
                        +" </fieldset>"
                        + "</article>"
                        + " </div>";
        }
            $('#lawyerContainer').append(html);
             $('#lawyertype').val(0);
        });
         
    })
    
    
    //function for add Rateing And Review
    
    $("#btnReview").click(function(){
       
     var Rateing = $("input[name='rating']:checked").val();
     var review = $("#comment").val();
     var lawyerId = $("#lawyrId").val();
     var CurrentUserID=$("#hiddenUserId").val();
     console.log("rate"+Rateing);
     
       if($("#comment").val() == ""){
           
           alert("Pls Add comment");
       }else if($("input[name='rating']:checked").val() == undefined)
       {
            alert("Pls Add Rateing");
           
       }else{
     
        $.post("addreview.do",
                {
                    rateing: Rateing,
                    review: review,
                    lawyerID: lawyerId,
                    userID: CurrentUserID
                }, 
                function (result) {
                var flag = result.flag;
              if(flag =1){
                  alert("Review Added Succesfully !");
                  $("#comment").val("");
                  $("input[name='rating']:checked").val("");
                  $("#lawyrId").val("");
                  window.location.reload();
              }
        });
       }
    });
});



//Special Function
function SetID(Id)
{
    var ID = Id;
    $("#lawyrId").val(ID);
    $("#Idlawyr").val(ID);
}

function Booking(Id)
{
    console.log("Username"+Id);
     var ID = Id;
    console.log("lawyrID"+ID);
    window.location = 'booking.do?' + ID;
    
}

function lawyertypeList() {
    $.post("lawyertypeList.do",{},function(result){
         var html='<option value="0" > --- Select Lawyer type --- </option>';
         lawyertypeArray = new Array();
        for(var i=0; i< result.length; i++)
        {
            html +='<option value="'+result[i][0]+'">'+result[i][1]+'</option>';
            lawyertypeArray.push(result[i][0]);
        }
        $('#lawyertype').html(html);
        $('#lawyertype').select2("val", 0);
    });
}


function showFulllawyers(Id)
{
    console.log("id"+Id);
    $.ajax({
        type: "POST",
        async: false,
        timeout: 500,
        url: "ShowLawyers.do",
        ID: Id,
        contentType: "application/json",
        dataType: "json",
        success: function(result)
        {
        console.log(JSON.parse(JSON.stringify(result)))   
        var re = JSON.parse(JSON.stringify(result))
        var html = "";
          for(var i=0; i< re.length; i++)
            {
              html += "<div class='col-xs-12 col-md-4' id='layerlist' style='margin-bottom:5px'>"
                      +"<article class = 'card animated fadeInLeft' style = 'padding: 20px' >"
                        +"<img  class ='card-img-top img-responsive' src='data:image/png;base64,"+re[i]["Image"]+"'>"                       
                        +"<div class = 'card-block'>"
                        +"<h4 class = 'text-muted'>Name:  "+ re[i]['Fname'] +" "+ re[i]['Lname'] +"</h4>"
                        +"<h5 class = 'text-muted'>Address:  " + re[i]['Address'] +" </h5>"
                        +"<h5 class = 'text-muted'>Email:  " + re[i]['Email'] +" </h5>"
                        +"<h5 class = 'text-muted'>Contact:  " + re[i]['Mobile'] +" </h5>"
                        +"<div class='solTitle'> <a href='#' class = 'btn btn-warning'value=" + re[i]['Email'] +" >Book Now</a></div>"
                        +"</div>"
                        +"</article>"
                       +" </div>";

            } 
            $('#lawyerContainer').append(html);
        }
    });
}
    
    
    //Function For Fetch UserId
function getUserID(email)
{
    $.post("getUserID.do", {email: email}, function (result) {
        $("#hiddenUserId").val(result);
    });

}

function showReview(){
    
    var LawyerID = document.getElementById("Idlawyr").value;
    window.location.assign("showreviews.do?ID="+LawyerID);

}

   