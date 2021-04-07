/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var packageID ;
$(document).ready(function () {
    
    var username = $("#hiddenUsername").val();
    console.log("username" + username);
    getID(username);
    

    packageID = getUrlVars()["PkgID"];
    console.log("id" + packageID);

    var generator = new IDGenerator();
    var TranscationId = generator.generate();
    console.log("TranscationId" + TranscationId);
      
       
   
    
    var OTPcheck=localStorage.getItem("Otp");
    console.log("name"+name);
    
    
    
    $("#Otp").click(function () {
      
        console.log("otptext" + $("#OtpText").val());
        console.log("OTPcheck" + OTPcheck);
       var UserId= $("#hiddenUserID").val();
        if ($("#OtpText").val() === OTPcheck)
        {
            alert("ok");
            $.post("paymentconfirm.do", {
                user: UserId,
                refID:TranscationId,
                pkgID:packageID
               }, function (result, status) {
                   console.log("res"+result);
                   console.log("res"+result.flag);
                
            });
            
        }
    
          
    
});
});





function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function IDGenerator() {
    this.length = 8;
    this.timestamp = +new Date;

    var _getRandomInt = function (min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    this.generate = function () {
        var ts = this.timestamp.toString();
        var parts = ts.split("").reverse();
        var id = "";

        for (var i = 0; i < this.length; ++i) {
            var index = _getRandomInt(0, parts.length - 1);
            id += parts[index];
        }

        return id;
    }
}


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







