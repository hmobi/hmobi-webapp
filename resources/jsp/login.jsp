<!DOCTYPE html>
<html>
<head>
<%
    out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/hmobi.css\"/>");
%>

<title>Find a Doctor | hMobi</title>

</head>
<!-- Temporary logic to ensure that non-admins don't see any pages with the dashboard class -->
<body>
        <div id="header">
                <h1 id="logo">
                        hMobi
                </h1>

                <%
                    String user = (String) request.getAttribute("user");
                    if(user != null)
                    {
                        //out.write("<img src=\"" + request.getContextPath() + "/img/" + user + ".png\"/>");
                        out.write("Welcome: " + user + ". Press <a href=\"" + request.getContextPath() + "/j_acegi_logout\">Logout</a>");
                    }
                    else
                    {
                        String errorMsg = (String) request.getAttribute("errorMsg");
                        if(errorMsg != null)
                        {
                            out.write("Error: " + errorMsg + "<br><br>");
                        }

                        out.write("<form id=\"login\" name=\"loginForm\" action=\"" + request.getContextPath() + "/j_acegi_security_check\" method=\"POST\">");
                        out.write("<input type=\"text\" name=\"j_username\"/>");
                        out.write("<input type=\"password\" name=\"j_password\"/>");
                        out.write("<input type=\"submit\" value=\"Login\"/>");
                        out.write("<a href=\"" + request.getContextPath() + "/signup.html\">Sign Up</a>");
                        out.write("</form>");
                    }

                %>
        </div>
        <div class="descript">
                <h1>Find a doctor near you.</h1>
                <p>hMobi allows you to find a doctor at a location convenient for you, and book appointments online </p>
        </div>
        <div class="search">
                <form id="specialty" name="SearchSpecialty">
                        <p>Specialty</p><input type="text" name="SpecialtyItem"/>
                </form>
                <form id="location" name="SearchLocation">
                        <p>Location</p><input type="text" name="LocationItem"/>
                </form>
                <input id="button" type="button" onclick="check(this.form)" value="Search"/>
        </div>

</body>
</html>