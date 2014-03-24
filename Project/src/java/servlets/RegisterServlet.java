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

/**
 *
 * @author william
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String type = request.getParameter("type");
        String errorMsg = "";
        
        if (user_id == null || user_id.equals("")) {
            errorMsg += "User ID can't be empty.\n";
        }
        if (first_name == null || first_name.equals("")) {
            errorMsg += "First name can't be empty.\n";
        }
        if (last_name == null || last_name.equals("")) {
            errorMsg += "Last name can't be empty.\n";
        }
        if (password == null || password.equals("")) {
            errorMsg += "Password can't be empty.\n";
        }
        if (email == null || email.equals("")) {
            errorMsg += "Email can't be empty.\n";
        }
        
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        // Check for a new user
        try {
            ps = con.prepareStatement("select user_id from User where user_id=?");
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            
            if (!(rs != null && rs.next()) || rs.getString("user_id").equals(user_id)) {
                errorMsg += "user ID already exists.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
               
        if (!errorMsg.equals("")) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMsg + "</font>");
            rd.include(request, response);
        } else {
            try {
                ps = con.prepareStatement("insert into User(user_id,first_name,last_name,password,type,email) values(?,?,?,?,?,?)");
                ps.setString(1, user_id);
                ps.setString(2, first_name);
                ps.setString(3,last_name);
                ps.setString(4, password);
                ps.setString(5, type);
                ps.setString(6, email);
                
                ps.execute();
                
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                PrintWriter out = response.getWriter();
                out.println("<font color=green>Registration successful, please login.</font>");
                rd.include(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("DBConnection problem.");
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
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
