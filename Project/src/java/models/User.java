/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author william
 */
public class User {
    String user_id;
    String first_name;
    String last_name;
    String type; /* Either patient, doctor, staff, or financial officer */
    String email;
    
    public User() {
        
    }
    
    public User(String id, String first_name, String last_name, String type, String email) {
        this.user_id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.type = type;
        this.email = email;
    }
    
    public User(ResultSet rs) throws SQLException {
        this(rs.getString("user_id"), rs.getString("first_name"),rs.getString("last_name"), rs.getString("type"), rs.getString("email"));
    }
    
    public String getId() {
        return user_id;
    }
    
    public void setId(String id) {
        this.user_id = id;
    }
    
    public String getFirstName() {
        return this.first_name;
    }
    
    public void setFirstName(String fn) {
        this.first_name = fn;
    }
    
    public String getLastName() {
        return this.last_name;
    }
    
    public void setLastName(String ln) {
        this.last_name = ln;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String tp) {
        this.type = tp;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String em) {
        this.email = em;
    }
}
