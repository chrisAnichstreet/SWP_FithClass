package at.htlinn.manzl;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Table(name ="Kunde")
public @Data class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String TitelV;
    private String first_name;
    private String last_name;
    private String TitelN;








/*
    public Kunde( String TitelV, String first_name, String last_name, String TitelN)
    {
        //this.ID = ID;
        this.TitelV = TitelV;
        this.first_name = first_name;
        this.last_name = last_name;
        this.TitelN = TitelN;
    }


 */
}
