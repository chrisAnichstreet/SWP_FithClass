package at.htlinn.manzl;

public class Main {

    public static void main(String[] args)
    {

        inputHandler.getInstance().execute("");



        /*while(true){
            String input ="";
            //Scanner scan = new Scanner(System.in);

            do {

                   //input = scan.next();

                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }while (input == "");

            System.out.println(input);
            if(input.equals("?"))
            {
                System.out.println(input);

                System.out.println(" ---   HELP   ---");
                System.out.println("The System is based on the logic Methods of CRUD.");
                System.out.println("If your reaction at this point is 'da Frog is CRUD?!' Well just Google.");
                System.out.println("Just kidding, CRUD is Create Read Update and Delete.");
                System.out.println("These are the most basic tools for every data base and these are implementet \n here in this Terminal.");
                System.out.println("Each Method is called by using the sign word and the Name of the table. ");
                System.out.println("Create: set");
                System.out.println("Read: get");
                System.out.println("Update: update");
                System.out.println("Delete: del");
                System.out.println("");
                System.out.println("--- How to call Methods. ---");
                System.out.println("Write the sign word and with a space in between the table name ");
                System.out.println("Example: get Kunde will return a Kunde object");
                System.out.println("If you want to search the table 'Kunde' for a certain costumer type in the style like described above: getKunde(firstname,lastname)");
                System.out.println("When the Syntax is correct and correct valuses where chosen for the parameters you will recive the entery you searched for.");
                System.out.println("This will look in our example like this:");
                System.out.println("Beispiel Tabelle");
                System.out.println("Parameters for search are: getKunde(Joe,Dough)");
                System.out.println("");
                System.out.println("--- Setting up a Bestellung ---");
                System.out.println("First Step is to create a new Bestellung \n, it is crutial to follow the logical steps.\n " +
                        "You need to fetch the Kunden ID with the 'get Kunde ()', or create a new one with the 'set Kunde' command.\n" +
                        "Then enter the Adresse ID where the bill should go to and where the order should be delivered.\n" +
                        "Now You create a Warenkorb with 'set Warenkorb ()'. The Parameters must be the Bestellung ID.\n" +);


            }

            if(input.equals("")){}
            else{
                inputHandler.getInstance().execute(input);
            }

        }*/

    }
}
