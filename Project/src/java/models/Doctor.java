/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author william
 */
public class Doctor extends User {
    String specialization;
    int numberOfPatientsSeen;
    
    public Doctor() {
        
    }
    
    public Doctor(String id, String first_name, String last_name, String email, String specialization) {
        super(id, first_name, last_name, "doctor", email);
        this.specialization = specialization;
    }
    
    public Doctor(ResultSet rs) throws SQLException {
        super(rs.getString("User.user_id"), rs.getString("User.first_name"), rs.getString("User.last_name"), "doctor", rs.getString("User.email"));
        this.specialization = rs.getString("Doctor.specialization");
    }
    
    public Doctor(ResultSet rs, boolean setNumberOfPatientsSeen) throws SQLException {
        super(rs.getString("user_id"), rs.getString("first_name"), rs.getString("last_name"), "doctor", rs.getString("email"));
        this.specialization = rs.getString("specialization");
        if (setNumberOfPatientsSeen) {
            this.numberOfPatientsSeen = rs.getInt("number_of_patients_seen");
        }
    }
    
    public String getSpecialization() {
        return this.specialization;
    }
    
    public void setSpecialization(String spec) {
        this.specialization = spec;
    }
    
    public int getNumberOfPatientsSeen() {
        return this.numberOfPatientsSeen;
    }
    
    public void setNumberOfPatientsSeen(int numberOfPatientsSeen) {
        this.numberOfPatientsSeen = numberOfPatientsSeen;
    }
}
