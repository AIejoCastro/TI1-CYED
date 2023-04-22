package Model;

import java.io.*;
import java.util.Arrays;

public class Airline {
    private String folder = "data";
    private String path = "data/passengerInfo.txt";
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


    public void loadInfo() {


    }
}
