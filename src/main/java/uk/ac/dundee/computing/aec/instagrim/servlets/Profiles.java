/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.*;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.UserStore;

/**
 *
 * @author Administrator
 */

@WebServlet(name = "Profiles",urlPatterns = {
        "/Profile",
        "/Profile/*"
})
public class Profiles extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }




    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.sendRedirect("/Instagrim");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String args[] = Convertors.SplitRequestPath(request);
        PicModel pm = new PicModel();
        pm.setCluster(cluster);
        //UUID picid = UUID.fromString(pm.getProfilePic(args[1]));
        // DisplayImage(1, picid, response);
        RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
        Session session = cluster.connect("instagrim");
        ResultSet rs = null;
        PreparedStatement ps = session.prepare("SELECT * from userprofiles where login=?");
        BoundStatement bs = new BoundStatement(ps);
        rs = session.execute(bs.bind(args[1]));
        UserStore us=null;
        if(rs.isExhausted()){

        }
        else{
            us = new UserStore();
            for (Row row : rs) {
                us.setUser(row.getString("login"),row.getString("country"),row.getString("first_name"),row.getString("last_name"));
                Set emailSetBind = new HashSet();
                us.setSet(row.getSet("email",String.class));
                //TO-DO
                // Implement Set suppport

            }
        }


        Pic lsPics = pm.getProfile(args[1]);


        HttpSession s1 = request.getSession();
        s1.setAttribute("Pics", lsPics);
        if(us!=null)s1.setAttribute("User",us);
        rd.forward(request, response);
        //int command;
        //DisplayImage(Convertors.DISPLAY_THUMB,args[1],  response);


    }


    private void DisplayImage(int type,UUID Image, HttpServletResponse response) throws ServletException, IOException {
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        System.out.print("Duuum du du duuum du du duuuuuuuum");
        Pic p = tm.getPic(type,Image);
        if (p != null) {
           System.out.println("This shit is null son , this shit is null");

            OutputStream out = response.getOutputStream();

            response.setContentType(p.getType());
            response.setContentLength(p.getLength());
            // out.write(Image);
            InputStream is = new ByteArrayInputStream(p.getBytes());
            BufferedInputStream input = new BufferedInputStream(is);
            byte[] buffer = new byte[8192];
            for (int length = 0; (length = input.read(buffer)) > 0; ) {
                out.write(buffer, 0, length);
            }
            out.close();

        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
