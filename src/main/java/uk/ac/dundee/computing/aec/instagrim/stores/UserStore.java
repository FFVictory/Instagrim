package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.Set;

/**
 * Created by Andrew on 28/10/2014.
 */
public class UserStore {
    String user,country,first_name,last_name;
    Set set;

    public void setUser(String login,String country,String first_name,String last_name){
        this.user = login;
        this.country = country;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public void setSet(Set set1){
        this.set = set1;
    }
    public Set getSet(){
        return this.set;
    }

    public String getUser(){
        return this.user;
    }
    public String getFirstName(){
        return this.first_name;
    }
    public String getLastName(){
        return this.last_name;
    }
    public String getCountry(){
        return this.country;
    }

    //NEEDS EMAIL AND ADRESSES

}
