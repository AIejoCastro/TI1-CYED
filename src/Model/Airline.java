package Model;


import java.io.*;
import java.util.Arrays;
import com.google.gson.Gson;
import structures.Hashtable;
import structures.HashtableNode;
import structures.Queue;
import structures.QueueNode;
import structures.Stack;
import structures.StackNode;

import java.util.ArrayList;
import java.util.Collections;


public class Airline {

    static String folder = "data";
    static String pathNE = "data/dataNEPassenger.txt";
    static String pathE = "data/dataEPassenger.txt";
    ArrayList<NEPassenger> nePassengers;
    ArrayList<EPassenger> ePassengers;
    ArrayList<Passenger> totalPassengers;



    private Hashtable hashtable;


    public Airline(){
        ePassengers = new ArrayList<>();
        nePassengers = new ArrayList<>();
        totalPassengers= new ArrayList<>();
        hashtable = new Hashtable(54);

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
                    "Miles: " + passengerIsExecutive.getMiles() + " THE USER FOUNDED IS EXECUTIVE";
        } else {
            NEPassenger passengerIsNotExecutive = (NEPassenger) passengerFounded;
            msg = "Name: " + passengerIsNotExecutive.getName() + " " + "\n" +
                    "Id: " + passengerIsNotExecutive.getId() + " " +"\n" +
                    "Seat: " + passengerIsNotExecutive.getSeat() + " " +"\n" +
                    "ArrivalTime: " + passengerIsNotExecutive.getArrivalTime() + " THE USER FOUNDED IS A NON EXECUTIVE PASSENGER";

        }
    }
        return msg;

    }
    //Registra la información de los pasajeros en tablas hash
    private void loadHashInfo(){

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
    public String registerPassenger(int option)
    {
            defineHow();
            return printPassengers(totalPassengers);

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

    private String printPassengers(ArrayList<Passenger> arrayListTP){
        String msg ="--Orden de llegada--" + "\n";

        for (int i=0; i<arrayListTP.size();i++){

            if(arrayListTP.get(i).isExecutive() == true){
                msg+= arrayListTP.get(i).getName() + " " +"ID: " +arrayListTP.get(i).getId() + " " + "| EXECUTIVE |";
                msg+= "\n";
            }else {
                msg+= arrayListTP.get(i).getName() + " " +"ID: " +arrayListTP.get(i).getId() + " " + "| NON EXECUTIVE |";
                msg+= "\n";
            }

        }
        return msg;
    }


    //Define el orden de llegada de los pasajeros

    public void defineHow(){

        for(int i=0; i<nePassengers.size();i++){
            totalPassengers.add(nePassengers.get(i));
        }

        for(int j=0;j<ePassengers.size();j++){
            totalPassengers.add(ePassengers.get(j));
        }
        Collections.sort(totalPassengers);
    }


    //Mostrarle al asistente el orden de ingreso de los pasajeros basado en lo seleccionado
    public String showOrderEntrance()
    {
        return null;
    }

    //Mostrarle al asistente el orden de salida de los pasajeros
    public String  showOrderExit()
    {
        return null;
    }

    // Archivos para guardar la información de los pasajeros


    public void loadHashPassengerInfo()
    {

       loadHashInfo();
    }


    public void loadNEpassenger() throws IOException
    {
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

    public void loadEpassenger() throws IOException
    {
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
