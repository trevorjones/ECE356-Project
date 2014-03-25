/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author william
 */
public class Prescription {
    String name;
    String alias;
    String description;
    
    public Prescription() {
        
    }
    
    public Prescription(String name, String alias, String description) {
        this.name = name;
        this.alias = alias;
        this.description = description;
    }
    
    public Prescription(String name, String alias, String description, boolean add) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setNamer(String name) {
        this.name = name;
    }
    
    public String getAlias() {
        return alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
