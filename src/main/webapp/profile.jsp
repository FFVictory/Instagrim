<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn" %>
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

    </header>

</body>
</html>
