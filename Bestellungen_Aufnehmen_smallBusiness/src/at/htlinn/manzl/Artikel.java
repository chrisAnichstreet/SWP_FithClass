package at.htlinn.manzl;

public class Artikel {

    private int id;
    private String name;
    private double preis;


    public Artikel(int id, String name, double preis)
    {
        this.id = id;
        this.name = name;
        this.preis = preis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

}
