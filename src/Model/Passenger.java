package Model;

public abstract class Passenger {

    private String name;
    private String id;
    private String seat;
    private double arrivalTime;
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

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isExecutive() {
        return executive;
    }

    public void setExecutive(boolean executive) {
        this.executive = executive;
    }
}
