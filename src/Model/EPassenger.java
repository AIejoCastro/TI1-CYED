package Model;

import java.util.Comparator;

public class EPassenger extends Passenger implements Comparable<Passenger> {


    //Miles
    private double miles;
    //Preference
    private boolean preference;

    public EPassenger(String name, String id, String seat, double arrivalTime, boolean executive, double miles, boolean preference) {
        super(name, id, seat, arrivalTime, executive);
        this.miles = miles;
        this.preference = preference;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public boolean isPreference() {
        return preference;
    }

    public void setPreference(boolean preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
       String msg=  "Name: " + getName() + " " +"\n" +
                "Id: " + getId() + " " +"\n" +
                "Seat: " + getSeat() + " " +"\n" +
                "Preference: " + isPreference() + " " +"\n" +
                "ArrivalTime: " + getArrivalTime() + " " +"\n" +
                "Miles: " + getMiles() +
                "\nTHE USER FOUNDED IS EXECUTIVE";
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
