package Model;

public class Passenger {

    //Passenger name
    private String name;

    //Passenger identification
    private String id;
      //Seat
    private String seat;
    //Arrival time
    private double arrivalTime;

    public Passenger(String name, String id, String seat,double arrivalTime) {
        this.name = name;
        this.id = id;
        this.seat = seat;
        this.arrivalTime= arrivalTime;
    }

    public String getName() {
        return name;
    }

    public double getArrivalTime(){
        return arrivalTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setArrivalTime(double arrivalTime){
        this.arrivalTime = arrivalTime;
    }
}
