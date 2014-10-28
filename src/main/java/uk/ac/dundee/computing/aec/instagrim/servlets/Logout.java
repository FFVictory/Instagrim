package uk.ac.dundee.computing.aec.instagrim.servlets;


import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
@WebServlet(name = "Logout", urlPatterns = {"/Logout" ,"/Logout/*"})
public class Logout extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session=request.getSession();
            LoggedIn lg =(LoggedIn) session.getAttribute("LoggedIn");
            lg.setLogedout();
            session.removeAttribute("LoggedIn");
            RequestDispatcher rd = request.getRequestDispatcher("/");
            rd.forward(request, response);
        }

    }


