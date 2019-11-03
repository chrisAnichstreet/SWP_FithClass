package at.htlinn.manzl;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBManager {



    private static DBManager DBM;
    private Connection c = null;

    private DBManager(){


        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bestellung","postgres", "1234");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
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


    //Kunde
    public void getKunde(String first_name, String last_name) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("SELECT * FROM kunde WHERE first_name = ? AND last_name = ?;");
        st.setString(1,first_name);
        st.setString(2,last_name);
        ResultSet rs = st.executeQuery();
        ArrayList<Kunde> kundenlist = new ArrayList<Kunde>();

        System.out.printf("| %25s | %25s | %25s | %25s | %25s |","ID","titelv","vorname","nachname","titeln");

        while(rs.next()){
            int id = rs.getInt("id");
            String titelv = rs.getString("titelv");
            String firstname = rs.getString("first_name");
            String lastname = rs.getString("last_name");
            String  titeln = rs.getString("titeln");
            kundenlist.add(new Kunde(id,titelv,firstname,lastname,titeln));
            System.out.printf("| %25s | %25s | %25s | %25s | %25s |",id,titelv,first_name,last_name,titeln);

        }
        rs.close();
        st.close();
        //return kundenlist;
        //return  kundenlist.get(0);
    }

    public void getKunde(int id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("SELECT * FROM kunde WHERE id = ? ;");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        ArrayList<Kunde> kundenlist = new ArrayList<Kunde>();
        System.out.printf("| %25s | %25s | %25s | %25s | %25s |","ID","titelv","vorname","nachname","titeln");
        while (rs.next()) {
            id = rs.getInt("id");
            String titelv = rs.getString("titelv");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String  titeln = rs.getString("titeln");
            kundenlist.add(new Kunde(id,titeln ,first_name,last_name,titeln));
            System.out.printf("| %25s | %25s | %25s | %25s | %25s |",id,titelv,first_name,last_name,titeln);
        }
        rs.close();
        st.close();
        //return kundenlist;
        //return  kundenlist.get(0);

    }


    public void setKunde(String TitelV,String first_name,String last_name,String TitelN) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("INSERT INTO kunde (titelv,first_name,last_name,titeln) VALUES (?,?,?,?);");
        st.setString(1,TitelV);
        st.setString(2,first_name);
        st.setString(3,last_name);
        st.setString(4,TitelN);
        st.executeQuery();
        st.close();
    }

    public void delKunde(int id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("DELETE FROM Kunde WHERE id = ?;");
        st.setInt(1,id);
        st.executeQuery();
        st.close();
    }




    //  ADRRESSE

    public ArrayList<Adresse> getAdresse(int id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("SELECT * FROM adresse WHERE ID = ?");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        ArrayList<Adresse> adresslist = new ArrayList<Adresse>();
        System.out.printf("| %25s | %25s | %25s | %25s | %25s | ","ID","Stadt","Street","PLZ","Hausnummer");

        while (rs.next()) {
            id = rs.getInt("ID");
            String stadt = rs.getString("Stadt");
            String straße = rs.getString("Street");
            String plz = rs.getString("PLZ");
            String  hnr = rs.getString("Hausnummer");
            adresslist.add(new Adresse(id,stadt,straße,plz,hnr));
            System.out.printf("\n| %25s | %25s | %25s | %25s | %25s |",id,stadt,straße,plz,hnr);

        }
        rs.close();
        st.close();
        return adresslist;
    }


    public void setAdresse(String stadt,String straße,String plz,String hnr) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("INSERT INTO adresse (Stadt,Street,PLZ,Hausnummer) VALUES (?,?,?,?);");
        st.setString(1,stadt);
        st.setString(2,straße);
        st.setString(3,plz);
        st.setString(4,hnr);
        st.executeQuery();
        st.close();
    }

    public void delAdresse(int id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("DELETE FROM adresse WHERE ID = ?;");
        st.setInt(1,id);
        st.executeQuery();
        st.close();
    }



    //      ARTIKEL


    public Artikel getArtikel(int id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("SELECT * FROM artikel WHERE id = ?;");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        ArrayList<Artikel> artikellist = new ArrayList<>();
        System.out.printf("| %25s | %25s | %25s |","ID","name","preis");

        while (rs.next()) {
            id = rs.getInt("ID");
            String name = rs.getString("name");
            double preis = rs.getDouble("preis");
            artikellist.add(new Artikel(id,name,preis));
            System.out.printf("\n| %25o | %25s | %25f |",id,name,preis);

        }
        rs.close();
        st.close();
        return artikellist.get(0);
    }


    public void setArtikel(String name, double preis) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("INSERT INTO artikel (name, preis) VALUES (?,?);");
        st.setString(1,name);
        st.setDouble(2,preis);
        st.executeQuery();
        st.close();
    }

    public void delArtikel(int id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("DELETE FROM artikel WHERE ID = ?;");
        st.setInt(1,id);
        st.executeQuery();
        st.close();
    }


    //  BESTELLUNG_ARTIKEL

    public ArrayList<Bestellung_Artikel> getBestellung_Artikel(int id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("SELECT * FROM bestellung_artikel WHERE Bestell_ID = ?");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        ArrayList<Bestellung_Artikel> bestellungenartikellist = new ArrayList<Bestellung_Artikel>();
        System.out.printf("| %25s | %25s | %25s | \n","Bestell_ID","Artikel_ID","menge");

        while (rs.next()) {
            int bestell_id = rs.getInt("Bestell_ID");
            int artikel_id = rs.getInt("Artikel_ID");
            int menge = rs.getInt("menge");
            bestellungenartikellist.add(new Bestellung_Artikel(bestell_id,artikel_id, menge));
            System.out.printf("| %25s | %25s | %25s |\n",id,artikel_id,menge);
        }
        rs.close();
        st.close();
        return bestellungenartikellist;
    }


    public void setBestellungen_Artikel(int Bestellung_ID, int Artikel_ID, int menge) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("INSERT INTO Bestellung_artikel (Bestell_ID,Artikel_ID,menge) VALUES (?,?,?);");
        st.setInt(1,Bestellung_ID);
        st.setInt(2,Artikel_ID);
        st.setInt(3,menge);
        st.executeQuery();
        st.close();
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
        PreparedStatement st = c.prepareStatement("SELECT * FROM bestellungen WHERE ID = ?");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        ArrayList<Bestellungen> bestellungenlist = new ArrayList<Bestellungen>();
        System.out.printf("| %25s | %25s | %25s | %25s | %25s |","ID","Kunden_ID","Adresse_Rechnung_ID","Adresse_Lieferung_ID");

        while (rs.next()) {
            id = rs.getInt("ID");
            int kunde_id = rs.getInt("Kunde_ID");
            int adresse_rechnung_id = rs.getInt("Adresse_Rechnung_ID");
            int adresse_lieferung_id = rs.getInt("Adresse_Lieferung_ID");
            bestellungenlist.add(new Bestellungen(id,kunde_id,adresse_rechnung_id,adresse_lieferung_id));
            System.out.printf("| %25s | %25s | %25s | %25s | %25s |",id,kunde_id,adresse_lieferung_id, adresse_lieferung_id);
        }
        rs.close();
        st.close();
        return bestellungenlist.get(0);
    }
    //call by Costomer ID
    public ArrayList<Bestellungen> getBestellungen_from(int kunde_id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("SELECT * FROM bestellungen WHERE ID = ?");
        st.setInt(1,kunde_id);
        ResultSet rs = st.executeQuery();
        ArrayList<Bestellungen> bestellungenlist = new ArrayList<Bestellungen>();
        System.out.printf("| %25s | %25s | %25s | %25s | %25s |","ID","Kunden_ID","Adresse_Rechnung_ID","Adresse_Lieferung_ID");

        while (rs.next()) {
            int id = rs.getInt("ID");
            kunde_id = rs.getInt("Kunde_ID");
            int adresse_rechnung_id = rs.getInt("Adresse_Rechnung_ID");
            int adresse_lieferung_id = rs.getInt("Adresse_Lieferung_ID");
            bestellungenlist.add(new Bestellungen(id,kunde_id,adresse_rechnung_id,adresse_lieferung_id));
            System.out.printf("| %25s | %25s | %25s | %25s | %25s |",id,kunde_id,adresse_lieferung_id, adresse_lieferung_id);
        }
        rs.close();
        st.close();
        return bestellungenlist;
    }


    public void setBestellung(int Kunde_ID,int Adresse_Rechnung_ID,int Adresse_Lieferung_ID) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("INSERT INTO bestellungen (Kunde_ID,Adresse_Rechnung_ID, Adresse_Lieferung_ID)  VALUES (?,?,?);");
        st.setInt(1,Kunde_ID);
        st.setInt(2,Adresse_Rechnung_ID);
        st.setInt(3,Adresse_Lieferung_ID);
        st.executeQuery();
        st.close();
    }

    public void delBestellung(int id) throws SQLException
    {
        PreparedStatement st = c.prepareStatement("DELETE FROM bestellungen WHERE ID = ?;");
        st.setInt(1,id);
        st.executeQuery();
        st.close();
    }




    //Sekuritie !
    public void Update(int id, String table,String collum, String value) throws SQLException {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("UPDATE ? SET ? = ? WHERE ?;");

        st.setString(1,table);
        st.setString(2,collum);
        st.setString(3,value);
        st.setInt(4,id);
        st.executeQuery();
        st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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

