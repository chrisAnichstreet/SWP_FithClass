package at.htlinn.manzl;
//import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.PriorityBlockingQueue;

//import static org.postgresql.core.SqlCommandType.SELECT;

public class DBManager {

    private static DBManager DBM;
    private Connection c = null;
    EntityManagerFactory f;
    EntityManager em;


    private DBManager(){


        /*try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jpa","postgres", "1234");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        */

        f = Persistence.createEntityManagerFactory("my-unit");
        em = f.createEntityManager();
        
        System.out.println("Opened database successfully");


    }


    public static DBManager getDBM()
    {
        if(DBM == null)
        {
            DBM = new DBManager();
        }

        return DBM;

    }

    //TODO Herrausfindne wie man SQL Abfragen mit JPA "klassisch" schreibt
    //Kunden mit Vor und Nachnamen bestimmem
    public void getKunde(String first_name, String last_name) throws SQLException
    {
        //TODO Fehler ind ABFRRAGE BEHEBEN
        TypedQuery<Kunde> query = em.createQuery("SELECT k FROM Kunde k WHERE k.first_name = :first_name AND k.last_name = :last_name", Kunde.class);
        query.setParameter("first_name", first_name);
        query.setParameter("last_name", last_name);
        List<Kunde> results = query.getResultList();
        for(Kunde k : results)
        {
            System.out.printf("| %25s | %25s | %25s | %25s | %25s |\n","ID","titelv","vorname","nachname","titeln");
            System.out.printf("| %25s | %25s | %25s | %25s | %25s |\n",k.getId() ,k.getTitelV(),k.getFirst_name(),k.getLast_name(),k.getTitelN());
        }


    }

    public Kunde getKunde(int id) throws SQLException
    {

        Kunde kunde = em.find(Kunde.class, id);

        System.out.printf("| %25s | %25s | %25s | %25s | %25s |\n","ID","titelv","vorname","nachname","titeln");
        System.out.printf("| %25s | %25s | %25s | %25s | %25s |\n",kunde.getId() ,kunde.getTitelV(),kunde.getFirst_name(),kunde.getLast_name(),kunde.getTitelN());

        return kunde;

    }


    public void setKunde(String TitelV,String first_name,String last_name,String TitelN) throws SQLException
    {
        //PreparedStatement st = c.prepareStatement("INSERT INTO kunde (titelv,first_name,last_name,titeln) VALUES (?,?,?,?);");
        Kunde kunde = new Kunde();
        kunde.setTitelV(TitelV);
        kunde.setFirst_name(first_name);
        kunde.setLast_name(last_name);
        kunde.setTitelN(TitelN);

        em.getTransaction().begin();
        em.persist(kunde);
        em.getTransaction().commit();

    }
    //TODO Herrausfinden wie man Recursiv in JPA löscht
    public void delKunde(int id) throws SQLException
    {
        Kunde kunde = em.find(Kunde.class, id);

        em.getTransaction().begin();
        em.remove(kunde);
        em.getTransaction().commit();
    }




    //  ADRRESSE

    public Adresse getAdresse(int id) throws SQLException
    {
        Adresse adresse = em.find(Adresse.class, id);
        System.out.printf("| %25s | %25s | %25s | %25s | %25s | ","ID","Stadt","Street","PLZ","Hausnummer");
        System.out.printf("\n| %25s | %25s | %25s | %25s | %25s |",adresse.getId(),adresse.getStadt(),adresse.getStraße(), adresse.getPlz(),adresse.getHnr());
        return adresse;
    }


    public void setAdresse(String stadt,String straße,String plz,String hnr) throws SQLException
    {
        Adresse adresse = new Adresse();
        adresse.setStadt(stadt);
        adresse.setStraße(straße);
        adresse.setPlz(plz);
        adresse.setHnr(hnr);

        em.getTransaction().begin();
        em.persist(adresse);
        em.getTransaction().commit();
    }

    public void delAdresse(int id) throws SQLException
    {
      Adresse adresse = em.find(Adresse.class, id)
        em.getTransaction().begin();
        em.remove(adresse);
        em.getTransaction().commit();
    }



    //      ARTIKEL


    public void getArtikel(int id) throws SQLException
    {
       Artikel artikel = em.find(Artikel.class, id);
        System.out.printf("| %25s | %25s | %25s |","ID","name","preis");
        System.out.printf("\n| %25o | %25s | %25f |",artikel.getId(), artikel.getName(), artikel.getPreis());

    }


    public void setArtikel(String name, double preis) throws SQLException
    {
        Artikel artikel = new Artikel();
        artikel.setName(name);
        artikel.setPreis(preis);

        em.getTransaction().begin();
        em.persist(artikel);
        em.getTransaction().commit();
    }

    public void delArtikel(int id) throws SQLException
    {
        Artikel artikel = em.find(Artikel.class, id)
        em.getTransaction().begin();
        em.remove(artikel);
        em.getTransaction().commit();
    }


    //  BESTELLUNG_ARTIKEL

    public ArrayList<Bestellung_Artikel> getBestellung_Artikel(int id) throws SQLException
    {
        ArrayList<Bestellung_Artikel> ba_list = new ArrayList<Bestellung_Artikel>();
        Bestellung_Artikel bestellung_artikel = em.find(Bestellung_Artikel.class, id);
        System.out.printf("| %25s | %25s | %25s | \n","Bestell_ID","Artikel_ID","menge");
            System.out.printf("| %25s | %25s | %25s |\n", bestellung_artikel.getBestellungen_id(),bestellung_artikel.getArtikel_id(), bestellung_artikel.getMenge());
            ba_list.add(bestellung_artikel);
          return  ba_list;
    }


    public void setBestellungen_Artikel(int Bestellung_ID, int Artikel_ID, int menge) throws SQLException
    {
        /*
        Bestellung_Artikel bestellung_artikel = new Bestellung_Artikel(Bestellung_ID,Artikel_ID,menge);

        em.getTransaction().begin();
        em.persist(bestellung_artikel);
        em.getTransaction().commit();

         */
    }

    public void delBestellungen(int Bestellung_ID) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("DELETE FROM Bestellung_artikel WHERE Bestell_ID = ?;");
        st.setInt(1,Bestellung_ID);
        st.executeQuery();
        st.close();
    }



    //      BESTELLUNGEN

    //call by id
    public Bestellungen getBestellungen(int id) throws SQLException
    {
        Bestellungen bestellungen = em.find(Bestellungen.class, id);
        System.out.printf("| %25s | %25s | %25s | %25s | %25s |","ID","Kunden_ID","Adresse_Rechnung_ID","Adresse_Lieferung_ID");
        System.out.printf("| %25s | %25s | %25s | %25s | %25s |",bestellungen.getId(), bestellungen.getKunden_id().getId(), bestellungen.getAdresse_rechnung_id(), bestellungen.getAdresse_rechnung_id());

        return bestellungen;

    }
    //call by Costomer ID
    public ArrayList<Bestellung_Artikel> getBestellungen_from(int kunde_id) throws SQLException
    {
        ArrayList<Bestellung_Artikel> ba_liste = new ArrayList<Bestellung_Artikel>();
        //TODO Hier muss eine liste von bestellungs Id'S ausgegeben werden, eine rs.next() qusi, (So oft wie eben die Kundennummer in Bestellungen vorkommt.
        Bestellung_Artikel bestellung_artikel = em.find(Bestellung_Artikel.class, kunde_id);
                System.out.printf("| %25s | %25s | %25s | %25s | %25s |","ID","Kunden_ID","Adresse_Rechnung_ID","Adresse_Lieferung_ID");
            //System.out.printf("| %25s | %25s | %25s | %25s | %25s |",bestellung_artikel.getId(), bestellungen.getKunden_id(), bestellungen.getAdresse_rechnung_id(), bestellungen.getAdresse_rechnung_id());
        ba_liste.add(bestellung_artikel);
        return  ba_liste;//TODO FAKE BEHEBEN (UM EINE FEHLERMELDUNG ZU unterdrücken
    }


    public void setBestellung(int Kunde_ID,int Adresse_Rechnung_ID,int Adresse_Lieferung_ID) throws SQLException
    {
        Bestellungen bestellungen = new Bestellungen(getKunde(Kunde_ID), getAdresse(Adresse_Rechnung_ID), getAdresse(Adresse_Lieferung_ID));

        em.getTransaction().begin();
        em.persist(bestellungen);
        em.getTransaction().commit();
    }

    public void delBestellung(int id) throws SQLException
    {
        Bestellungen bestellungen = em.find(Bestellungen.class, id);


        em.getTransaction().begin();
        em.remove(bestellungen);
        em.getTransaction().commit();
    }



    //TODO die Möglichkeit der Klassichen SQL Abfage mit JPA auf Update einer Zeile einer Beliebigen Spalte anwenden
    //Sekuritie !
    public void Update(int id, String table,String collum, String value) throws SQLException {
        PreparedStatement st = null;
        try {
            Query queryee = em.createQuery(
                    "UPDATE Kunde SET first_name = 'Thomas' " +
                            "WHERE id = :p");
            int updateCount = queryee.executeUpdate();

            Query query = em.createQuery("UPDATE "+table+" SET "+collum+"="+value+" WHERE "+table+".id = '"+id+"' ;");
            query.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO Herrausfinden wöfür ich diese Methode mit PLAIN SQL benötigt habe
    public int lastSequencID(String table) throws SQLException
    {
        //PreparedStatement st = c.prepareStatement("SELECT currval (pg_get_serial_sequence(?,ID));");
        PreparedStatement st = c.prepareStatement("SELECT currval ('bestellungen_id_seq');");
        //st.setString(1,table);
        ResultSet rs = st.executeQuery();
        return rs.getInt("ID");
    }


    public void closeDB()
    {

        try {
            c.close();
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Datenbank-Connenction kann nicht geschlossen werden wenn sie nicht geöffnet ist!");
        }
    }

}

