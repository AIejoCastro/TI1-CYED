package Model;

public class EPassenger extends Passenger{

    //Miles
    private double miles;
    //Preference
    private boolean preference;

    public EPassenger(String name, String id, String seat, double arrivalTime, double miles, boolean preference) {
        super(name, id, seat, arrivalTime);
        this.miles = miles;
        this.preference = preference;
    }
}
