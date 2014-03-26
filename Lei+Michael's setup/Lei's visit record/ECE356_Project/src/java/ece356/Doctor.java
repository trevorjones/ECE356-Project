/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356;

/**
 *
 * @author slouli
 */
public class Doctor {
    private String user_id;
    private String first_name;
    private String last_name;
    private String specialization;
    
    public Doctor(String user_id, String first_name, String last_name, String specialization) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialization = specialization;
    }
    
    public String getUserid() {
        return this.user_id;
    }
    
    public String getFname() {
        return this.first_name;
    }
    
    public String getLname() {
        return this.last_name;
    }
    
    public String getSpec() {
        return this.specialization;
    }
    
}
