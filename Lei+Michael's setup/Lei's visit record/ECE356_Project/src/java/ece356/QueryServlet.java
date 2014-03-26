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
public class QueryServlet extends HttpServlet {

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
        String strQueryNum = request.getParameter("qnum");
        int intQueryNum = Integer.parseInt(strQueryNum);
        
        String url;
        
        try {
            if (intQueryNum == 1) {
                ArrayList ret = ProjectDBAO.getPrescriptions();
                request.setAttribute("prescriptionList", ret);
                url = "/prescription.jsp";
            } else if(intQueryNum == 2) {
                ArrayList ret = ProjectDBAO.getDoctors();
                request.setAttribute("doctorList", ret);
                url = "/doctor.jsp";
            } else if (intQueryNum == 3) {
              query3helper(request, response);
                url = "/doctor.jsp";
            } else if (intQueryNum ==4){
                ArrayList ret = ProjectDBAO.getPatients();
                request.setAttribute("patientList", ret);
                url = "/patient.jsp";
            } else if (intQueryNum ==5){
                query5helper (request, response);
                url = "/patient.jsp";
            } else if (intQueryNum ==6){
                query6helper (request, response);
                url = "/patient_detail.jsp";
            } else if (intQueryNum ==7){   //record list
                query7helper (request, response);
                url = "/records.jsp";
            } else if (intQueryNum ==8){  //individual record
                query8helper (request, response);
                url = "/record_detail.jsp";
            } else if (intQueryNum ==9){  //record search
                query9helper (request, response);
                url = "/records.jsp";
            }else if (intQueryNum ==10){  //record edit
                query8helper (request, response);
                url = "/record_edit.jsp";
            }
            else if (intQueryNum ==11){  //record update
                query8helper (request, response);
                url = "/record_detail.jsp";
            }
            else {
                throw new RuntimeException("Invalid query number: " + intQueryNum);
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/error.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
        
    }
    
    protected void query3helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String doctor_id = request.getParameter("doctor_query");
        
        ArrayList ret = ProjectDBAO.queryDoctor(doctor_id);
        request.setAttribute("doctorList", ret);
    }    
    protected void query5helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String patient_id = request.getParameter("patient_query");
        

        ArrayList ret = ProjectDBAO.queryPatient(patient_id);
        request.setAttribute("patientList", ret);
    }
    protected void query6helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String patient_id = request.getParameter("patient_id");
        

        ArrayList ret = ProjectDBAO.getPatient_detail(patient_id);
        request.setAttribute("patient_detail", ret);
    }
    protected void query7helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String patient_id = request.getParameter("patient_id");
        

        ArrayList ret = ProjectDBAO.getRecords(patient_id);
        request.setAttribute("record_list", ret);
    }
    protected void query8helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String patient_id = request.getParameter("patient_id");
        String visit_date = request.getParameter("visit_date");
        

        ArrayList ret = ProjectDBAO.getRecord_detail(patient_id, visit_date);
        request.setAttribute("record_detail", ret);
    }
    protected void query9helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String patient_id = request.getParameter("patient_id");
        String visit_date = request.getParameter("visit_date");
        

        ArrayList ret = ProjectDBAO.queryRecords(patient_id, visit_date);
        request.setAttribute("record_list", ret);
    }
    protected void query11helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String patient_id = request.getParameter("patient_id");
        String visit_date = request.getParameter("visit_date");
        String length_of_visit = request.getParameter("length_of_visit");
        String proc = request.getParameter("proc");
        String scheduling_of_treatment = request.getParameter("scheduling_of_treatment");
        String freeform_comments = request.getParameter("freeform_comments");
        String surgery_performed = request.getParameter("surgery_performed");
        String diagnosis = request.getParameter("diagnosis");
        String prescription = request.getParameter("prescription");
        

        ProjectDBAO.updateRecord_detail(patient_id, visit_date, length_of_visit, proc, 
                scheduling_of_treatment, freeform_comments, surgery_performed, diagnosis, prescription);
        ArrayList ret = ProjectDBAO.getRecord_detail(patient_id, visit_date);
        request.setAttribute("record_detail", ret);
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
