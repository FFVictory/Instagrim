<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn" %>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.Pic" %>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.models.PicModel" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.DataInputStream" %>
<%@ page import="java.io.DataOutputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.servlets.Image" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.*"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@ page import="java.security.Key" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 19/10/2014
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>


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
                Pic p = null;
                if (lg != null) {
                    UserName = lg.getUsername();
                    if(lg.getlogedin()) {
                        %><%=UserName%><%
                    }
                    }else{ UserName="Anonymous";response.sendRedirect("/");}
                        %>

        </h1>
        <article>
            <h1>Your Profile Pic</h1>
            <%
                p= (Pic)session.getAttribute("Pics");

                if (p== null) {
            %>
            <p>No Pictures found</p>
            <%
            } else {
                    System.out.println("Test");
                    lg = (LoggedIn) session.getAttribute("LoggedIn");
                    UserName="";
                    if (lg != null) {
                        UserName = lg.getUsername();
                        if (lg.getlogedin()) {

                        }else {

                        }}
            %>
            <a href="/Image/<%=p.getSUUID()%>" ><img src="/Thumb/<%=p.getSUUID()%>"></a><br>
                    <%
                        UserStore us =(UserStore) session.getAttribute("User");
                    %>
                    <form action="/updateUser" method="POST">
                        <ul>
                            <li>Profile for <%=us.getUser()%><input type="hidden" value="<%=us.getUser()%>" name="userName"></li>
                            <li>Your first name : <input type="text" name="firstName" value="<%=us.getFirstName()%>"></li>
                            <li>Your last name : <input type="text" name="lastName" value="<%=us.getLastName()%>"></li>
                            <li>Your country : <input type="text" name="country" value="<%=us.getCountry()%>"></li>
                            <%
                                Set<String> set = us.getSet();
                                String email = "";
                                for(String s : set)
                                {
                                    email= s;
                                    System.out.println("Email is "+email+"");
                                }

                            %>
                            <li>Your email : <input type="text" name="email" value="<%=email%>"></li>
                            <li><input type="submit" name="btn1" value="Update Profile"></li>
                        </ul>
                    </form>
            <%

                }

            %>

        </article>

    </header>
    <footer>
        <ul>
            <li class="footer"><a href="/">Home</a></li>
            <li>&COPY; Andy V</li>
            <li>Welcome <%=UserName%> </li>
            <li class="footer"><a href="/">Home</a></li>
            <form action="/Logout" method="post" >

                <li>
                    <input type="submit" name="Logout" value="Logout">
                </li>

            </form>

            </a>
        </ul>

    </footer>
</body>
</html>
