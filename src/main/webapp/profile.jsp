<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn" %>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.Pic" %>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.models.PicModel" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.DataInputStream" %>
<%@ page import="java.io.DataOutputStream" %>
<%@ page import="java.io.IOException" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 19/10/2014
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html; charset=UTF-8">
    <title>Instagrind</title>
    <link rel="stylesheet" type="text/css" href="Styles.css" />
</head>
<body>
    <header>
        <h1>Profile for
            <%
                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                String UserName;
                if (lg != null) {
                    UserName = lg.getUsername();
                    if(lg.getlogedin()) {
                        %><%=UserName%><%
                    }
                    }else{ UserName="Anonymous";}
                        %>

        </h1>
        <form method="POST" action="upload">
        <ul>
            <li>
                <%
                    PicModel pm = new PicModel();
                    java.util.LinkedList<Pic> Pics = pm.getPicsForUser(UserName);
                    // get the image from the database
                    byte[] imgData = Pics.getFirst().getBytes();

                    // display the image
                    response.setContentType("image/gif");
                    OutputStream o = response.getOutputStream();
                    o.write(imgData);
                    o.flush();
                    o.close();
                %>
            </li>
            <li><a href="upload.jsp">Upload Yo</a></li>
        </ul>
        </form>
    </header>

</body>
</html>
