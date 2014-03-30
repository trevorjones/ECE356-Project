/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.*;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author slouli
 */
public class Log {

    private String path;
    Object Event;
    Object Event2;

    public Log(Object Event) {
        this.Event = Event;
        this.path = "/Users/slouli/Documents/ECE3B/ECE356/git/ECE356-Project/Project/logs/ECE356_logs.txt";
    }

    public Log(Object Event, Object Event2) {
        this.Event = Event;
        this.Event2 = Event2;
        this.path = "/Users/slouli/Documents/ECE3B/ECE356/git/ECE356-Project/Project/logs/ECE356_logs.txt";
    }

    public void Create() {
        BufferedWriter output = null;
        File file = new File(this.path);
        Date date = new java.util.Date();
        java.text.SimpleDateFormat t_stamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = t_stamp.format(date.getTime());

        try {
            //file.createNewFile();
            output = new BufferedWriter(new FileWriter(file, true));

            String s = timestamp + ": \n\tCREATE ";

            if (this.Event instanceof Appointment && this.Event2 == null) {
                Appointment appt = (Appointment) this.Event;
                s += "Appointment, \n\t"
                        + "Patient: " + appt.getPatientId() + ", \n\t"
                        + "Doctor: " + appt.getDoctorId() + ", \n\t"
                        + "Appointment Start: " + appt.getApptStart() + ", \n\t"
                        + "Appointment End: " + appt.getApptEnd() + ", \n\t"
                        + "Procedure: " + appt.getProc() + "\r\n\n";

            } else if (this.Event instanceof Prescription && this.Event2 == null) {
                Prescription presc = (Prescription) this.Event;
                s += "Prescription, " + presc.getAlias() + ", "
                        + presc.getName() + ", " + presc.getDescription();

            } else if (this.Event instanceof User && this.Event2 == null) {
                User user = (User) this.Event;
                s += "User, \n\t"
                        + "User ID:" + user.getId() + ", \n\t"
                        + "First Name: " + user.getFirstName() + ", \n\t"
                        + "Last Name: " + user.getLastName() + ", \n\t"
                        + "User Type: " + user.getType() + ", \n\t"
                        + "E-mail: " + user.getEmail() + "\r\n\n";
            } else if (this.Event instanceof Doctor && this.Event2 instanceof Staff) {
                Doctor doctor = (Doctor) this.Event;
                Staff staff = (Staff) this.Event2;
                
                s += "Doctor-Staff, \n\t"
                        + "Doctor ID: " + doctor.getId() + ", \n\t"
                        + "Staff ID: " + staff.getId() +  ", \n\t"
                        + "Permission: " + staff.getPermission() + "\r\n\n";
            } else if (this.Event instanceof Doctor && this.Event2 instanceof Patient) {
                Doctor doctor = (Doctor) this.Event;
                Patient patient = (Patient) this.Event2;
                
                s += "Doctor-Patient Permission, \n\t"
                        + "Doctor Id: " + doctor.getId() + ", \n\t"
                        + "Patient Id: " + patient.getId() + ", \n\t"
                        + "Default Doctor: " + patient.getPermission() + "\r\n\n";    
            }
            
            output.write(s);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Delete() {
        BufferedWriter output = null;
        File file = new File(this.path);
        Date date = new java.util.Date();
        java.text.SimpleDateFormat t_stamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = t_stamp.format(date.getTime());

        try {
            //file.createNewFile();
            output = new BufferedWriter(new FileWriter(file, true));

            String s = timestamp + ": \n\tDELETE ";

            if (this.Event instanceof Appointment && this.Event2 == null) {
                Appointment appt = (Appointment) this.Event;
                
                s += "Appointment, \n\t"
                        + "Patient: " + appt.getPatientId() + ", \n\t"
                        + "Doctor: " + appt.getDoctorId() + ", \n\t"
                        + "Appointment Date: " + appt.getApptDate() + ", \n\t"
                        + "Appointment Start: " + appt.getApptStart() + ", \n\t"
                        + "Appointment End: " + appt.getApptEnd() + ", \n\t"
                        + "Procedure: " + appt.getProc() + "\r\n\n";
            }  else if (this.Event instanceof Doctor && this.Event2 instanceof Patient) {
                Doctor doctor = (Doctor) this.Event;
                Patient patient = (Patient) this.Event2;
                
                s += "Doctor-Patient Permission, \n\t"
                        + "Doctor Id: " + doctor.getId() + ", \n\t"
                        + "Patient Id: " + patient.getId() + "\r\n\n";    
            }

            output.write(s);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Update() {
        BufferedWriter output = null;
        File file = new File(this.path);
        Date date = new java.util.Date();
        java.text.SimpleDateFormat t_stamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = t_stamp.format(date.getTime());

        try {
            //file.createNewFile();
            output = new BufferedWriter(new FileWriter(file, true));

            String s = timestamp + ": \n\tUPDATE ";

            if (this.Event instanceof Patient && this.Event2 == null) {
                Patient p = (Patient) this.Event;
                if (p.ohip == null) {
                    s += "Patient, \n\t"
                            + "Patient ID:" + p.getId() + ", \n\t"
                            + "First Name: " + p.getFirstName() + ", \n\t"
                            + "Last Name: " + p.getLastName() + ", \n\t"
                            + "E-mail: " + p.getEmail() + ", \n\t"
                            + "Address: " + p.getAddress() + ", \n\t"
                            + "Phone: " + p.getPhone() + "\r\n\n";
                } else {
                    s += "Patient, \n\t"
                            + "Patient ID:" + p.getId() + ", \n\t"
                            + "First Name: " + p.getFirstName() + ", \n\t"
                            + "Last Name: " + p.getLastName() + ", \n\t"
                            + "E-mail: " + p.getEmail() + ", \n\t"
                            + "Address: " + p.getAddress() + ", \n\t"
                            + "Current Health: " + p.getCurrentHealth() + ", \n\t"
                            + "OHIP: " + p.getOHIP() + ", \n\t"
                            + "Phone: " + p.getPhone() + ", \n\t"
                            + "SIN: " + p.getSIN() + "\r\n\n";
                }
            } else if (this.Event instanceof Patient && this.Event2 instanceof Doctor) {
                Patient p = (Patient) this.Event;
                Doctor d = (Doctor) this.Event2;
                
                s += "Default Doctor, \n\t"
                        + "Patient ID: " + p.getId() + ", \n\t" 
                        + "Default Doctor: " + d.getId() + "\r\n\n";
            } else if (this.Event instanceof Doctor && this.Event2 instanceof Staff) {
                Doctor doctor = (Doctor) this.Event;
                Staff staff = (Staff) this.Event2;

                s += "Doctor-Staff, \n\t"
                        + "Doctor ID: " + doctor.getId() + ", \n\t"
                        + "Staff ID: " + staff.getId() + ", \n\t"
                        + "Permission: " + staff.getPermission() + "\r\n\n";                
            } else if (this.Event instanceof Doctor && this.Event2 == null) {
                Doctor doctor = (Doctor) this.Event;
                
                s += "Doctor,\n\t" 
                        + "Doctor ID: " + doctor.getId() + ", \n\t"
                        + "First Name: " + doctor.getFirstName() + ", \n\t"
                        + "Last Name: " + doctor.getLastName() + ", \n\t"
                        + "E-mail: " + doctor.getEmail() + ", \n\t" 
                        + "Specialization: " + doctor.getSpecialization() + "\r\n\n";     
            }

            output.write(s);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
