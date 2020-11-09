public class MenuItemFactory {

  public static abstract class MenuItem {
    final String itemNumber;
    final String size;

    public MenuItem(String itemNumber, String size) {
      this.itemNumber = itemNumber;
      this.size = size;
    }
  }

  public static MenuItem pizza(String itemNumber, String size, String base, boolean extraCheese, boolean extraGarlic) {
    return new Pizza(itemNumber, size, base, extraCheese, extraGarlic);
  }

  public static MenuItem curry(String itemNumber, String size, String curryType) {
    return new Curry(itemNumber, size, curryType);
  }

  public static MenuItem softDrink(String itemNumber, String size, String flavour, String containerType) {
    return new SoftDrink(itemNumber, size, flavour, containerType);
  }

  private static class Pizza extends MenuItem {
    final String base;
    final boolean extraCheese;
    final boolean extraGarlic;

    public Pizza(String itemNumber, String size, String base, boolean extraCheese, boolean extraGarlic) {
      super(itemNumber, size);
      this.base = base;
      this.extraCheese = extraCheese;
      this.extraGarlic = extraGarlic;
    }

    @Override
    public String toString() {
      return String.format("Pizza: %s, %s, %s, %s, %s", this.itemNumber, this.size, this.base,
          getYesNo(this.extraCheese), getYesNo(this.extraGarlic));
    }

    private String getYesNo(boolean choice) {
      return choice ? "Yes" : "No";
    }
  }

  private static class Curry extends MenuItem {
    final String curryType;

    public Curry(String itemNumber, String size, String curryType) {
      super(itemNumber, size);
      this.curryType = curryType;
    }

    @Override
    public String toString() {
      return String.format("Curry: %s, %s, %s", this.itemNumber, this.size, this.curryType);
    }
  }

  private static class SoftDrink extends MenuItem {
    final String flavour;
    final String containerType;

    public SoftDrink(String itemNumber, String size, String flavour, String containerType) {
      super(itemNumber, size);
      this.flavour = flavour;
      this.containerType = containerType;
    }

    @Override
    public String toString() {
      return String.format("Soft Drink: %s, %s, %s, %s", this.itemNumber, this.size, this.flavour, this.containerType);
    }
  }
}