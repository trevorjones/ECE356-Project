/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author william
 */
public class UserController {
    
    public static void create(Connection con, String user_id, String first_name, String last_name, String password, String type, String email) throws SQLException{
        PreparedStatement ps = con.prepareStatement("insert into User(user_id,first_name,last_name,password,type,email) values(?,?,?,?,?,?)");
        ps.setString(1, user_id);
        ps.setString(2, first_name);
        ps.setString(3,last_name);
        ps.setString(4, password);
        ps.setString(5, type);
        ps.setString(6, email);
        ps.execute();
        ps.close();
        
        // Create the associated type
        if (type.equals("patient")) {
            PatientController.create(con, user_id, "", "", "", "", 0);
        } else if (type.equals("doctor")) {
            DoctorController.create(con, user_id, "Not Set");
        } else {
            
        }
    }
    
}
