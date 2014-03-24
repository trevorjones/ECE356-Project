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
                if(request.getParameter("prescription_query")==null) {
                    ArrayList ret = ProjectDBAO.getPrescriptions();
                    request.setAttribute("prescriptionList", ret);
                } else {
                    ArrayList ret = query1Helper(request, response);
                    request.setAttribute("prescriptionList", ret);
                }
                url = "/prescription.jsp";
            } else if (intQueryNum == 2) {  //Doctor views
                if(request.getParameter("doctor_query")==null) {
                    ArrayList ret = ProjectDBAO.getDoctors();
                    request.setAttribute("doctorList", ret);
                } else {
                    ArrayList ret = query2Helper(request, response);
                    request.setAttribute("doctorList", ret);
                }
                url = "/doctor.jsp";
            } else if (intQueryNum == 3) {
                ArrayList ret = query3Helper(request, response);
                request.setAttribute("apptList", ret);
                url = "/appointment.jsp";
            } else {
                throw new RuntimeException("Invalid query number: " + intQueryNum);
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/error.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    protected ArrayList query1Helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String prescription_name = request.getParameter("prescription_query");

        ArrayList ret = ProjectDBAO.queryPrescription(prescription_name);
        return ret;
    }
    
    protected ArrayList query2Helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String doctor_id = request.getParameter("doctor_query");

        ArrayList ret = ProjectDBAO.queryDoctor(doctor_id);
        return ret;
    }

    protected ArrayList query3Helper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String doctor_id = request.getParameter("doctor_id");

        ArrayList ret = ProjectDBAO.queryDoctorAppt(doctor_id);
        return ret;
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
