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
public class Patient {
    String user_id;
    String address;
    String current_health;
    String ohip;
    int phone;
    int sin;
    
    public Patient() {
        
    }
    
    public Patient(String id, String address, String current_health, String ohip, int phone, int sin) {
        this.user_id = id;
        this.address = address;
        this.current_health = current_health;
        this.ohip = ohip;
        this.phone = phone;
        this.sin = sin;
    }
    
    public String getId() {
        return user_id;
    }
    
    public void setId(String id) {
        this.user_id = id;
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
    
    public int getPhone() {
        return phone;
    }
    
    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    public int getSIN() {
        return this.sin;
    }
    
    public void setSIN(int sin) {
        this.sin = sin;
    }
}
