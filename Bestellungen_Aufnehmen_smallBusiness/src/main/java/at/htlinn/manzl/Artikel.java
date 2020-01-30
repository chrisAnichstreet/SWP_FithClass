package at.htlinn.manzl;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public @Data class Artikel {

    //INSERT SOME ORM NOTATIONS for the Classes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double preis;
}
