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
/**
 * Created by Andrew on 28/10/2014.
 */
@WebServlet(name = "updateUser", urlPatterns = {"/updateUser","/updateUser/*"})
public class updateUser extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub`
        cluster = CassandraHosts.getCluster();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("CMON ");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CMON BLJATJ");
        String username=request.getParameter("userName");
        String first_name=request.getParameter("firstName");
        String last_name=request.getParameter("lastName");
        String country=request.getParameter("country");
        String email = request.getParameter("email");
        User us=new User();
        us.setCluster(cluster);
        us.updateUserInfo(username,first_name,last_name,country,email);
        response.sendRedirect("/");

    }
}

