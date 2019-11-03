package at.htlinn.manzl;

public class Kunde {

    private int ID;
    private String TitelV;
    private String first_name;
    private String last_name;
    private String TitelN;


    public Kunde(int ID, String TitelV, String first_name, String last_name, String TitelN)
    {
        this.ID = ID;
        this.TitelV = TitelV;
        this.first_name = first_name;
        this.last_name = last_name;
        this.TitelN = TitelN;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitelV() {
        return TitelV;
    }

    public void setTitelV(String titelV) {
        TitelV = titelV;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitelN() {
        return TitelN;
    }

    public void setTitelN(String titelN) {
        TitelN = titelN;
    }





}
