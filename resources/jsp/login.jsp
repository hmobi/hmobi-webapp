<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="../css/hmobi.css"/>

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
                    //out.write("Welcome: " + user + ". Press <a href=\"" + request.getContextPath() + "/j_acegi_logout\">Logout</a>");
                    out.write("Welcome: " + user + ". Press <a href=\"../logout\">Logout</a>");
                %>
        </div>
        <div class="descript">
                <h1>Find a doctor near you.</h1>
                <p>hMobi allows you to find a doctor at a location convenient for you, and book appointments online </p>
        </div>
</body>
</html>