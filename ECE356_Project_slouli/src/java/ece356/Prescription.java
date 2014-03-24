/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ece356;

/**
 *
 * @author slouli
 */
public class Prescription {
    private String name;
    private String alias;
    private String description;
    
    public Prescription(String name, String alias, String description) {
        this.name = name;
        this.alias = alias;
        this.description = description;
    }

    Prescription(String string, String string0, String string1, boolean add) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public String getDescr() {
        return this.description;
    }
    
    public void setDescr(String description) {
        this.description = description;
    }
}
