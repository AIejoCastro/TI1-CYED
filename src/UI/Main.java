package UI;
import java.util.*;
public class Main {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Main m = new Main();
        m.load();
        m.menu();
    }


    public void menu(){
    int option = 0;
       do{

           System.out.println("Welcome to the Avianca Airline assistant" + "\n" +
           "1.Información de un pasajero" + "\n" +
           "2.Registrar la llegada de un pasajero a la sala" + "\n" +
           "3.Mostrar el orden en el que deben ingresar los pasajeros" + "\n" +
           "4.Mostrar el orden en el que deben salir los pasajeros" + "\n" +
            "0. Salir");

           option = sc.nextInt();

           switch (option) {
               case 1:
                   break;
               case 2:
                   break;
               case 3:
                   break;
               case 4:
                   break;
               case 0:
                   System.out.println("Saliendo del programa...");
                   break;
               default:
                   System.out.println("Opción incorrecta");
                   break;

           }


       }while(option!=0);


    }

    public void load(){


    }
}


