public class Planet {

  final String name;
  final double mass;
  final double density;

  public Planet(String name, double mass, double density) {
    this.name = name;
    this.mass = mass;
    this.density = density;
  }

  @Override
  public String toString() {
    return String.format("%s has mass %s and density %s", name, mass, density);
  }

  @Override
  public boolean equals(Object other) {
    return (other instanceof Planet) && ((Planet) other).name.equals(this.name);
  }
}
