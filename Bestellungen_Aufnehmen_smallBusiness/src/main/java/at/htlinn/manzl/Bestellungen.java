package at.htlinn.manzl;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "bestellungen")
public @Data class Bestellungen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @Getter
    private Kunde kunde_id;

    @ManyToOne
    @Getter
    private Adresse adresse_rechnung_id;
    @ManyToOne
    private Adresse adresse_lieferung_id;

    /*
   @OneToMany( targetEntity=Bestellung_Artikel.class )
   private List<Bestellung_Artikel> bestellung_artikelArrayList = new ArrayList<>();
    */
   public  Bestellungen(){}

   public  Bestellungen( Kunde kunde_id, Adresse adresse_rechnung_id, Adresse adresse_lieferung_id){

       this.kunde_id = kunde_id;
       this.adresse_rechnung_id = adresse_rechnung_id;
       this.adresse_lieferung_id = adresse_lieferung_id;
   }

    public Kunde getKunden_id() {
       return kunde_id;
    }
}
