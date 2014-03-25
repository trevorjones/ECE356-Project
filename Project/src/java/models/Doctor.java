/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author william
 */
public class Doctor {
    String user_id;
    String first_name;
    String last_name;
    String specialization;
    
    public Doctor() {
        
    }
    
    public Doctor(String id, String first_name, String last_name, String specialization) {
        this.user_id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialization = specialization;
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
    
    public String getSpecialization() {
        return this.specialization;
    }
    
    public void setSpecialization(String spec) {
        this.specialization = spec;
    }
}
