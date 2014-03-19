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
public class DoctorStaff {
    String doctor_id;
    String staff_id;
    boolean permission;
    
    public DoctorStaff(String doctor_id, String staff_id, boolean permission) {
        this.doctor_id = doctor_id;
        this.staff_id = staff_id;
        this.permission = permission;
    }
    
    public String getDoctorId() {
        return this.doctor_id;
    }
    
    public void setDoctorId(String id) {
        this.doctor_id = id;
    }
    
    public String getStaffId() {
        return this.staff_id;
    }
    
    public void setStaffId(String id) {
        this.staff_id = id;
    }
    
    public boolean getPermission() {
        return this.permission;
    }
    
    public void setUserId(boolean permission) {
        this.permission = permission;
    }
}
