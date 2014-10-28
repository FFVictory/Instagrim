package uk.ac.dundee.computing.aec.instagrim.servlets;


import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
@WebServlet(name = "ProfilePic", urlPatterns = {"/ProfilePic","/ProfilePic/*"})
public class ProfilePic extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated me thod stub
        cluster = CassandraHosts.getCluster();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PicModel pm = new PicModel();
        pm.setCluster(cluster);
        String UserName = (String)request.getParameter("user");
        UUID id=UUID.fromString(request.getParameter("huj"));
        response.sendRedirect("/mkprof/"+UserName+"/"+id+"");
        //rd.forward(request,response);
    }
}