<%-- 
    Document   : OTP
    Created on : 27 Apr, 2020, 12:58:01 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction</title>
        <link rel="stylesheet" href="bootstrap4/bootstrap.min.css">
        <link href="customcss/otp.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap4/popper.min.js" type="text/javascript"></script>
        <script src="bootstrap4/bootstrap.min.js" type="text/javascript"></script>
        <script src="customjs/OTP.js" type="text/javascript"></script>
        <script>
         $(function() {
  'use strict';

  var body = $('body');

  function goToNextInput(e) {
    var key = e.which,
      t = $(e.target),
      sib = t.next('input');

    if (key != 9 && (key < 48 || key > 57)) {
      e.preventDefault();
      return false;
    }

    if (key === 9) {
      return true;
    }

    if (!sib || !sib.length) {
      sib = body.find('input').eq(0);
    }
    sib.select().focus();
  }

  function onKeyDown(e) {
    var key = e.which;

    if (key === 9 || (key >= 48 && key <= 57)) {
      return true;
    }

    e.preventDefault();
    return false;
  }
  
  function onFocus(e) {
    $(e.target).select();
  }

  body.on('keyup', 'input', goToNextInput);
  body.on('keydown', 'input', onKeyDown);
  body.on('click', 'input', onFocus);

})   
            
            
            
            
            
            
            
        </script>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="dialog">
                <button class="close">×</button>
                <h3>Please enter the 4-digit verification code we sent via EMAIL:</h3>
                <span>(we want to make sure it's you before we contact our movers)</span>
                <div id="form">
                    <input type="text" maxLength="4" size="4" min="0" id="OtpText" max="9" pattern="[0-9]{1}" />
                    <input type="hidden" value="${sessionScope.username }" id="hiddenUsername">
                    <input type="hidden"  id="hiddenUserID">
                    <button class="btn btn-primary btn-embossed"  id="Otp">Verify</button>
                </div>

            </div>
        </div>
    </body>
</html>
