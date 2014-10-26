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
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>

               
                <li><a href="upload.jsp">Upload</a></li>
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        String UserName;
                        if (lg != null) {
                            UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>

                <li><a href="/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <%}
                            }else{ UserName = "Anonymous";
                                %>
                 <li><a href="register.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <%
                                        
                            
                    }%>
            </ul>
        </nav>
        <footer>
            <ul>
                <li class="footer"><a href="/">Home</a></li>
                <li>&COPY; Andy V</li>
                <li>Welcome faggot named
                        <%=UserName%>
                </li>
                <li>
                    <a href="/" >Logout
                    <%
                        //HttpSession session=request.getSession();
                        session.removeAttribute("LoggedIn");
                    %>
                    </a>
                </li>
            </ul>
        </footer>
    </body>
</html>
