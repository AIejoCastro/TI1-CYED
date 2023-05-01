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
    private PriorityQueue<EPassenger,Integer> ePassengerEntrance;
    private PriorityQueue<NEPassenger,Integer> nePassengerEntrance;

    private int operationManually;
    public Queue<NEPassenger> nEpassengersQueue;
    public Queue<EPassenger> ePassengersQueue;
    public Queue<NEPassenger> nEpassengersExit;
    public Queue<EPassenger> ePassengersExit;
    private HashTable hashtable;

    public Airline(){
        ePassengers = new ArrayList<>();
        nePassengers = new ArrayList<>();
        totalPassengers= new ArrayList<>();
        hashtable = new HashTable();
        ePassengerEntrance = new PriorityQueue(18);
        nePassengerEntrance = new PriorityQueue(36);

    }
    //Buscar la información de un pasajero
    public String searchPassengerInformation(String key) {
        String msg = "";

        Passenger passengerFounded = (Passenger) hashtable.searchOnHashTable(key);
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

            hashtable.insertOnHashTable(nePassengers.get(i).getId(),nePassengers.get(i));

        }

        for(int j=0;j<ePassengers.size();j++){

            hashtable.insertOnHashTable(ePassengers.get(j).getId(),ePassengers.get(j));

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

    private String registerPassengerAutomatically(Queue queue, Queue queueToPrint, String key) {
        String msg;
        operationManually=0;
        if (hashtable.searchOnHashTable(key)!=null) {
            Passenger passengerFounded = (Passenger) hashtable.searchOnHashTable(key);
            if (passengerFounded.isExecutive()) {
                EPassenger exPassenger = (EPassenger) passengerFounded;
                Node <EPassenger> passengerQueueNode = new Node<>(exPassenger);
                queue.enqueue(passengerQueueNode);
                queueToPrint.enqueue(passengerQueueNode);
                msg = "Name: " + passengerQueueNode.getItem().getName() + " with ID: " + passengerQueueNode.getItem().getId()+
                        " WAS REGISTERED SUCCESSFULLY\n";
            } else {
                NEPassenger nePassenger = (NEPassenger) passengerFounded;
                Node <NEPassenger> node = new Node<>(nePassenger);
                queue.enqueue(node);
                queueToPrint.enqueue(node);
                msg = "Name: " + node.getItem().getName() + " with ID: " + node.getItem().getId()+
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
        String msg;
        PriorityQueueNode neNode;
        PriorityQueueNode eNode;

        if(operationManually==1){
            int c=0;

            while(!nEpassengersQueue.isEmpty()){
                c++;
                neNode = new PriorityQueueNode<>(nEpassengersQueue.getHead().getItem(),calculateEntranceNEPassengers( nEpassengersQueue.getHead().getItem(),c));
                nePassengerEntrance.insert(neNode);

                nEpassengersQueue.dequeue();
            }
            int b=0;
            while(!ePassengersQueue.isEmpty()){
                b++;
                eNode = new PriorityQueueNode<>(ePassengersQueue.getHead().getItem(), calculateEntranceEPassengers( ePassengersQueue.getHead().getItem(), b));
                ePassengerEntrance.insert(eNode);

                ePassengersQueue.dequeue();
            }

            msg = "-----Entrance order-----\n" +
                    "Executive/Disabled group\n" +
                    "Please present yourself in the respective order\n\n";
            int ePassengerEntranceInt = ePassengerEntrance.occupiedSize();
            for (int i = 0; i < ePassengerEntranceInt; i++) {
                EPassenger passenger =ePassengerEntrance.maximum().getElement();
                ePassengerEntrance.extractMax();
                if(passenger.isPreference())
                    msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat()+ " " + "DISCAPACIDAD" + " " + "miles: " + passenger.getMiles()+ "\n";
                else msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat()  + " " + "miles: " +passenger.getMiles()+ "\n";


            }

            msg += "------------------------\n" +
                    "Economy group\n" +
                    "Please present yourself in the respective order\n\n";

            int ePassengerEntranceint = nePassengerEntrance.occupiedSize();
            for (int i = 0; i < ePassengerEntranceint; i++) {
                NEPassenger passenger =nePassengerEntrance.maximum().getElement();
                nePassengerEntrance.extractMax();
                msg += i + ") " +passenger.getName() + " " + passenger.getSeat() + "\n";

            }


        }else{
            ePassengersQueue = new Queue<>();
            nEpassengersQueue = new Queue<>();

            for (NEPassenger nePassenger : nePassengers) {
                Node<NEPassenger> p = new Node<>(nePassenger);
                nEpassengersQueue.enqueue(p.getItem());
            }

            for (EPassenger ePassenger: ePassengers){
                Node<EPassenger> p = new Node<>(ePassenger);
                ePassengersQueue.enqueue(p.getItem());
            }
            int c = 0;
            while(!nEpassengersQueue.isEmpty()){
                c++;
                neNode = new PriorityQueueNode<>(nEpassengersQueue.getHead().getItem(),calculateEntranceNEPassengers( nEpassengersQueue.getHead().getItem(),c));
                nePassengerEntrance.insert(neNode);

                nEpassengersQueue.dequeue();
            }
            int b=0;
            while(!ePassengersQueue.isEmpty()){
                b++;
                eNode = new PriorityQueueNode<>(ePassengersQueue.getHead().getItem(), calculateEntranceEPassengers( ePassengersQueue.getHead().getItem(), b));
                ePassengerEntrance.insert(eNode);

                ePassengersQueue.dequeue();
            }

            msg = "-----Entrance order-----\n" +
                    "Executive/Disabled group\n" +
                    "Please present yourself in the respective order\n\n";
            int ePassengerEntranceInt = ePassengerEntrance.occupiedSize();
            for (int i = 0; i < ePassengerEntranceInt; i++) {
                EPassenger passenger =ePassengerEntrance.maximum().getElement();
                ePassengerEntrance.extractMax();
                if(passenger.isPreference())
                    msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat()+ " " + "DISCAPACIDAD" + " " + "miles: " + passenger.getMiles()+ "\n";
                else msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat()  + " " + "miles: " +passenger.getMiles()+ "\n";


            }

            msg += "------------------------\n" +
                    "Economy group\n" +
                    "Please present yourself in the respective order\n\n";

            int ePassengerEntranceint = nePassengerEntrance.occupiedSize();
            for (int i = 0; i < ePassengerEntranceint; i++) {
                NEPassenger passenger =nePassengerEntrance.maximum().getElement();
                nePassengerEntrance.extractMax();
                msg += i + ") " +passenger.getName() + " " + passenger.getSeat() + "\n";

            }

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

    public String registerPassengerManually(String ID, Queue queue, Queue queueToPrint, int x) {

        String msg;

        msg = registerPassengerManuallyP(ID,queue,queueToPrint,x);

        return msg;

    }

    private String registerPassengerManuallyP(String key, Queue queue, Queue queueToPrint, int x) {
        operationManually = 1;

        String msg;
        if(hashtable.searchOnHashTable(key)!=null){
            Passenger passengerFounded = (Passenger) hashtable.searchOnHashTable(key);
            if(passengerFounded.isExecutive()){
                EPassenger exPassenger = (EPassenger) passengerFounded;

                Node<EPassenger> passengerQueueNode = new Node<>(exPassenger);
                ePassengersExit.enqueue(passengerQueueNode.getItem());
                ePassengersQueue.enqueue(passengerQueueNode.getItem());
                queue.enqueue(passengerQueueNode);
                queueToPrint.enqueue(passengerQueueNode);

                msg= "Name: " + passengerQueueNode.getItem().getName() + " with ID: " + passengerQueueNode.getItem().getId()+
                " WAS REGISTERED SUCCESSFULLY";
            }else{
                NEPassenger nePassenger = (NEPassenger) passengerFounded;
                Node<NEPassenger> node = new Node<>(nePassenger);
                nEpassengersQueue.enqueue(node.getItem());
                nEpassengersExit.enqueue(node.getItem());
                queue.enqueue(node);
                queueToPrint.enqueue(node);
                msg= "Name: " + node.getItem().getName() + " with ID: " + node.getItem().getId()+
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

        PriorityQueueNode neNodeE;
        PriorityQueueNode eNodeE;

        if(operationManually==1){

            int nEPassengersQueueInt = nEpassengersExit.size();

            int ePassengersQueueInt = ePassengersExit.size();

            for(int i=0;i<nEPassengersQueueInt;i++){
                neNodeE = new PriorityQueueNode<>(nEpassengersExit.getHead().getItem(),calculateExitNEPassengers(nEpassengersExit.getHead().getItem(), i + 18));
                nePassengerEntrance.insert(neNodeE);
                nEpassengersExit.dequeue();
            }

            for(int i=0; i<ePassengersQueueInt;i++){
                eNodeE = new PriorityQueueNode<>(ePassengersExit.getHead().getItem(), calculateExitEPassengers(ePassengersExit.getHead().getItem(), i));
                ePassengerEntrance.insert(eNodeE);
                ePassengersExit.dequeue();
            }

            msg = "-----Exit order-----\n";

            int passEExit = ePassengerEntrance.occupiedSize();
            int passNExit = nePassengerEntrance.occupiedSize();


            for (int i = 0; i < passEExit; i++) {
                EPassenger passenger =ePassengerEntrance.maximum().getElement();
                ePassengerEntrance.extractMax();
                if(passenger.isPreference()) {
                    msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat() + " " + " | Presenta discapacidad |" + "\n";

                }
                else {
                    msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat() + " " + " | No presenta discapacidad |"+"\n";

                }


            }
            msg += "-----Exit order non executive-----\n";

            for (int i = 0; i < passNExit; i++) {
                NEPassenger passenger =nePassengerEntrance.maximum().getElement();
                nePassengerEntrance.extractMax();
                msg += i+1 + ". " + passenger.getName() + " " + passenger.getSeat() +  " " + " | No presenta discapacidad | "+ "\n";

            }
        }else{
            ePassengersQueue = new Queue<>();
            nEpassengersQueue = new Queue<>();

            for (NEPassenger nePassenger : nePassengers) {
                Node<NEPassenger> p = new Node<>(nePassenger);
                nEpassengersQueue.enqueue(p.getItem());
            }
            int nEPassengersQueueInt = nEpassengersQueue.size();
            for (EPassenger ePassenger: ePassengers){
                Node<EPassenger> p = new Node<>(ePassenger);
                ePassengersQueue.enqueue(p.getItem());
            }
            int ePassengersQueueInt = ePassengersQueue.size();

            for(int i=0;i<nEPassengersQueueInt;i++){
                neNodeE = new PriorityQueueNode<>(nEpassengersQueue.getHead().getItem(),calculateExitNEPassengers(nEpassengersQueue.getHead().getItem(), i + 18));
                nePassengerEntrance.insert(neNodeE);
                nEpassengersQueue.dequeue();
            }

            for(int i=0; i<ePassengersQueueInt;i++){
                eNodeE = new PriorityQueueNode<>(ePassengersQueue.getHead().getItem(), calculateExitEPassengers(ePassengersQueue.getHead().getItem(), i));
                ePassengerEntrance.insert(eNodeE);
                ePassengersQueue.dequeue();
            }

            msg = "-----Exit order-----\n";

            for (int i = 0; i < ePassengersQueueInt; i++) {
                EPassenger passenger =ePassengerEntrance.maximum().getElement();
                ePassengerEntrance.extractMax();
                if(passenger.isPreference()) {
                    msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat() + " Presenta discapacidad" + "\n";

                }
                else {
                    msg += i + 1 + ". " + passenger.getName() + " " + passenger.getSeat() + " No presenta discapacidad"+"\n";

                }
            }

            for (int i = 0; i < nEPassengersQueueInt; i++) {
                NEPassenger passenger =nePassengerEntrance.maximum().getElement();
                nePassengerEntrance.extractMax();
                msg += i + 19 + ". " + passenger.getName() + " " + passenger.getSeat() + "\n";
            }
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
                msg += queueToPrint.getHead().getItem() + "\n";

                queueToPrint.dequeue();
            }
            return msg;
        }
}