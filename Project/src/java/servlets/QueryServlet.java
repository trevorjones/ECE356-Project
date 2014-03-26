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
import Controllers.PrescriptionController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public static final String STAFF_NOT_ASSIGNED = "staff_not_assigned";
    public static final String STAFF_QUERY = "staff_query";

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
            } else if(query.equals(STAFF_NOT_ASSIGNED)) {
                ArrayList ret = DoctorStaffController.queryStaffNotWorkingForDoctor(con, request.getParameter("doctor_id"));
                request.setAttribute("staffList", ret);
                url = "/addStaff.jsp";
            } else if(query.equals(STAFF_QUERY)) {
                ArrayList ret = DoctorStaffController.queryByDoctor(con, request.getParameter("doctor_id"));
                request.setAttribute("staffList", ret);
                url = "/staff.jsp";
            } else if(query.equals(DOCTORS_QUERY_BY_STAFF)) {
                ArrayList ret = DoctorStaffController.queryByStaff(con, request.getParameter("staff_id"));
                request.setAttribute("doctorList", ret);
                url = "/doctor.jsp";
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
