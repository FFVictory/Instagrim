<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <header>

        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>

        <nav>
            <ul>
                <li class="nav"><a href="/upload.jsp">Upload</a></li>
            </ul>
        </nav>

        <article>
            <h1>Your Pics</h1>
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            Pic p=null;
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                p = (Pic) iterator.next();

                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                String UserName="";
                if (lg != null) {
                    UserName = lg.getUsername();
                    if (lg.getlogedin()) {

                    }else {response.sendRedirect("/");

                    }}
                %>
                    <a href="/Image/<%=p.getSUUID()%>" ><img src="/Thumb/<%=p.getSUUID()%>"></a><br>
                    <form action="/mkprof/<%=UserName%>/<%=p.getSUUID()%>" method="get">
                        <ul>

                            <li>Make Profile<input type="submit" value="Make Profile">
                                <input type="hidden" name="huj"  value="<%=p.getSUUID()%>">
                                <input type="hidden" name="user" value="<%=UserName%>"></li>
                        </ul>
                    </form>
            <%
            }
        }

        %>

        <br/>
        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
