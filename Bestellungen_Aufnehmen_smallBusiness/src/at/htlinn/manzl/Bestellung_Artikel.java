package at.htlinn.manzl;

public class Bestellung_Artikel {

    private int Bestell_ID;
    private int Artikel_ID;
    private int menge;

    public Bestellung_Artikel(int Bestell_ID, int Artikel_ID, int menge)
    {
        this.Bestell_ID = Bestell_ID;
        this.Artikel_ID = Artikel_ID;
        this.menge = menge;
    }

    public int getBestell_ID() {
        return Bestell_ID;
    }

    public int getArtikel_ID() {
        return Artikel_ID;
    }

    public int getMenge() {
        return menge;
    }

}
