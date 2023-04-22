package Model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Airline {

    static String folder = "data";
    static String pathNE = "data/dataNEPassenger.txt";
    static String pathE = "data/dataEPassenger.txt";
    ArrayList<NEPassenger> nePassengers = new ArrayList<>();
    ArrayList<EPassenger> ePassengers = new ArrayList<>();

    public ArrayList<NEPassenger> getNePassengers() {
        return nePassengers;
    }

    public void setNePassengers(ArrayList<NEPassenger> nePassengers) {
        this.nePassengers = nePassengers;
    }

    public ArrayList<EPassenger> getePassengers() {
        return ePassengers;
    }

    public void setePassengers(ArrayList<EPassenger> ePassengers) {
        this.ePassengers = ePassengers;
    }

    public Airline(){


    }

    //Buscar la información de un pasajero
    public String searchPassengerInformation(){
        return null;
    }
    //Registrar la llegada de un pasajero
    public boolean registerPassenger(){
        boolean succesfull = false;

        if(registerPassengerB()) {
            succesfull = true;
        }else{ succesfull= false;}



        return succesfull;

    }

    private boolean registerPassengerB() {
        return false;
    }

    //Mostrarle al asistente el orden de ingreso de los pasajeros basado en lo seleccionado
    public String showOrderEntrance(){
        return null;
    }

    //Mostrarle al asistente el orden de salida de los pasajeros
    public String  showOrderExit(){
        return null;
    }


    public void loadNEpassenger() throws IOException{
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

    public void loadEpassenger() throws IOException{
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
}
