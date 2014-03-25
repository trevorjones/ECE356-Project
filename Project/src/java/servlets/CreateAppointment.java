/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import Controllers.AppointmentController;
import Controllers.DoctorPatientController;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Appointment;
import models.Patient;

/**
 *
 * @author william
 */
@WebServlet(name = "CreateAppointment", urlPatterns = {"/CreateAppointment"})
public class CreateAppointment extends HttpServlet {

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

        String patient_id = request.getParameter("patient_id");
        String doctor_id = "tpain";
        String start_time = request.getParameter("start_time");
        String end_time = request.getParameter("end_time");
        String status = "scheduled";
        String procedure = request.getParameter("procedure");
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");

        String url;
        try {
            AppointmentController.create(null, patient_id, doctor_id, start_time, end_time, status, procedure);
            ArrayList<Appointment> ret = AppointmentController.queryDoctorAppt(con, doctor_id);
            request.setAttribute("apptList", ret);
            ArrayList<Patient> ret2 = DoctorPatientController.queryByDoctor(con, doctor_id);
            request.setAttribute("patientList", ret2);
            url = "/appointment.jsp";
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
