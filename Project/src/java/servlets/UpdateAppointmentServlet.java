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
import java.util.Date;
import java.util.Calendar;
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
@WebServlet(name = "UpdateAppointmentServlet", urlPatterns = {"/UpdateAppointmentServlet"})
public class UpdateAppointmentServlet extends HttpServlet {

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
        String doctor_id = request.getParameter("doctor_id");

        String url = "/appointment.jsp";
        if (request.getParameter("submit").equals("Add Appointment")) {
            String patient_id = request.getParameter("patient_id");
            String date = request.getParameter("date");
            String start_time = request.getParameter("start_time");
            String end_time = request.getParameter("end_time");
            String status = "scheduled";
            String procedure = request.getParameter("procedure");

            String start_datetime = date + " " + start_time;
            String end_datetime = date + " " + end_time;

            Appointment appt = new Appointment(patient_id, doctor_id, start_datetime, end_datetime, status, procedure);

            try {
                Integer conflicts = AppointmentController.sanityCheck(con, doctor_id, start_datetime, end_datetime);
                if (start_datetime.length() == 16 || end_datetime.length() == 16) {
                    if (conflicts == 0 && start_datetime.compareTo(end_datetime) < 0) {
                        AppointmentController.create(con, appt);     
                    } else {
                        request.setAttribute("conflicts", conflicts);
                    }
                } else {
                    request.setAttribute("error_str", 1);
                }
                apptHelper(con, request, response);
            } catch (Exception e) {
                request.setAttribute("exception", e);
                url = "/error.jsp";
            }
        } else if (request.getParameter("submit").equals("Remove")) {
            try {
                String[] delAppt;
                delAppt = request.getParameterValues("delAppt");
                for (String appt : delAppt) {
                    String[] res = appt.split("&");
                    AppointmentController.delete(con, doctor_id, res[1], res[0]);
                }
                apptHelper(con, request, response);
            } catch (Exception e) {
                request.setAttribute("exception", e);
                url = "/error.jsp";
            }
        } else if (request.getParameter("submit").equals("prev_day") || request.getParameter("submit").equals("next_day")) {
            try {
                apptHelper(con, request, response);
            } catch (Exception e) {
                request.setAttribute("exception", e);
                url = "/error.jsp";
            }

        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void apptHelper(Connection con, HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        Date date = new java.util.Date();
        java.text.SimpleDateFormat t_stamp = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String cur_date = t_stamp.format(date.getTime());
        ArrayList<Appointment> ret;

        String dt = request.getParameter("appt_date");  // Start date;
        if (request.getParameter("submit").equals("prev_day")) {
            try {
                Calendar c = Calendar.getInstance();
                c.setTime(t_stamp.parse(dt));
                c.add(Calendar.DATE, -1);  // number of days to add
                dt = t_stamp.format(c.getTime());
            } catch (Exception e) {
                request.setAttribute("exception", e);
            }
        } else if (request.getParameter("submit").equals("next_day")) {
            try {
                Calendar c = Calendar.getInstance();
                c.setTime(t_stamp.parse(dt));
                c.add(Calendar.DATE, 1);  // number of days to add
                dt = t_stamp.format(c.getTime());
            } catch (Exception e) {
                request.setAttribute("exception", e);
            }
        }
        
        if (dt == null) {
            ret = AppointmentController.queryDoctorAppt(con, request.getParameter("doctor_id"), cur_date);
        } else {
            ret = AppointmentController.queryDoctorAppt(con, request.getParameter("doctor_id"), dt);
            request.setAttribute("search_date", dt);
        }
        request.setAttribute("apptList", ret);
        ArrayList<Patient> ret2 = DoctorPatientController.queryByDoctor(con, request.getParameter("doctor_id"));
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
