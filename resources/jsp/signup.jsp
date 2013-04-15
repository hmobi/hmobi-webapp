<!DOCTYPE html>
<html>
<head>

<%
    out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/hmobi.css\"/>");
%>

<title> Sign up | hMobi </title>
</head>

<body>
	<div id="header">
		<h1 id="logo">
			hMobi 
		</h1>
	</div>
	<h1> Sign up for your own personal hMobi account! </h1>
	<form name="FirstNameForm">
		<p>First Name</p><input type="text" name="FirstNameItem"/>
	</form>
	<br/>
	<form name="LastNameForm">
		<p>Last Name</p><input type="text" name="LastNameItem"/>
	</form>
	<br/>
	<form name="EmailForm">
		<p>Email</p><input type="text" name="EmailItem"/>
	</form>
	<br/>
	<form name="PasswordForm">
		<p>Password</p><input type="text" name="PasswordItem"/>
	</form>
	<br/>
	<form name="ConfirmPassForm">
		<p>Confirm Password</p><input type="text" name="ConfirmPassItem"/>
	</form>
	<br/>
	<div id="button">Sign up!</div>
	<br/>



</body>
</html>