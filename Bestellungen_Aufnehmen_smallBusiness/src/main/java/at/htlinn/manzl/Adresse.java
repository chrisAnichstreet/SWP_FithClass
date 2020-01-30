package at.htlinn.manzl;

//import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "adresse")
public @Data class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stadt;
    private String straße;
    private String plz;
    private String hnr;

}
