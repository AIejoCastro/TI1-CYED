package Model;

import java.util.Comparator;

public class NEPassenger extends Passenger implements Comparable<Passenger> {


    public NEPassenger(String name, String id, String seat, double arrivalTime, boolean executive) {
        super(name, id, seat, arrivalTime, executive);
    }

    @Override
    public String toString() {
        String msg;
        msg = "Name: " + getName() + " " + "\n" +
                "Id: " + getId() + " " +"\n" +
                "Seat: " + getSeat() + " " +"\n" +
                "ArrivalTime: " + getArrivalTime() + " THE USER FOUNDED IS A NON EXECUTIVE PASSENGER";
        return msg;
    }



    @Override
    public int compareTo(Passenger o) {
        if(o.getArrivalTime()>arrivalTime){
            return -1;
        }else if(o.getArrivalTime()==arrivalTime){
            return 0;
        }else{
            return 1;
        }
    }
}
