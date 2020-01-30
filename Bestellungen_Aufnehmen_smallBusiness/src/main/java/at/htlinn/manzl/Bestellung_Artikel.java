package at.htlinn.manzl;


import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public @Data class  Bestellung_Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    //@MapsId("id")
    //@JoinColumn(name = "bestellungen")
    @Getter
    @NonNull
    private Bestellungen bestellungen_id;


    @ManyToOne
    private Artikel artikel_id;
    private int menge;

    public Bestellung_Artikel(int id, Bestellungen bestellungen_id, Artikel artikel_ID, int menge) {
        this.id = id;
        this.bestellungen_id = bestellungen_id;
        artikel_id = artikel_ID;
        this.menge = menge;
    }
    public Bestellung_Artikel(){}
}
