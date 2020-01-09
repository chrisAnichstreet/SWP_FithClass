package at.htlinn.manzl;
import java.sql.*;

public class DB_Connect {

    private DB_Connect db;

    private void DB_Connect ()
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection db = DriverManager.getConnection(url, username, password);
        // Mann muss die Zeile Oben Berücksichtigen. UM eine Verbindung mit der Datenbank aufzubauen muss die URL zur angabe der Datenbank location am Rechner
        // angegeben werden. Der USer1n2a3me4 und das Passwort für die Anmeldung an der Datenbank. MEhr IFOS AUF: https://www.postgresql.org/docs/7.4/jdbc-use.html
    }

    public DB_Connect getDb()
    {
        if(db == null){
            DB_Connect db = new DB_Connect();
        }
        return db;
    }

    public
}
