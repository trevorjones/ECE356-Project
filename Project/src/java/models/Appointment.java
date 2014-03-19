/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author william
 */
public class Appointment {
    String patient_id;
    String doctor_id;
    Date scheduled_date;
    String status; /* Either scheduled, cancelled, done */
    String procedure;
    Date estimated_length;
    
    public Appointment(String patient_id, String doctor_id, Date scheduled_date, String status, String procedure, Date estimated_length) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.scheduled_date = scheduled_date;
        this.status = status;
        this.procedure = procedure;
        this.estimated_length = estimated_length;
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
    
    public Date getScheduledDate() {
        return this.scheduled_date;
    }
    
    public void setScheduledDate(Date scheduled_date) {
        this.scheduled_date = scheduled_date;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getProcedure() {
        return this.procedure;
    }
    
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    
    public Date getEstimatedLength() {
        return this.estimated_length;
    }
    
    public void setEstimatedLength(Date estimated_length) {
        this.estimated_length = estimated_length;
    }
}
