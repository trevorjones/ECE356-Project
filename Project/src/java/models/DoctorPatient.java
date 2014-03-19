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
public class DoctorPatient {
    String patient_id;
    String doctor_id;
    boolean permissions[];
    
    public DoctorPatient(String patient_id, String doctor_id, boolean permissions[]) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.permissions = permissions;
    }
    
    public String getPatientId() {
        return this.patient_id;
    }
    
    public void setPatientId(String id) {
        this.patient_id = id;
    }
    
    public String getDoctorId() {
        return this.doctor_id;
    }
    
    public void setDoctorId(String id) {
        this.doctor_id = id;
    }
    
    public boolean[] getPermissions() {
        return this.permissions;
    }
    
    public void setPermissions(boolean permissions[]) {
        this.permissions = permissions;
    }
}
