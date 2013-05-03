<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/hmobi.css"/>
<title>Find a Doctor | hMobi</title>

</head>
<!-- Temporary logic to ensure that non-admins don't see any pages with the dashboard class -->
<body>
        <div id="header">
                <h1 id="logo">
                        hMobi
                </h1>

                <%
                    String errorMsg = (String) request.getAttribute("errorMsg");
                    if(errorMsg != null)
                    {
                        out.write("Error: " + errorMsg + "<br><br>");
                    }
                %>
                <form id="login" name="loginForm" action="security_check" method="POST">
                        <input type="text" name="j_username"/>
                        <input type="password" name="j_password"/>
                        <input type="submit" value="Login"/>
                </form>

                <form id="loginGoogle" name="loginFormGoogle" action="google.html" method="POST">
                        <input type="submit" value="Login with Google"/>
                </form>

                <a href="signup.html">Sign Up</a>


        </div>
        <div class="descript">
                <h1>Find a doctor near you.</h1>
                <p>hMobi allows you to find a doctor at a location convenient for you, and book appointments online </p>
        </div>
        <div class="search">
                <form id="search" name="search" action="search.html">
                        <p>Specialty</p><input type="text" name="speciality"/>
                        <p>Location</p><input type="text" name="location"/>
                        <input id="button" type="submit" value="Search"/>
                </form>

        </div>

</body>
</html>