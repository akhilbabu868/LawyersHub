/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

 var emailIdUser = $("#emailUser").val();
//For Card Number formatted input
    var cardNum = document.getElementById('cr_no');
    cardNum.onkeyup = function (e) {
        if (this.value == this.lastValue)
            return;
        var caretPosition = this.selectionStart;
        var sanitizedValue = this.value.replace(/[^0-9]/gi, '');
        var parts = [];

        for (var i = 0, len = sanitizedValue.length; i < len; i += 4) {
            parts.push(sanitizedValue.substring(i, i + 4));
        }
        for (var i = caretPosition - 1; i >= 0; i--) {
            var c = this.value[i];
            if (c < '0' || c > '9') {
                caretPosition--;
            }
        }
        caretPosition += Math.floor(caretPosition / 4);

        this.value = this.lastValue = parts.join(' ');
        this.selectionStart = this.selectionEnd = caretPosition;
    }

    //For Date formatted input
    var expDate = document.getElementById('exp');
    expDate.onkeyup = function (e) {
        if (this.value == this.lastValue)
            return;
        var caretPosition = this.selectionStart;
        var sanitizedValue = this.value.replace(/[^0-9]/gi, '');
        var parts = [];

        for (var i = 0, len = sanitizedValue.length; i < len; i += 2) {
            parts.push(sanitizedValue.substring(i, i + 2));
        }
        for (var i = caretPosition - 1; i >= 0; i--) {
            var c = this.value[i];
            if (c < '0' || c > '9') {
                caretPosition--;
            }
        }
        caretPosition += Math.floor(caretPosition / 2);

        this.value = this.lastValue = parts.join('/');
        this.selectionStart = this.selectionEnd = caretPosition;
    }
    
    var Rate = getUrlVars()["rate"];
    var ID = getUrlVars()["id"];
    console.log("num"+Rate);
    console.log("ID"+ID);
    $("#amount").html(Rate);
    $("#PkgId").val(ID);

    var OTP =   generateOTP();
    console.log("otp"+OTP);
 
     localStorage.setItem("Otp", OTP);

    $('#pay').click(function () {

       var pkgID =  $("#PkgId").val();
       
        if ($("#name").val() == "")
        {
            alert("Please Enter  Card Name");

        } else if ($("#cr_no").val() == "" ) {

            alert("Please Enter Debit Card Number");

        } else if ($("#exp").val() == "")
        {
            alert("Please Enter Expaire date");

        } else if ($("#cvv").val() == "") {

            alert("Please Enter CVV");
           
        } else {
            
            $.post("sendOTP.do", {otp: OTP, email: emailIdUser}, function (data, status) {
                console.log("data" + data.flag);
            });
           
            window.location = 'transaction.do?PkgID='+pkgID;
        }
    });
});

//==================================================================================================================================//
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
//=============================================================================================================================//

///Function FOR Generate OTP

function generateOTP()
{
    var digits = '0123456789';

    var otpLength = 4;

    var otp = '';

    for(var i=1; i<=otpLength; i++)

    {
        var index = Math.floor(Math.random()*(digits.length));
        otp = otp + digits[index];
    }
    return otp;

}



