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
public class Patient_detail {
    private String user_id;
    private String first_name;
    private String last_name;
    private String address;
    private String current_health;
    private String ohip;
    private String email;
    private int phone;
    private int sin;
    
    public Patient_detail (String user_id, String first_name, String last_name, String email, String address, String current_health, String ohip, int phone, int sin){
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address=address;
        this.current_health=current_health;
        this.ohip = ohip;
        this.email = email;
        this.phone = phone;
        this.sin=sin;
        
    }
    public String getUserid() {
        return this.user_id;
    }
    
    public String getFname() {
        return this.first_name;
    }
    public String getEmail() {
        return this.email;
    }
    public String getLname() {
        return this.last_name;
    }
    public String getAddress(){
        return this.address;
    }
    public String getCurrent_health(){
        return this.current_health;
    }
    public String getOHIP(){
        return this.ohip;
    }
    public int getPhone(){
        return this.phone;
    }
    public int getSIN(){
        return this.sin;
    }
}
