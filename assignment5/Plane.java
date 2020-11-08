public class Plane extends Vehicle {
  final String manufacturer;
  final int model;

  public Plane(String colour, int passengers, String manufacturer, int model) {
    super(colour, passengers);
    this.manufacturer = manufacturer;
    this.model = model;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(" %s %d", manufacturer, model);
  }
}
