package Model;


import java.io.*;
import java.util.Arrays;
import com.google.gson.Gson;
import structures.*;

import java.util.ArrayList;
import java.util.Collections;


public class Airline {

    static String folder = "data";
    static String pathNE = "data/dataNEPassenger.txt";
    static String pathE = "data/dataEPassenger.txt";
    ArrayList<NEPassenger> nePassengers;
    ArrayList<EPassenger> ePassengers;
    ArrayList<Passenger> totalPassengers;
    private PriorityQueue ePassengerEntrance;
    private PriorityQueue nePassengerEntrance;

    private Hashtable hashtable;

    public Airline(){
        ePassengers = new ArrayList<>();
        nePassengers = new ArrayList<>();
        totalPassengers= new ArrayList<>();
        hashtable = new Hashtable(54);
        ePassengerEntrance = new PriorityQueue(18);
        nePassengerEntrance = new PriorityQueue(36);

    }
    //Buscar la información de un pasajero
    public String searchPassengerInformation(String key) {
        String msg = "";

        Passenger passengerFounded = (Passenger) hashtable.search(key);
        if (passengerFounded == null) return msg = "THERE'S NO USER WITH THAT IDENTIFICATION";
        else {

        if (passengerFounded.isExecutive() == true) {
            EPassenger passengerIsExecutive = (EPassenger) passengerFounded;
            msg = "Name: " + passengerIsExecutive.getName() + " " +"\n" +
                    "Id: " + passengerIsExecutive.getId() + " " +"\n" +
                    "Seat: " + passengerIsExecutive.getSeat() + " " +"\n" +
                    "Preference: " + passengerIsExecutive.isPreference() + " " +"\n" +
                    "ArrivalTime: " + passengerIsExecutive.getArrivalTime() + " " +"\n" +
                    "Miles: " + passengerIsExecutive.getMiles() +
                    "\nTHE USER FOUNDED IS EXECUTIVE";
        } else {
            NEPassenger passengerIsNotExecutive = (NEPassenger) passengerFounded;
            msg = "Name: " + passengerIsNotExecutive.getName() + " " + "\n" +
                    "Id: " + passengerIsNotExecutive.getId() + " " +"\n" +
                    "Seat: " + passengerIsNotExecutive.getSeat() + " " +"\n" +
                    "ArrivalTime: " + passengerIsNotExecutive.getArrivalTime() +
                    "\nTHE USER FOUNDED IS A NON EXECUTIVE PASSENGER";

        }
        }
        return msg;

    }
    //Registra la información de los pasajeros en tablas hash
    private void loadHashInfo() {

        for(int i=0; i<nePassengers.size();i++){
            HashtableNode passengerNE = new HashtableNode<>(nePassengers.get(i).getId(),nePassengers.get(i));
            hashtable.insert(passengerNE,nePassengers.get(i).getId());

        }

        for(int j=0;j<ePassengers.size();j++){
            HashtableNode passengerE = new HashtableNode<>(ePassengers.get(j).getId(),ePassengers.get(j));
            hashtable.insert(passengerE,ePassengers.get(j).getId());

        }
    }

    //Registrar la llegada de un pasajero
    public String registerPassenger(Queue queue, Queue queueToPrint) {
        String msg = "--Orden de llegada--" + "\n\n";
        defineHow();
        for (int i = 1; i <= totalPassengers.size(); i++) {
            msg += registerPassengerAutomatically(queue, queueToPrint, String.valueOf(i));
        }
        return msg;
    }

    private String registerPassengerAutomatically(Queue queue, Queue queueToPrint, String i) {
        String msg;
        if (hashtable.containsKey(i)) {
            Passenger passengerFounded = (Passenger) hashtable.search(i);
            if (passengerFounded.isExecutive()) {
                EPassenger exPassenger = (EPassenger) passengerFounded;
                QueueNode<EPassenger> passengerQueueNode = new QueueNode<>(exPassenger);
                queue.offer(passengerQueueNode);
                queueToPrint.offer(passengerQueueNode);
                msg = "Name: " + passengerQueueNode.getValue().getName() + " with ID: " + passengerQueueNode.getValue().getId()+
                        " WAS REGISTERED SUCCESSFULLY\n";
            } else {
                NEPassenger nePassenger = (NEPassenger) passengerFounded;
                QueueNode<NEPassenger> node = new QueueNode<>(nePassenger);
                queue.offer(node);
                queueToPrint.offer(node);
                msg = "Name: " + node.getValue().getName() + " with ID: " + node.getValue().getId()+
                        " WAS REGISTERED SUCCESSFULLY\n";
            }
        }else msg = "That ID is not registered on the database\n";
        return msg;
    }

    //Define el orden de llegada de los pasajeros
    private void defineHow() {
        for (int i = 0; i < ePassengers.size(); i++) {
            totalPassengers.add(ePassengers.get(i));
        }

        for (int i = 0; i < nePassengers.size(); i++) {
            totalPassengers.add(nePassengers.get(i));
        }

        Collections.sort(totalPassengers);
    }

    //Mostrarle al asistente el orden de ingreso de los pasajeros basado en lo seleccionado
    public String showOrderEntrance() {
        return showEntrance();
    }

    private String showEntrance() {
        String msg = "";
        ArrayList<Passenger> orderEntrance = totalPassengers;
        PriorityQueueNode neNode;
        PriorityQueueNode eNode;

        for(int i=0;i<nePassengers.size();i++){
            neNode = new PriorityQueueNode<>(nePassengers.get(i),calculateEntranceNEPassengers(nePassengers.get(i),i + 18));
            nePassengerEntrance.insert(neNode);
        }
        for(int i=0; i<ePassengers.size();i++){
            eNode = new PriorityQueueNode<>(ePassengers.get(i), calculateEntranceEPassengers(ePassengers.get(i), i));
            ePassengerEntrance.insert(eNode);
        }

        msg = "-----Entrance order-----\n" +
                "Executive/Disabled group\n" +
                "Please present yourself in the respective order\n\n";

        for (int i = 0; i < ePassengers.size(); i++) {
            EPassenger passenger = (EPassenger) ePassengerEntrance.extractMax();
            if(passenger.isPreference())
            msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat()+ " " + "DISCAPACIDAD" + " " + "miles: " + passenger.getMiles()+ "\n";
            else msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat()  + " " + "miles: " +passenger.getMiles()+ "\n";


        }

        msg += "------------------------\n" +
                "Economy group\n" +
                "Please present yourself in the respective order\n\n";

        for (int i = 0; i < nePassengers.size(); i++) {
            NEPassenger passenger = (NEPassenger) nePassengerEntrance.extractMax();

            msg += i + ") " +passenger.getName() + " " + passenger.getSeat() +  "\n";
        }

        return msg;
    }
    public int calculateEntranceNEPassengers(NEPassenger passenger, int arrival) {
        int x = 0;

        if(passenger.getSeat().charAt(1)=='4')x = -5000;
        else if(passenger.getSeat().charAt(1)=='5') x=20000;
        else if (passenger.getSeat().charAt(1)=='6') x=100000;
        else if (passenger.getSeat().charAt(1)=='7') x=150000;
        else if (passenger.getSeat().charAt(1)=='8') x=200000;
        else if (passenger.getSeat().charAt(1)=='9') x=250000;
        //ANA      A4    =  100000
        //VALENTINA  D7 = 50000 - 5000
        if(passenger.getSeat().charAt(0)=='A')x+= 10000;
        else if(passenger.getSeat().charAt(0)=='B') x+=5000;
        else if (passenger.getSeat().charAt(0)=='C') x+=-5000;
        else if (passenger.getSeat().charAt(0)=='D') x+=-5000;
        else if (passenger.getSeat().charAt(0)=='E') x+=5000;
        else if (passenger.getSeat().charAt(0)=='F') x+=10000;

        x -= arrival;

        return x;
    }

    public double calculateEntranceEPassengers(EPassenger passenger, int arrival) {
        double x = 0;

        if(passenger.getSeat().charAt(1)=='1')x = -5000;
        else if(passenger.getSeat().charAt(1)=='2') x= 20000;
        else if (passenger.getSeat().charAt(1)=='3') x= 100000;

        if(passenger.getSeat().charAt(0)=='A')x+= 10000;
        else if(passenger.getSeat().charAt(0)=='B') x+=5000;
        else if (passenger.getSeat().charAt(0)=='C') x+=-5000;
        else if (passenger.getSeat().charAt(0)=='D') x+=-5000;
        else if (passenger.getSeat().charAt(0)=='E') x+=5000;
        else if (passenger.getSeat().charAt(0)=='F') x+=10000;

        x -= arrival;
        x += passenger.getMiles();
        if (passenger.isPreference()) {
            x += 600000;
        }

        return x;
    }

    public String registerPassengerManually(String ID, Queue queue, Queue queueToPrint) {

        String msg;
        int op = 2;
        msg = registerPassengerManually(ID,queue,queueToPrint,2);

        return msg;

    }

    private String registerPassengerManually(String key,Queue queue, Queue queueToPrint, int x) {
        String msg;
        if(hashtable.containsKey(key)){
            Passenger passengerFounded = (Passenger) hashtable.search(key);
            if(passengerFounded.isExecutive()){
                EPassenger exPassenger = (EPassenger) passengerFounded;

                QueueNode<EPassenger> passengerQueueNode = new QueueNode<>(exPassenger);

                queue.offer(passengerQueueNode);
                queueToPrint.offer(passengerQueueNode);

                msg= "Name: " + passengerQueueNode.getValue().getName() + " with ID: " + passengerQueueNode.getValue().getId()+
                " WAS REGISTERED SUCCESSFULLY";
            }else{
                NEPassenger nePassenger = (NEPassenger) passengerFounded;
                QueueNode<NEPassenger> node = new QueueNode<>(nePassenger);
                queue.offer(node);
                queueToPrint.offer(node);
                msg= "Name: " + node.getValue().getName() + " with ID: " + node.getValue().getId()+
                " WAS REGISTERED SUCCESSFULLY";
            }
        }else msg = "That ID is not registered on the database";

        return msg;
    }

    //Mostrarle al asistente el orden de salida de los pasajeros
    public String  showOrderExit() {
        return showExit();
    }

    private String showExit() {
        String msg = "";
        ArrayList<Passenger> orderEntrance = totalPassengers;
        PriorityQueueNode neNodeE;
        PriorityQueueNode eNodeE;

        for(int i=0;i<nePassengers.size();i++){
            neNodeE = new PriorityQueueNode<>(nePassengers.get(i),calculateExitNEPassengers(nePassengers.get(i), i + 18));
            nePassengerEntrance.insert(neNodeE);
        }
        for(int i=0; i<ePassengers.size();i++){
            eNodeE = new PriorityQueueNode<>(ePassengers.get(i), calculateExitEPassengers(ePassengers.get(i), i));
            ePassengerEntrance.insert(eNodeE);
        }

        msg = "-----Exit order-----\n";

        for (int i = 0; i < ePassengers.size(); i++) {
            EPassenger passenger = (EPassenger) ePassengerEntrance.extractMax();
            if(passenger.isPreference()) {
                msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat() + "Presenta discapacidad" + "\n";

            }
            else {
                msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat() + "No presenta discapacidad"+"\n";

            }
        }

        for (int i = 0; i < nePassengers.size(); i++) {
            NEPassenger passenger = (NEPassenger) nePassengerEntrance.extractMax();
            msg += i + 19 + ". " + passenger.getName() + " " + passenger.getSeat() + "\n";
        }

        return msg;
    }

    public int calculateExitNEPassengers(NEPassenger passenger, int arrival){
        int x = 0;

        if(passenger.getSeat().charAt(1)=='4')x = 250000;
        else if(passenger.getSeat().charAt(1)=='5') x=200000;
        else if (passenger.getSeat().charAt(1)=='6') x=150000;
        else if (passenger.getSeat().charAt(1)=='7') x=100000;
        else if (passenger.getSeat().charAt(1)=='8') x=20000;
        else if (passenger.getSeat().charAt(1)=='9') x=-5000;
        //ANA      A4    =  100000
        //VALENTINA  D7 = 50000 - 5000
        if(passenger.getSeat().charAt(0)=='A')x+= -5000;
        else if(passenger.getSeat().charAt(0)=='B') x+=5000;
        else if (passenger.getSeat().charAt(0)=='C') x+=10000;
        else if (passenger.getSeat().charAt(0)=='D') x+=10000;
        else if (passenger.getSeat().charAt(0)=='E') x+=5000;
        else if (passenger.getSeat().charAt(0)=='F') x+=-5000;

        x -= arrival;

        return x;
    }

    public double calculateExitEPassengers(EPassenger passenger, int arrival) {
        double x = 0;

        if(passenger.getSeat().charAt(1)=='1')x = 100000;
        else if(passenger.getSeat().charAt(1)=='2') x= 20000;
        else if (passenger.getSeat().charAt(1)=='3') x= -5000;

        if(passenger.getSeat().charAt(0)=='A')x+= -5000;
        else if(passenger.getSeat().charAt(0)=='B') x+=5000;
        else if (passenger.getSeat().charAt(0)=='C') x+=10000;
        else if (passenger.getSeat().charAt(0)=='D') x+=10000;
        else if (passenger.getSeat().charAt(0)=='E') x+=5000;
        else if (passenger.getSeat().charAt(0)=='F') x+=-5000;

        x -= arrival;

        return x;
    }

    // Archivos para guardar la información de los pasajeros
    public void loadHashPassengerInfo() {
       loadHashInfo();
    }

    public void loadNEPassenger() throws IOException {
        File file = new File(pathNE);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String content = "";
        String line = "";
        while ((line = reader.readLine()) != null) {
            content += line + "\n";
        }
        Gson gson = new Gson();
        NEPassenger[] array = gson.fromJson(content, NEPassenger[].class);
        nePassengers.addAll(Arrays.asList(array));
        fis.close();
    }

    public void loadEPassenger() throws IOException {
        File file = new File(pathE);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String content = "";
        String line = "";
        while ((line = reader.readLine()) != null) {
            content += line + "\n";
        }
        Gson gson = new Gson();
        EPassenger[] array = gson.fromJson(content, EPassenger[].class);
        ePassengers.addAll(Arrays.asList(array));
        fis.close();
        File f = new File(folder);

    }

    public String printQueue(Queue queue, Queue queueToPrint){
        String msg = "";
        for (int i=0 ; i< queue.size();i++){
            msg += queueToPrint.peek() + "\n";
            queueToPrint.poll();
        }
        return msg;
    }
}