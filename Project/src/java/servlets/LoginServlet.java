/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Doctor;
import models.Patient;
import models.User;

/**
 *
 * @author william
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        String errorMsg = null;
        
        if (user_id == null || user_id.equals("") || password == null || password.equals("")) {
            invalidUser(request, response);
        } else {

            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                ps = con.prepareStatement("select * from User where user_id=? and password=? limit 1");
                ps.setString(1, user_id);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs != null && rs.next()) {
                    HttpSession session = request.getSession();
                    String type = rs.getString("type");
                    
                    if (type.equals("doctor")) {
                        ps = con.prepareStatement("select * from User,Doctor where User.user_id = ? and User.user_id = Doctor.user_id");
                        ps.setString(1, user_id);
                        rs = ps.executeQuery();
                        rs.next();
                        session.setAttribute("user", new Doctor(rs));
                    } else if (type.equals("patient")) {
                        ps = con.prepareStatement("select * from User,Patient where User.user_id = ? and User.user_id = Patient.user_id");
                        ps.setString(1, user_id);
                        rs = ps.executeQuery();
                        rs.next();
                        session.setAttribute("user", new Patient(rs));
                    } else {
                        type = "user";
                        session.setAttribute("user", new User(rs));
                    }
                    session.setAttribute("type", type);
                    
                    
                    // Redirect to difference pages depending on user type
                    response.sendRedirect("home.jsp");
                } else {
                    invalidUser(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("DB Connection Problem");
            } finally {
                try {
                    rs.close();
                    ps.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }        
    }
    
    private void invalidUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        PrintWriter out = response.getWriter();
        out.println("<div class=\"container\" style=\"width:300px;\"><span class=\"label label-danger\" style=\"display:block;\">Invalid user id or password</span></div>");
        rd.include(request, response);
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
