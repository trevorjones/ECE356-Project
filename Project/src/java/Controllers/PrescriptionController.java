/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Prescription;

/**
 *
 * @author william
 */
public class PrescriptionController {
    
    public static ArrayList<Prescription> getAll(Connection con) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        ArrayList<Prescription> ret = null;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Prescription");
            ret = new ArrayList<Prescription>();
            while (resultSet.next()) {
                Prescription p = new Prescription(
                        resultSet.getString("name"),
                        resultSet.getString("alias"),
                        resultSet.getString("description"));
                ret.add(p);
            }
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public static ArrayList<Prescription> query(Connection con, String prescription_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Prescription> ret;

        try {
            //Build SQL Query
            String query = "SELECT * FROM Prescription "
                    + "WHERE Prescription.name LIKE ? or Prescription.alias LIKE ? or Prescription.description LIKE ?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%" + prescription_id + "%");
            pstmt.setString(2, "%" + prescription_id + "%");
            pstmt.setString(3, "%" + prescription_id + "%");

            ResultSet resultSet;
            resultSet = pstmt.executeQuery();

            ret = new ArrayList<Prescription>();
            while (resultSet.next()) {
                Prescription p = new Prescription(
                        resultSet.getString("Prescription.name"),
                        resultSet.getString("Prescription.alias"),
                        resultSet.getString("Prescription.description"));
                ret.add(p);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}
