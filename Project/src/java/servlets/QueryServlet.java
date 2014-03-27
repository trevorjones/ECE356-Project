/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Controllers.AppointmentController;
import Controllers.DoctorController;
import Controllers.DoctorPatientController;
import Controllers.DoctorStaffController;
import Controllers.PatientController;
import Controllers.PrescriptionController;
import Controllers.VisitationRecordController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Doctor;
import models.Patient;

/**
 *
 * @author william
 */
@WebServlet(name = "QueryServlet", urlPatterns = {"/QueryServlet"})
public class QueryServlet extends HttpServlet {
    
    // Query Types
    public static final String PRESCRIPTIONS_ALL = "prescriptions_all";
    public static final String PRESCRIPTIONS_QUERY = "prescriptions_query";
    public static final String DOCTORS_ALL = "doctors_all";
    public static final String DOCTORS_QUERY = "doctors_query";
    public static final String DOCTORS_QUERY_BY_STAFF = "doctors_query_by_staff";
    public static final String APPOINTMENTS_FOR_DOCTOR = "appointments for doctor";
    public static final String STAFF_QUERY = "staff_query";
    public static final String PATIENTS_BY_STAFF = "patients_by_staff";
    public static final String PATIENTS_SEARCH_BY_STAFF = "patients_search_by_staff";
    public static final String PATIENTS_SEARCH_BY_DOCTOR = "patients_search_by_doctor";
    public static final String PATIENTS_BY_DOCTOR = "patients_by_doctor";
    public static final String PATIENT_DETAILS = "patient_details";
    public static final String RECORDS_BY_PATIENT_AS_STAFF = "records_by_patient_as_staff";
    public static final String RECORDS_BY_PATIENT_AS_DOCTOR = "records_by_patient_as_doctor";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String query = request.getParameter("query");
        String url;
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        
        try {
            if (query.equals(PRESCRIPTIONS_ALL)) {
                ArrayList ret = PrescriptionController.getAll(con);
                request.setAttribute("prescriptionList", ret);
                url = "/prescription.jsp";
            } else if (query.equals(PRESCRIPTIONS_QUERY)) {
                ArrayList ret = PrescriptionController.query(con, request.getParameter("prescription_query"));
                request.setAttribute("prescriptionList", ret);
                url = "/prescription.jsp";
            } else if(query.equals(DOCTORS_ALL)) {
                ArrayList ret = DoctorController.getAll(con);
                request.setAttribute("doctorList", ret);
                url = "/doctor.jsp";
            } else if(query.equals(DOCTORS_QUERY)) {
                ArrayList ret = DoctorController.query(con, request.getParameter("doctor_query"));
                request.setAttribute("doctorList", ret);
                url = "/doctor.jsp";
            } else if (query.equals(APPOINTMENTS_FOR_DOCTOR)) {
                String doctor_id = request.getParameter("doctor_id");
                
                ArrayList ret = AppointmentController.queryDoctorAppt(con, doctor_id);
                request.setAttribute("apptList", ret);
                
                ret = DoctorPatientController.queryByDoctor(con, doctor_id);
                request.setAttribute("patientList", ret);
                
                url = "/appointment.jsp";
            } else if(query.equals(STAFF_QUERY)) {
                ArrayList ret = DoctorStaffController.queryByDoctor(con, request.getParameter("doctor_id"));
                request.setAttribute("staffRemoveList", ret);
                ret = DoctorStaffController.queryStaffNotWorkingForDoctor(con, request.getParameter("doctor_id"));
                request.setAttribute("staffAddList", ret);
                url = "/staff.jsp";
            } else if(query.equals(DOCTORS_QUERY_BY_STAFF)) {
                ArrayList ret = DoctorStaffController.queryByStaff(con, request.getParameter("staff_id"));
                request.setAttribute("doctorList", ret);
                url = "/doctor.jsp";
            } else if(query.equals(PATIENTS_BY_STAFF)) {
                ArrayList ret = PatientController.getAllWithPermission(con, request.getParameter("staff_id"));
                request.setAttribute("patientList", ret);
                url = "/patient.jsp";
            } else if(query.equals(PATIENTS_SEARCH_BY_STAFF)) {
                ArrayList ret = PatientController.queryPatientByStaff(con, request.getParameter("patient_query"), request.getParameter("staff_id"));
                request.setAttribute("patientList", ret);
                url = "/patient.jsp";
            } else if(query.equals(PATIENTS_SEARCH_BY_DOCTOR)) {
                ArrayList ret = PatientController.queryPatientByDoctor(con, request.getParameter("patient_query"), request.getParameter("doctor_id"));
                request.setAttribute("patientList", ret);
                url = "/patient.jsp";
            } else if(query.equals(PATIENTS_BY_DOCTOR)) {
                ArrayList ret = DoctorPatientController.queryByDoctor(con, request.getParameter("doctor_id"));
                request.setAttribute("patientList", ret);
                url = "/patient.jsp";
            } else if(query.equals(PATIENT_DETAILS)) {
                String patient_id = request.getParameter("patient_id");
                Patient p = PatientController.getPatient(con, patient_id);
                request.setAttribute("patient", p);
                ArrayList<Doctor> ds = DoctorController.getAll(con);
                request.setAttribute("doctors", ds);
                String curDocID = DoctorPatientController.getDoctorIDOfPatient(con, patient_id);
                request.setAttribute("curDocID", curDocID);
                url = "/patient_detail.jsp";
            } else if(query.equals(RECORDS_BY_PATIENT_AS_STAFF)) {
                ArrayList ret = VisitationRecordController.queryByPatientAsStaff(con, request.getParameter("patient_id"), request.getParameter("staff_id"));
                request.setAttribute("visitation_record_list", ret);
                url = "/records.jsp";
            } else if(query.equals(RECORDS_BY_PATIENT_AS_DOCTOR)) {
                ArrayList ret = VisitationRecordController.queryByPatientAsDoctor(con, request.getParameter("patient_id"), request.getParameter("doctor_id"));
                request.setAttribute("visitation_record_list", ret);
                url = "/records.jsp";
            } else {
                throw new RuntimeException("Invalid query: " + query);
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/error.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
        
    }   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
