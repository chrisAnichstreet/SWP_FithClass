package at.htlinn.manzl;

public class Adresse {

    private int id;
    private String stadt;
    private String straße;
    private String plz;
    private String hnr;

    public Adresse(int id, String stadt, String straße, String plz, String hnr)
    {
        this.id = id;
        this.stadt = stadt;
        this.straße = straße;
        this.plz =plz;
        this.hnr = hnr;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getStraße() {
        return straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getHnr() {
        return hnr;
    }

    public void setHnr(String hnr) {
        hnr = hnr;
    }
}
