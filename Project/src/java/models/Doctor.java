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
    String specialization;
    
    public Doctor(String id, String specialization) {
        this.user_id = id;
        this.specialization = specialization;
    }
    
    public String getId() {
        return user_id;
    }
    
    public void setId(String id) {
        this.user_id = id;
    }
    
    public String getSpecialization() {
        return this.specialization;
    }
    
    public void setSpecialization(String spec) {
        this.specialization = spec;
    }
}
