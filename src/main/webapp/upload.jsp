<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn" %>
<%--
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <h1>InstaGramulja ! </h1>
        <h2>Your world in Black and Whiters</h2>
        <nav>
            <ul>
                <%
                    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                    String UserName="";
                    if (lg != null) {
                        UserName = lg.getUsername();
                        if (lg.getlogedin()) {

                        } else {
                            response.sendRedirect("/");

                        }
                    }
                %>
                <li class="nav"><a href="upload.jsp">Upload</a></li>
            </ul>
        </nav>
 
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
