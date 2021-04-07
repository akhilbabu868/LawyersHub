/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
    
    $('#dob').datepicker({
        autoclose: true,
        todayHighlight: true
    });
   qulificationList();
   lawyertypeList();
   districtList();
   
   
   
   //    loadimage(id);

    $("#file").change(function () {

        var file = $('input[type=file]').prop('files')[0];
        console.log("f" + file);
        var mime = file.type;
        console.log("mime" + mime);
        if ((mime == 'image/png') || (mime == 'image/jpeg')) {   
            
        }else{
             alert('PNG or JPG files only!');
             $("#file").val("");
             event.preventDefault(); 
        }

    });
    
   // Validation Which ALLOWS ONLY Numbers
    
     $("#mobile").bind("keypress", function (e) {
          var keyCode = e.which ? e.which : e.keyCode
          if (!(keyCode >= 48 && keyCode <= 57)) {
            return false;
          }
      });
    
   
    //Email Validation
    
    $("#email").change(function (){
        
        var email = $("#email").val();
        var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (!regex.test(email)) {
           alert("Invalid Email");
           $("#email").val("");
        }else{
            $.post("checkEmail.do",
                    {
                        emailId: email
                    },
            function (result) {
              
              console.log("res"+result);
              if(result.length > 0)
              {
                  alert("Email ID Already Exisist");
                  $("#email").val("");
              }

            });
            
        }
    });
    
    
    
    

});
   
  
  
  function submitForm()
{
    
        var formdata = new FormData($('#LawyerRegistrationForm')[0]);
        var districtId = document.getElementById("District").value;
        var qualificationId = document.getElementById("qualification").value;
        var lawyertypeId = document.getElementById("lawyertype").value;
        var username = document.getElementById("email").value;
        var myform= document.getElementById('LawyerRegistrationForm');
        var fd = new FormData(myform);
        var file_data = $('input[type="file"]')[0].files; // for multiple files

        fd.append("LawyerImage", file_data[0]);
        fd.append("district", districtId);
        fd.append("qualification", qualificationId);
        fd.append("lawyertype", lawyertypeId);
        fd.append("username", username);
        
           
        if( $("#firstname").val() == "")
        {
            alert("Pls enter First Name");
            
        }else if($("#lastname").val() == ""){
            
             alert("Pls enter Last Name");
            
        }else if($("#address").val() == ""){
            
             alert("Pls enter Address Name");
            
        }else if($("#dob").val() == ""){
            
             alert("Pls Date of Birth ");
             
        }else if($("#District").val() == ""){
            
             alert("Pls Select of District ");
             
        }else if($("#qualification").val() == ""){
            
             alert("Pls select of qualification");
             
        }else if($("#email").val() == ""){
            
             alert("Pls enter of emailID ");
             
             
        }else if($("#file").val() == ""){
            
             alert("Pls Choose of Image ");
             
        }
        else if($("#mobile").val() == ""){
            
             alert("Pls enter of mobile number ");
        }
        else{
        
        
        $.ajax({
            type: 'POST',
            url: 'Lawyersave.do',
            data: fd,
            success: function (data, status) {
                console.log("data"+data);
                if (data == 1) {
                    
                    alert("Registration Sucessfull Wait For Admin Approval !");
                    window.location = "login.do";
                }

                $('#LawyerRegistrationForm')[0].reset();

            },
            error: function (data) {

                alert(" Error..! ");

            },
            cache: false,
            contentType: false,
            processData: false
        });

    }

  
}
  
  
  
  
  
  
  
  function qulificationList() {
    $.post("qualificationList.do",{},function(result){
         var html='<option value="0" > --- Select Qualification --- </option>';
        qualificationArray = new Array();
        for(var i=0; i< result.length; i++)
        {
            html +='<option value="'+result[i][0]+'">'+result[i][1]+'</option>';
            qualificationArray.push(result[i][0]);
        }
        $('#qualification').html(html);
        $('#qualification').select2("val", 0);
    });
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

function districtList() {
    $.post("districtList.do",{},function(result){
         var html='<option value="0" > --- Select District --- </option>';
        districtArray = new Array();
        for(var i=0; i< result.length; i++)
        {
            html +='<option value="'+result[i][0]+'">'+result[i][1]+'</option>';
            districtArray.push(result[i][0]);
        }
        $('#District').html(html);
        $('#District').select2("val", 0);
    });
}

function groupDropDown(districtId)
{
    var DistrictID = districtId;
    $.post("ListTown.do",
            {
                district: DistrictID
            },
    function (result) {
        
            var html ='';
        townArray = new Array();
        for(var i=0; i< result.length; i++)
        {
            html +='<option value="'+result[i][0]+'">'+result[i][1]+'</option>';
            townArray.push(result[i][0]);
        }
        $('#Town').html(html);
        $('#Town').select2("val", "1");
      
       
    });  
}