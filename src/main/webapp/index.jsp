<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <h1>InstaGrimus ! </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                    <%
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        String UserName;
                        if (lg != null) {
                            UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                <li><a href="/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="upload.jsp">Upload</a></li>
                <li><a href="/Profile/<%=lg.getUsername()%>">Profile</a></li>
                        <form action="/Logout" method="post" >

                                <li>
                                   <input type="submit" name="Logout" value="Logout">
                                </li>

                        </form>
                    <%}
                            }else {%>
                <li><a href="register.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
                        <%UserName = "Anonymous";
                    }%>
            </ul>
        </nav>
        <footer>
            <ul>
                <li class="footer"><a href="/">Home</a></li>
                <li>&COPY; Andy V</li>
                <li>Welcome <%=UserName%> </li>

                    </a>
            </ul>
        </footer>
    </body>
</html>
