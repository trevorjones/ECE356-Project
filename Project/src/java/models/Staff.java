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
public class Staff extends User {
    private boolean permission;
    
    public Staff() {
        
    }
    
    public Staff(String id, String first_name, String last_name, String email, boolean permission) {
        super(id, first_name, last_name, "staff", email);
        this.permission = permission;
    }
    
    public boolean getPermission() {
        return this.permission;
    }
    
    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
