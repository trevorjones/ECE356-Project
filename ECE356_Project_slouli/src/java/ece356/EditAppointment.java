/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author slouli
 */
public class EditAppointment extends HttpServlet {

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
        
        String url = "/appointment.jsp";
        if (request.getParameter("submit").equals("Insert_Appt")) {
            String patient_id = request.getParameter("patient_id");
            String doctor_id = request.getParameter("doctor_id");
            String date = request.getParameter("date");
            String start_time = request.getParameter("start_time");
            String end_time = request.getParameter("end_time");
            String status = "scheduled";
            String procedure = request.getParameter("procedure");
            
            String start_datetime = date + " " + start_time;
            String end_datetime = date + " " + end_time;
            
            Appointment appt = new Appointment(patient_id, doctor_id, start_datetime, end_datetime, status, procedure);

            try {
                Integer conflicts = ProjectDBAO.sanityCheckAppt(start_datetime, end_datetime);
                if(conflicts == 0) {
                    ProjectDBAO.addAppointment(appt);
                } else {
                    request.setAttribute("conflicts", conflicts);
                }
                apptHelper(request,response);
            } catch (Exception e) {
                request.setAttribute("exception", e);
                url = "/error.jsp";
            }
        } else if (request.getParameter("submit").equals("Delete_Appt")) {
            try {
                String[] delAppt;
                delAppt = request.getParameterValues("delAppt");
                for (String Appt : delAppt) {
                    ProjectDBAO.delAppointment(Appt);
                }
                apptHelper(request, response);
            } catch (Exception e) {
                request.setAttribute("exception", e);
                url = "/error.jsp";
            }
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    protected void apptHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
                ArrayList<Appointment> ret = ProjectDBAO.queryDoctorAppt(request.getParameter("doctor_id"));
                request.setAttribute("apptList", ret);
                ArrayList<Patient> ret2 = ProjectDBAO.queryDoctorPatient(request.getParameter("doctor_id"));
                request.setAttribute("patientList", ret2);
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
