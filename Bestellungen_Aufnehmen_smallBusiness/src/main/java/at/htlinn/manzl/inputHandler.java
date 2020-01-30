package at.htlinn.manzl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class inputHandler {

    private static inputHandler inputHandler = null;
    private func mode;
    private table  tab;
    private String[] words;
    private ArrayList<String> params;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public inputHandler(){


        System.out.println("Welcome to Small Bussiness DB!");
        System.out.println("------------------------------");
        System.out.println("You don't like Terminals? Take it like a (wo)man or leave it.");
        System.out.println("Have fun!");
        System.out.println("Enter '?' to list all Methods.");

    }


    public static inputHandler getInstance()
    {
        if(inputHandler == null)
        {
            inputHandler = new inputHandler();
        }

        return inputHandler;

    }

    public void Scan()
    {
        while (true)
        {
            String input = "";
            do {
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } while (input == "");

            System.out.println(input);
            if (input.equals("?")) {
                System.out.println(input);
               help();
            }
            else {
                execute(input);
            }
        }
    }

    public String Scanline()
    {
        while (true)
        {
            String input = "";
            do {
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } while (input == "");

            System.out.println(input);
            if (input.equals("?")) {
                System.out.println(input);
                help();
            }
            else {
                return (input);
            }
        }
    }



    public void execute(String input)
    {
         words = input.split(" ");

         try {
             mode = getMode(words[0]);
         } catch (NullPointerException | IndexOutOfBoundsException e){
             System.out.println("The Function  does not exist. Check your Syntax or enter '?'.");
         }

        try {
             tab = getTabname(words[1]);
        } catch (NullPointerException | IndexOutOfBoundsException e){
            System.out.println("The Table entered  does not exist. Check your Syntax or enter '?'.");
        }


        try { params = getParams(words[2]);
        } catch (NullPointerException| IndexOutOfBoundsException e){
            System.out.println("Parameters you entered where incorrect. Check your Syntax or enter '?'.");
        }


        try {
            Supervisor(mode, tab, params);
        } catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("There was an Error in the Execution of the Query! Check the validness of the parameters");
        }
        Scan();
    }

    private ArrayList<String> getParams(String word) throws  IndexOutOfBoundsException
    {
        ArrayList pam = new ArrayList();
        word = word.replace("(","");
        word = word.replace(")","");
        if(word.contains(",")) {

            String[] params = word.split(",");
            for(String i: params)
            {
                pam.add(i);
            }

        }
        else {

            pam.add(word);
        }
        return pam;
    }

    private table getTabname(String input)throws IndexOutOfBoundsException
    {
        if (input.equals("Kunde")){
            return table.Kunde;
        }
        if (input.equals("Artikel")){
            return table.Artikel;
        }
        if (input.equals("Adresse")){
            return table.Adresse;
        }
        if (input.equals("Bestellungen")){
            return table.Bestellungen;
        }
        if (input.equals("Bestellungen_Kunde")){
            return table.Bestellungen_Kunde;
        }
        if (input.equals("Warenkorb")){
            return  table.Warenkorb;
        }

        return null;
    }
    private func getMode(String input) throws IndexOutOfBoundsException
    {
        if (input.equals("get")){
            return func.get;
        }
        if (input.equals("set")){
            return func.set;
        }
        if (input.equals("del")){
            return func.del;
        }
        if (input.equals("update")){
            return func.update;
        }

        return null;
    }

    private void Supervisor(func mode, table tab, ArrayList<String> params) throws SQLException {

        if((mode == func.get) && (tab == table.Kunde))
        {
            if(params.size() >1)
            {
                DBManager.getDBM().getKunde(params.get(0),params.get(1));
            }
            else{
                System.out.println(params.get(0));
                DBManager.getDBM().getKunde(Integer.parseInt(params.get(0)));
            }
        }

        if((mode == func.set) && (tab == table.Kunde))
        {
            DBManager.getDBM().setKunde(params.get(0),params.get(1),params.get(2),params.get(3));
        }

        if((mode == func.del) && (tab == table.Kunde))
        {
            DBManager.getDBM().delKunde(Integer.parseInt( params.get(0)));
        }

        if((mode == func.get) && (tab == table.Bestellungen))
        {
            if(params.size()>1){
                DBManager.getDBM().getBestellungen(Integer.parseInt( params.get(0)));
            }
            else{
                easygetBestellung();
            }
        }

        if((mode == func.get) && (tab == table.Bestellungen_Kunde))
        {
            DBManager.getDBM().getBestellungen_from(Integer.parseInt( params.get(0)));
        }

        if((mode == func.set) && (tab == table.Bestellungen))
        {
            if(params.size()>1){
                DBManager.getDBM().setBestellung(Integer.parseInt( params.get(0)),Integer.parseInt( params.get(1)),Integer.parseInt( params.get(2)));
            }
            else{
                easysetBestellung();
            }

        }

        if((mode == func.del) && (tab == table.Bestellungen))
        {
            DBManager.getDBM().delBestellung(Integer.parseInt( params.get(0)));
        }

        if((mode == func.get) && (tab == table.Warenkorb))
        {
            DBManager.getDBM().getBestellung_Artikel(Integer.parseInt( params.get(0)));
        }

        if((mode == func.set) && (tab == table.Warenkorb))
        {
            DBManager.getDBM().setBestellungen_Artikel(Integer.parseInt( params.get(0)), Integer.parseInt( params.get(1)), Integer.parseInt( params.get(2)));
        }

        if((mode == func.del) && (tab == table.Warenkorb))
        {
            DBManager.getDBM().delBestellung(Integer.parseInt( params.get(0)));
        }

        if((mode == func.get) && (tab == table.Artikel))
        {
            DBManager.getDBM().getArtikel(Integer.parseInt(params.get(0)));
        }

        if((mode == func.set) && (tab == table.Artikel))
        {
            DBManager.getDBM().setArtikel(params.get(0),Double.parseDouble(params.get(1)));
        }
        if((mode == func.del) && (tab == table.Artikel))
        {
            DBManager.getDBM().delArtikel(Integer.parseInt(params.get(0)));
        }

        if((mode == func.get) && (tab == table.Adresse))
        {
            DBManager.getDBM().getAdresse(Integer.parseInt(params.get(0)));
        }
        if((mode == func.set) && (tab == table.Adresse))
        {
            DBManager.getDBM().setAdresse(params.get(0),params.get(1),params.get(2),params.get(3));
        }
        if((mode == func.del) && (tab == table.Adresse))
        {
            DBManager.getDBM().delAdresse(Integer.parseInt(params.get(0)));
        }

        if((mode == func.update))
        {
            DBManager.getDBM().Update(Integer.parseInt(params.get(0)),tab.name(),params.get(1),params.get(2));
        }


    }

    private void easygetBestellung()
    {
        ArrayList<Integer> params = new ArrayList();
        System.out.println("SEE ORDER:\n-------\n Enter ORDER ID:");
        params.add(Integer.parseInt(Scanline()));
        System.out.println("Articles And their Amount:\n=========================");
        ArrayList<Bestellung_Artikel> warenkorb = new ArrayList<Bestellung_Artikel>();
        try {
           warenkorb = DBManager.getDBM().getBestellung_Artikel(params.get(0));
        } catch (SQLException e) { }
        System.out.println("-------------------------------------------------------------------------------------------------------\n");
        System.out.print("Names and Prices:\n==========================\n");
        for( int i = 0; i < warenkorb.size(); i++)
        {
            try {
                DBManager.getDBM().getArtikel(warenkorb.get(i).getArtikel_id().getId());
            } catch (SQLException e) {
            }
        }



    }

    private void easysetBestellung()
    {
        ArrayList<String> params = new ArrayList();
        System.out.println("New ORDER:\n-------\n Enter Costomers ID:");
        params.add(Scanline());
        System.out.println("Enter adress ID for the bill:");
        params.add(Scanline());
        System.out.println("Enter adress ID for delivery:");
        params.add(Scanline());

        try {
            DBManager.getDBM().setBestellung(Integer.parseInt( params.get(0)),Integer.parseInt( params.get(1)),Integer.parseInt( params.get(2)));
        } catch (SQLException e) {
            System.out.print("...");
        }
        int bestellungs_id = 0;
        try {
            bestellungs_id = DBManager.getDBM().lastSequencID("Bestellungen");
        } catch (SQLException e) {
            System.out.print("~~~");
        }
        try {
            fillWarenkorb(bestellungs_id);
        } catch (SQLException e) {
            System.out.println("***");
        }
    }

    private void fillWarenkorb(int bestellungs_id) throws SQLException {
        ArrayList<Integer> articel_id = new ArrayList();
        ArrayList<Integer> menge = new ArrayList();
        System.out.println("Enter the articles and afterwards the number of them.");
        System.out.printf(" Enter '!' to save.");

        do{
            System.out.print("\nArticle ID: ");
            articel_id.add(Integer.parseInt(Scanline()));
            System.out.print("Amount of the article: ");
            menge.add(Integer.parseInt(Scanline()));
        } while (!Scanline().equals("!"));

        for(int i = 0; i< articel_id.size(); i++)
        {
            DBManager.getDBM().setBestellungen_Artikel(bestellungs_id,articel_id.get(i),menge.get(i));
        }


    }

    private void help()
    {
        System.out.println(" ---   HELP   ---");
        System.out.println("The System is based on the logic Methods of CRUD.");
        System.out.println("If your reaction at this point is 'da Frog is CRUD?!', chill out.");
        System.out.println("CRUD is Create Read Update and Delete.");
        System.out.println("These are the most basic tools for every data base and these are implementet \n here in this Terminal.");
        System.out.println("Each Method is called by using the sign word and the Name of the table. ");
        System.out.println("Create: set");
        System.out.println("Read: get");
        System.out.println("Update: update");
        System.out.println("Delete: del");
        System.out.println("");
        System.out.println("--- How to call Methods. ---");
        System.out.println("Write the sign word and with a space in between the table name ");
        System.out.println("Example: get Kunde will return all information of the table entry.");
        System.out.println("If you want to search the table 'Kunde' for a certain costumer, type in the style like described above:\nget Kunde(firstname,lastname)");
        System.out.println("When the Syntax is correct and correct values where chosen for the parameters you will receive the entry you searched for.");
        System.out.println("This will look in our example like this:");
        System.out.println("Beispiel Tabelle");
        System.out.println("Parameters for search are: get Kunde(Joe,Dough)\nTo call any of the CRUD functions enter the ID as the parameter. \n" +
                "In Method are at least implemented in this style. \n" +
                "Update etwa funktioniert: update Kunde (1,first_name,werner) id,collum, new value ");
        System.out.println("");
        System.out.println("--- Easy Mode ---");
        System.out.println("All possible functions can be done in the way described above. But especially to create and viewing Orders is difficult. \n" +
                "Therefore there is a interactive mode for creating and viewing a Order.\n" +
                "To Call the interactive mod just omit the Parameters in the brackets as in the example underneath:\n" +
                "Creating a Order: set Bestellung ()\n" +
                "It will open the dialog, all steps are explaned.\n" +
                "======================== Have Fun ! ========================");
    }

}
