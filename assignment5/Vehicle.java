public class Vehicle {
    private int numPassengers;
    private String colour;

    Vehicle(String colour, int passengers) {
        this.numPassengers = passengers;
        this.colour = colour;
    }

    public String toString() {
        return colour + " " + numPassengers + " passengers";
    }
}
