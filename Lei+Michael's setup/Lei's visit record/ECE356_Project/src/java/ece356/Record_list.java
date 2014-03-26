/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356;
import java.sql.Date;

/**
 *
 * @author Lei Wu
 */
public class Record_list {
    private String puserid;
    private String duserid;
    private Date visit_date;
    
    public Record_list (String puserid, String duserid, Date visit_date){
        this.puserid = puserid;
        this.duserid = duserid;
        this.visit_date = visit_date;
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
}
