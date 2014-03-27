/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author william
 */
public class VisitationRecord {
    String patient_id;
    String doctor_id;
    Date visit_date;
    Date updated_at;
    Date length_of_visit;
    String procedure;
    Date scheduling_of_treatment;
    String freeform_comments;
    String surgery_performed;
    String diagnosis;
    String prescription_name;
    
    public VisitationRecord() {
        
    }
    
    public VisitationRecord(String patient_id, String doctor_id, Date visit_date, Date updated_at, Date length_of_visit, String procedure, Date scheduling_of_treatment, String freeform_comments, String surgery_performed, String diagnosis, String prescription_name) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.visit_date = visit_date;
        this.updated_at = updated_at;
        this.length_of_visit = length_of_visit;
        this.procedure = procedure;
        this.scheduling_of_treatment = scheduling_of_treatment;
        this.freeform_comments = freeform_comments;
        this.surgery_performed = surgery_performed;
        this.diagnosis = diagnosis;
        this.prescription_name = prescription_name;
    }
    
    public VisitationRecord(ResultSet rs) throws SQLException {
        this(rs.getString("patient_user_id"), rs.getString("doctor_user_id"), rs.getDate("visit_date"), rs.getDate("updated_at"), rs.getDate("length_of_visit"), rs.getString("proc"), rs.getDate("scheduling_of_treatment"), rs.getString("freeform_comments"), rs.getString("surgery_performed"), rs.getString("diagnosis"), rs.getString("prescription_name"));
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
    
    public Date getVisitDate() {
        return this.visit_date;
    }
    
    public void setVisitDate(Date visit_date) {
        this.visit_date = visit_date;
    }
    
    public Date getUpdatedAt() {
        return this.updated_at;
    }
    
    public void setUpdatedAT(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    public Date getLengthOfVisit() {
        return this.length_of_visit;
    }
    
    public void setLengthOfVisit(Date length_of_visit) {
        this.length_of_visit = length_of_visit;
    }
    
    public String getProcedure() {
        return this.procedure;
    }
    
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    
    public Date getSchedulingOfTreatment() {
        return this.scheduling_of_treatment;
    }
    
    public void setSchedulingOfTreatment(Date scheduling_of_treatment) {
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
}
