/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356;
import java.sql.*;

/**
 *
 * @author Lei Wu
 */
public class Record_detail {
    private String puserid;
    private String duserid;
    private Date visit_date;
    private Timestamp updated_at;
    private Time length_of_visit;
    private String proc;
    private Date scheduling_of_treatment;
    private String freeform_comments;
    private String surgery_performed;
    private String diagnosis;
    private String prescription;
    
    public Record_detail (String puserid, String duserid, Date visit_date, Timestamp updated_at, Time length_of_visit, String proc, 
            Date scheduling_of_treatment, String freeform_comments, String surgery_performed, String diagnosis, String prescription){
        this.puserid = puserid;
        this.duserid = duserid;
        this.visit_date = visit_date;
        this.updated_at=updated_at;
        this.length_of_visit=length_of_visit;
        this.proc = proc;
        this.scheduling_of_treatment = scheduling_of_treatment;
        this.freeform_comments=freeform_comments;
        this.surgery_performed=surgery_performed;
        this.diagnosis=diagnosis;
        this.prescription=prescription;
               
    }
    public String getPuserid() {
        return this.puserid;
    }
    
    public String getDuserid() {
        return this.duserid;
    }
    
    public Date getVisit_date() {
        return this.visit_date;
    }
    public Timestamp getUpdated_at(){
        return this.updated_at;
    }
    public Time getLength_of_visit(){
        return this.length_of_visit;
    }
    public String getProc(){
        return this.proc;
    }
    public Date getScheduling_of_treatment(){
        return this.scheduling_of_treatment;
    }
    public String getFreeform_comments(){
        return this.freeform_comments;
    }
    public String getSurgery_performed(){
        return this.surgery_performed;
    }
    public String getDiagnosis(){
        return this.diagnosis;
    }
    public String getPrescription(){
        return this.prescription;
    }
    
}
