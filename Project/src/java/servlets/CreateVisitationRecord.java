/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Controllers.VisitationRecordController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author william
 */
@WebServlet(name = "CreateVisitationRecord", urlPatterns = {"/CreateVisitationRecord"})
public class CreateVisitationRecord extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        
        String patient_id = request.getParameter("patient_id");
        String doctor_id = request.getParameter("doctor_id");
        String visit_date = request.getParameter("visit_date") + " " + request.getParameter("visit_time");
        String length_of_visit = request.getParameter("length_of_visit");
        String proc = request.getParameter("procedure");
        String freeform_comments = request.getParameter("freeform_comments");
        String scheduling_of_treatment = request.getParameter("scheduling_of_treatment_date") + " " + request.getParameter("scheduling_of_treatment_time");
        String surgery_performed = request.getParameter("surgery_performed");
        String diagnosis = request.getParameter("diagnosis");
        String prescription_name = request.getParameter("prescription_name");
        
        try {
            VisitationRecordController.create(con, patient_id, doctor_id, visit_date, length_of_visit, proc, scheduling_of_treatment, freeform_comments, surgery_performed, diagnosis, prescription_name);
            getServletContext().getRequestDispatcher("/QueryServlet?query=" + QueryServlet.RECORDS_BY_PATIENT_AS_DOCTOR + "&patient_id=" + patient_id + "&doctor_id=" + doctor_id).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
