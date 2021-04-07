/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    var LawyerID = getUrlVars()["ID"]; 
   getReviewsByLawyerId(LawyerID);
    
});



//Function Fetch ID From URL
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function getReviewsByLawyerId(LawyerId){
    
     $.post("getReviewByID.do", {Lawyer: LawyerId}, function (result) {
        console.log(JSON.parse(JSON.stringify(result)));
        var re = JSON.parse(JSON.stringify(result));
        var html = "";
        for (var i = 0; i < re.length; i++)
        {
            html += "<div class='col-xs-12 col-md-12' id='layerlist' style='margin-bottom:5px'>"
                    + "<article class = 'card animated fadeInLeft' style = 'padding: 20px' >"
                    + "<div class='col text-right'></div> <hr>"
                    + "<img  class='rounded-circle'  src='data:image/png;base64," + re[i]["Image"] + "' style='width:50px'>"
                    + "<div class = 'card-block'>"
                    + "<i><p>" + re[i]['Review'] + " </p><i>"
                    + "<br><hr><b>Email ID:</b>" + re[i]['Email'] + "</div>"
                    + "</div>"
                    + "</article>"
                    + " </div>";

        }
        $('#reviewContainer').append(html);
    });
    
}