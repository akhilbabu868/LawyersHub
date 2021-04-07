<%-- 
    Document   : login
    Created on : 21 Dec, 2019, 8:08:40 PM
    Author     : ASUS
--%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login </title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
<style>
  a:link {
  color: #f44336;
}


</style>     
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w">
					<span class="login100-form-title p-b-32">
						 Login
					</span>

					<span class="txt1 p-b-11">
						Email
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Username is required">
						<input class="input100" type="text" id="inputUsername" name="username" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						Password
					</span>
					<div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" id="inputPassword" name="pass" >
						<span class="focus-input100"></span>
					</div>
					
					<div class="flex-sb-m w-full p-b-48">
                                            
						<input type="button"  value="login" class="btn btn-primary" id="btnlogin" name="login">
                                            <div>
                                               
                                                <a href="Forgot.do" class="txt3">Forgot Password?</a><br>
                                                <a href="userregistration.do" class="txt3">User Sign Up</a><br> 
                                                <a href="lawyer.do" class="txt3">Lawyer Sign Up</a>

                                            </div>
                                             
					</div>

					<div class="container-login100-form-btn">
<!--						<button class="btn btn-primary"id="login">
							Login
						</button>-->
            
					</div>

				</form>
			</div>
		</div>
	</div>
	

	
	
<!--===============================================================================================-->

</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script src="customjs/login.js"></script>
</html>