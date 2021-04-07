/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
  var username = $("#hiddenUsername").val();
  displayUser(username);
  loadimage(username);
  
  
  
  $("#btnEdit").click(function (){
     $.post("editprofiledata.do", {user: username}, function (result, status) {
            console.log("result" + result);
            console.log(JSON.parse(JSON.stringify(result)))
            var re = JSON.parse(JSON.stringify(result))
            if (result.length > 0)
            {
                var Id = re[0]["ID"];
                var address = re[0]["Address"];
                var contact = re[0]["Mobile"];
                console.log("Id"+Id)
                $("#UserID").val(Id);
                $("#modaladdress").val(address);
                $("#modalmobile").val(contact);
            }
        })
     
  });
  
   
  
  
  
  
  
});

function submitmodalForm()
{
        var Address = document.getElementById("modaladdress").value;
        var Mobile = document.getElementById("modalmobile").value;
        var UserID = document.getElementById("UserID").value;
        var myform= document.getElementById('UpdateForm');
        var fd = new FormData(myform);
        var file_data = $('input[type="file"]')[0].files; // for multiple files

        fd.append("UserImage", file_data[0]);
        fd.append("ID", UserID);
        fd.append("address", Address);
        fd.append("mobile", Mobile);

        $.ajax({
            type: 'POST',
            url: 'updateprofileDetails.do',
            data: fd,
            success: function (data, status) {
                console.log("data"+data);
                if (data == 1) {
                    
                    alert("Updated Sucessfull  !");
                    var username = $("#hiddenUsername").val();
                    displayUser(username);
                    loadimage(username);
                }

                $('#UpdateForm')[0].reset();

            },
            error: function (data) {

                alert(" Error..! ");

            },
            cache: false,
            contentType: false,
            processData: false
        });

    }



//Function For Display User Profile
function displayUser(username)
{
     $.post("Userprofile.do", {user: username}, function (result, status) {
       
       console.log("data"+result);
       if(result.length > 0)
       {
           var UserID = result[0][0];
           var firstname = result[0][1];
           var lastname = result[0][2];
           var Dob = result[0][3];
           var address = result[0][4];
           var contact = result[0][6];
           var pkg = result[0][7];
           var desc = result[0][8];
           var image = 
           console.log("name"+firstname);
           $("#fname").html(firstname);
           $("#fullname").text(firstname+lastname);
           $("#lname").text(lastname);
           $("#dob").text(Dob);
           $("#addres").text(address);
           $("#mob").text(contact);
           $("#hiddenUserID").val(UserID);
           $("#pkgname").text(pkg);
           $("#pkgdes").text(desc);
//           $("#my_image").attr("src",image);
//           $("#hiddenImg").val();
       }
    });
    
    
}

//Function Load User Image
function loadimage(username)
{
    
    $.post("getUserImg.do", {email: username}, function (data, status) {
        $('#LogoA').prop('src', 'data:image/png;base64,' + data[0].Image);

    });

}

