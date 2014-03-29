/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;
import java.io.*;

/**
 *
 * @author slouli
 */
public class Log {
    private String path;
    Object Event;
    
    public Log(Appointment Appt) {
        this.Event = Appt;
        this.path = "/Users/slouli/Documents/ECE3B/ECE356/git/ECE356-Project/Project/logs/ECE356_logs.txt";
    }
    
    public Log(Patient patient){
        this.Event = patient;
    }
    
    public Log(Doctor doctor, Patient patient) {
        
    }
    
    public void Create() {
        BufferedWriter output = null;
        File file = new File(this.path);
        try{
        //file.createNewFile();
        output = new BufferedWriter(new FileWriter(file, true));
        if (this.Event instanceof Appointment) {
            Appointment appt = (Appointment) this.Event;
            String s = "CREATE Appointment: " + appt.getPatientId() 
                    + ", " + appt.getDoctorId() + ", " + appt.getApptStart() 
                    + ", " + appt.getApptEnd() + "\r\n";
            output.write(s);
            
        } else if(this.Event instanceof Prescription) {
            
        } else if(this.Event instanceof Doctor) {
        
        }
        output.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void Delete() {
    
    }
    
    public void Update() {
    }
    
}
