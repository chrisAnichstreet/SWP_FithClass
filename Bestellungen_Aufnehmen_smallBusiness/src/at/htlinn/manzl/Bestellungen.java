package at.htlinn.manzl;

public class Bestellungen {

    private int id;
    private int kunden_id;
    private int adresse_rechnung_id;
    private int adresse_lieferung_id;

    public Bestellungen(int id, int kunden_id,int adresse_rechnung_id,int adresse_lieferung_id)
    {
        this.id = id;
        this.kunden_id = kunden_id;
        this.adresse_rechnung_id = adresse_rechnung_id;
        this.adresse_lieferung_id = adresse_lieferung_id;
    }
}
