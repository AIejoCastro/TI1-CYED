package Model;


import java.util.Comparator;

public abstract class Passenger implements Comparable<Passenger> {

    private String name;
    private String id;
    private String seat;
    public double arrivalTime;
    private boolean executive;

    public Passenger(String name, String id, String seat, double arrivalTime, boolean executive) {
        this.name = name;
        this.id = id;
        this.seat = seat;
        this.arrivalTime = arrivalTime;
        this.executive = executive;

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




    public boolean isExecutive() {
        return executive;
    }

    public void setExecutive(boolean executive) {
        this.executive = executive;
    }

}
