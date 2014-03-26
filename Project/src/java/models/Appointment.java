/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;
import java.util.Date;
        
/**
 *
 * @author slouli
 */
public class Appointment {
    private String patient_id;
    private String doctor_id;
    private String appt_date;
    private String appt_start;
    private String appt_end;
    private String status;
    private String proc;
    
    
    public Appointment(String patient_id, String doctor_id, Date start, Date end,
            String status, String proc) {
        
        java.text.SimpleDateFormat date = new java.text.SimpleDateFormat("yyy-MM-dd");
        java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("HH:mm:ss");
        
        String appt_date = date.format(start);
        String appt_start = time.format(start);
        String appt_end = time.format(end);
        
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appt_date = appt_date;
        this.appt_start = appt_start;
        this.appt_end = appt_end;
        this.status = status;
        this.proc = proc;
    }
    
    public Appointment(String patient_id, String doctor_id, String start, String end,
            String status, String proc) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appt_start = start;
        this.appt_end = end;
        this.status = status;
        this.proc = proc;
    }    
    
    public String getPatientId() {
        return this.patient_id;
    }
    
    public void setPatientId(String patient_id) {
        this.patient_id = patient_id;
    }
    
    public String getDoctorId() {
        return this.doctor_id;
    }
    
    public void setDoctorId(String doctor_id) {
        this.doctor_id = doctor_id;
    }
    
    public String getApptDate() {
        return this.appt_date;
    }
    
    public String getApptStart() {
        return this.appt_start;
    }
    
    public String getApptEnd() {
        return this.appt_end;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status= status;
    }
    
    public String getProc() {
        return this.proc;
    }
    
    public void setProc(String proc) {
        this.proc = proc;
    }
}
