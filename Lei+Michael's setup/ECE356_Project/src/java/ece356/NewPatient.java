package ece356;

public class NewPatient {
    private int insertflag;
    
    public NewPatient (int insertflag){
        this.insertflag = insertflag;
    }
    
    public int getInsertFlag() {
        return this.insertflag;
    }
}