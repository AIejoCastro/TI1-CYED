package UI;
import Model.Airline;
import structures.Queue;

import java.io.IOException;

import java.util.*;

public class Main {
    Scanner sc = new Scanner(System.in);
    Airline avianca = new Airline();
    private Queue queue;
    private Queue queueToPrint;


    public static void main(String[] args) throws IOException {

        Main m = new Main();
        m.load();
        m.loadHashInfo();

        m.menu();

    }



    private void loadHashInfo() {

        avianca.loadHashPassengerInfo();

    }


    public void menu(){
    int option = 0;
       do{

           System.out.println("Welcome to the Avianca Airline assistant" + "\n" +
           "1.Passenger Information" + "\n" +
           "2.Simulate the entry of all passengers" + "\n" +
           "3.Select the manually who is entering the airline "  + "\n"+
           "4.Show the entrance" + "\n" +
           "5.Show the exit" + "\n" +
           "9. Print queue" + "\n" +
                   "0.Exit");

           option = sc.nextInt();
           sc.nextLine();
           switch (option) {
               case 1:  // Ya quedó listo el primer caso
                   System.out.println("Please digit the id of the person you want to search: ");
                   String key = sc.nextLine();
                   System.out.println(avianca.searchPassengerInformation(key));

                   break;
               case 2:
                   System.out.println(avianca.registerPassenger(2));
                   break;
               case 3:
                   registerPassengersManually();

                   break;
               case 4:
                   System.out.println(avianca.showOrderEntrance());

                   break;
               case 5:
                   System.out.println(avianca.showOrderExit());

                   break;
               case 0:
                   System.out.println("Exiting the program...");
                   break;
               case 9:
                   printQueque();

               default:
                   System.out.println("Wrong option");
                   break;

           }


       }while(option!=0);


    }

    private void printQueque() {

            System.out.println(avianca.printQueue(queue,queueToPrint));

    }

    private void registerPassengersManually() {
        String IDperson= "";
        System.out.println("Enter the number of people that is coming to the airline: " + "\n");
        int ite = sc.nextInt();
        queue = new Queue<>(ite);
        queueToPrint= new Queue<>(ite);
        sc.nextLine();
        for(int i =0; i<ite;i++){
            System.out.println("Please enter the ID of the " + (i+1) +" person that arrived" + "\n");
            IDperson = sc.nextLine();
            System.out.println(avianca.registerPassengerManually(IDperson,queue,queueToPrint));
        }


    }

    public void load() throws IOException {
        avianca.loadEpassenger();
        avianca.loadNEpassenger();

    }
}


