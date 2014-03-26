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
public class Doctor extends User {
    String specialization;
    
    public Doctor() {
        
    }
    
    public Doctor(String id, String first_name, String last_name, String email, String specialization) {
        super(id, first_name, last_name, "doctor", email);
        this.specialization = specialization;
    }
    
    public String getSpecialization() {
        return this.specialization;
    }
    
    public void setSpecialization(String spec) {
        this.specialization = spec;
    }
}
