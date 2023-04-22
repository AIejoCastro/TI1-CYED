package Model;

public class EPassenger extends Passenger{

    private double miles;
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
}
