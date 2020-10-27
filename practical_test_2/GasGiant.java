public class GasGiant extends Planet {
  final boolean hasRings;

  public GasGiant(String name, double mass, double density, boolean hasRings) {
    super(name, mass, density);
    this.hasRings = hasRings;
  }

  @Override
  public String toString() {
    return String.format("Gas giant %s has mass %s and density %s, and %s", name, mass, density,
        hasRings ? "has rings" : "does not have rings");
  }
}
