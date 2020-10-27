public class RockyPlanet extends Planet {
  final int numberOfMoons;

  public RockyPlanet(String name, double mass, double density, int numberOfMoons) {
    super(name, mass, density);
    this.numberOfMoons = numberOfMoons;
  }

  @Override
  public String toString() {
    return String.format("Rocky planet %s has mass %s and density %s, and number of moons is %d", name, mass, density,
        numberOfMoons);
  }
}
