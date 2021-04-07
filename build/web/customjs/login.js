/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


 


});

// Function for Login
   $('#btnlogin').click(function () {

    
        var username = $('#inputUsername').val()
        var password = $('#inputPassword').val()

        $.post("auth.do",
                {
                    username: username,
                    password: password
                },
            function (data, status) {
            var result = JSON.parse(JSON.stringify(data));
            console.log("rs"+result);
            if (result.length > 0)
            {
                var Role = result[0]['Role'];
                var Status = result[0]['Status'];
                if(Role == 1 && Status==1){
                   window.location = "Admin.do";
                }else if(Role == 2 && Status==1){
                    window.location = "Userdashboard.do";
                }else if(Role == 3 && Status==1){
                    window.location = "Lawyerdashboard.do";
                }else if(Role == 2 && Status==0 ||Role == 3 && Status==0){
                    
                     alert("Please Wait For Admin approval !");
                }
             }else{
                
                alert("Invalid Username and Password !");
            }
        })

    });
    
