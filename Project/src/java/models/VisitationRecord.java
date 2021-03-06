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
public class VisitationRecord {
    String patient_id;
    String doctor_id;
    String visit_date;
    String length_of_visit;
    String procedure;
    String scheduling_of_treatment;
    String freeform_comments;
    String surgery_performed;
    String diagnosis;
    String prescription_name;
    String updated_at;
    
    public VisitationRecord() {
        
    }
    
    public VisitationRecord(String patient_id, String doctor_id, String visit_date, String length_of_visit, String procedure, String scheduling_of_treatment, String freeform_comments, String surgery_performed, String diagnosis, String prescription_name, String updated_at) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.visit_date = visit_date;
        this.length_of_visit = length_of_visit;
        this.procedure = procedure;
        this.scheduling_of_treatment = scheduling_of_treatment;
        this.freeform_comments = freeform_comments;
        this.surgery_performed = surgery_performed;
        this.diagnosis = diagnosis;
        this.prescription_name = prescription_name;
        this.updated_at = updated_at;
    }
    
    public VisitationRecord(ResultSet rs) throws SQLException {
        this(rs.getString("patient_user_id"), rs.getString("doctor_user_id"), rs.getTimestamp("visit_date").toString(), rs.getTime("length_of_visit").toString(), rs.getString("proc"), rs.getTimestamp("scheduling_of_treatment").toString(), rs.getString("freeform_comments"), rs.getString("surgery_performed"), rs.getString("diagnosis"), rs.getString("prescription_name"), rs.getTimestamp("updated_at").toString());
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
    
    public String getVisitDate() {
        return this.visit_date;
    }
    
    public String getVisitDateFormated() {
        return this.visit_date.split(" ")[0];
    }
    
    public String getVisitTimeFormated() {
        String[] time = this.visit_date.split(" ")[1].split(":");
        return time[0]+":"+time[1];
    }
    
    public String getSchedulingDateFormated() {
        return this.scheduling_of_treatment.split(" ")[0];
    }
    
    public String getSchedulingTimeFormated() {
        String[] time = this.scheduling_of_treatment.split(" ")[1].split(":");
        return time[0]+":"+time[1];
    }
    
    public String getLengthTimeFormated() {
        String[] time = this.length_of_visit.split(":");
        return time[0]+":"+time[1];
    }
    
    public void setVisitDate(String visit_date) {
        this.visit_date = visit_date;
    }
    
    public String getLengthOfVisit() {
        return this.length_of_visit;
    }
    
    public void setLengthOfVisit(String length_of_visit) {
        this.length_of_visit = length_of_visit;
    }
    
    public String getProcedure() {
        return this.procedure;
    }
    
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    
    public String getSchedulingOfTreatment() {
        return this.scheduling_of_treatment;
    }
    
    public void setSchedulingOfTreatment(String scheduling_of_treatment) {
        this.scheduling_of_treatment = scheduling_of_treatment;
    }
    
    public String getFreeformComments() {
        return this.freeform_comments;
    }
    
    public void setFreeformComments(String freeform_comments) {
        this.freeform_comments = freeform_comments;
    }
    
    public String getSurgeryPerformed() {
        return this.surgery_performed;
    }
    
    public void setSurgeryPerformed(String surgery_performed) {
        this.surgery_performed = surgery_performed;
    }
    
    public String getDiagnosis() {
        return this.diagnosis;
    }
    
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public String getPrescriptionName() {
        return this.prescription_name;
    }
    
    public void setPrescriptionName(String prescription_name) {
        this.prescription_name = prescription_name;
    }
    
    public String getUpdatedAt() {
        return this.updated_at;
    }
    
    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
    }
}
