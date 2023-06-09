package Model;


import java.util.Comparator;

public abstract class Passenger implements Comparable<Passenger> {

    private String name;
    private String id;
    private String seat;
    public double arrivalTime;
    private boolean executive;
    private double entrance;

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

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public double getEntrance() {
        return entrance;
    }

    public void setEntrance(double entrance) {
        this.entrance = entrance;
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