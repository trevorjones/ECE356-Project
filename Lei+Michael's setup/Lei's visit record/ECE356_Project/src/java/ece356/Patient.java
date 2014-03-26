/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356;

/**
 *
 * @author Lei Wu
 */
public class Patient {
    private String user_id;
    private String first_name;
    private String last_name;
    
    public Patient (String user_id, String first_name, String last_name){
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
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
}
