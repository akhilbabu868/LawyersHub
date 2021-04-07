/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
    
    showFullpkg();
    
//   lawyertypeList() ;
//   showFulllawyers();
//   
//   $(".solTitle a").on('click',function(e){
//            var con =  $(this).attr('value');
//            window.location = "booking.do";
//            console.log("con"+con);
//        });
    
});





function showFullpkg()
{
    $.ajax({
        type: "POST",
        async: false,
        timeout: 500,
        url: "ShowfullPkg.do",
        contentType: "application/json",
        dataType: "json",
        success: function(result)
        {
        console.log(JSON.parse(JSON.stringify(result)))   
        var re = JSON.parse(JSON.stringify(result))
        var html = "";
          for(var i=0; i< re.length; i++)
            {
                if(re[i]['Rate'] > 0)
                {
                  
                     html += " <div class='col-12 col-sm-6 col-lg-4'>"
                        + " <div class='card'>"
                        + "<div class='card-header' style='text-align: center'>" + re[i]['Packagename'] +" </div>"
                        + "<div class='card-body'>"
                        + "<div class='col text-center'>"
                        + "<h1 class='card-title'>Rs " + re[i]['Rate'] +" </h1>"
                        + "<h4 class='card-subtitle mb-2'>Per Yearly</h4><hr>"
                        + "<h6 class='card-subtitle mb-3'>Features</h6><hr>"
                        + "<p class='card-text'>" + re[i]['Description'] +"</p>"
                        + "<button type='button' onclick='buypkg("+ re[i]['Rate']+","+ re[i]['ID']+")'  class='btn btn-danger free'>Buy Now</button>"
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + " </div>";
                }
              
            } 
            $('#lawyerContainer').append(html);
        }
    });
}

 
 function buypkg(amount,Id){
    var Amount = amount;
    var ID = Id;
    window.location = 'paymentview.do?rate='+Amount+'&id='+ID;
 }