/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    public boolean RegisterUser(String username, String Password,String first_name,String last_name,String email,String postcode,String address,String country){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name,last_name,email,addresses,country) Values(?,?,?,?,?,?,?)");
        //Set emailSetBind = "{'"+email+"'}";
        Set emailSetBind = new HashSet();
        emailSetBind.add(email);
        Map addressMapBind = new HashMap();
        addressMapBind.put(postcode,address );
       // String addressMapBind="{'"+postcode+"' : '"+address+"'}";
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,first_name,last_name,emailSetBind,addressMapBind,country));
        //We are assuming this always works.  Also a transaction would be good here !

        return true;
    }
    public void updateUserInfo(String userName,String firstName,String lastName,String country,String email){
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("UPDATE userprofiles SET first_name=? WHERE login=?");
        BoundStatement bs = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute(
                bs.bind(firstName,userName));
        ps = session.prepare("UPDATE userprofiles SET last_name=? WHERE login=?");
        bs = new BoundStatement(ps);
        rs = session.execute(
                bs.bind(lastName,userName));
        ps = session.prepare("UPDATE userprofiles SET country=? WHERE login=?");
        bs = new BoundStatement(ps);
        rs = session.execute(
                bs.bind(country,userName));
        Set emailSetBind = new HashSet();
        emailSetBind.add(email);
        ps = session.prepare("        UPDATE userprofiles SET email = ? WHERE login=?;");
        bs = new BoundStatement(ps);
        rs = session.execute(
                bs.bind(emailSetBind,userName));

    }
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
