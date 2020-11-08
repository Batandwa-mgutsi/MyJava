public class Car extends Vehicle {
  final int doors;

  public Car(String colour, int passengers, int doors) {
    super(colour, passengers);
    this.doors = doors;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(" %d doors", this.doors);
  }
}