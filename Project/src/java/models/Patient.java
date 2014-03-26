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
public class Patient extends User {
    String address;
    String current_health;
    String ohip;
    String phone;
    int sin;
    
    public Patient() {
        
    }
    
    public Patient(String id, String first_name, String last_name, String email, String address, String current_health, String ohip, String phone, int sin) {
        super(id, first_name, last_name, "patient", email);
        this.address = address;
        this.current_health = current_health;
        this.ohip = ohip;
        this.phone = phone;
        this.sin = sin;
    }
    
    public Patient(ResultSet rs) throws SQLException {
        super(rs.getString("User.user_id"), rs.getString("User.first_name"), rs.getString("User.last_name"), "patient", rs.getString("User.email"));
        this.address = rs.getString("Patient.address");
        this.current_health = rs.getString("Patient.current_health");
        this.ohip = rs.getString("Patient.ohip");
        this.phone = rs.getString("Patient.phone");
        this.sin = rs.getInt("Patient.sin");
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCurrentHealth() {
        return this.current_health;
    }
    
    public void setCurrentHealth(String currentHealth) {
        this.current_health = currentHealth;
    }
    
    public String getOHIP() {
        return this.ohip;
    }
    
    public void setOHIP(String ohip) {
        this.ohip = ohip;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public int getSIN() {
        return this.sin;
    }
    
    public void setSIN(int sin) {
        this.sin = sin;
    }
}
