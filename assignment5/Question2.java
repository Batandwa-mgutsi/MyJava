import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Question2 {

  public static void main(String[] args) {
    final Scanner keyboard = new Scanner(System.in);
    final List<MenuItemFactory.MenuItem> menuItems = new LinkedList<>();

    System.out.println("Welcome to Great International Food Court");

    String userCommand = "";
    do {
      userCommand = askStringInput("MENU: add (P)izza, add (C)urry, add (S)oft drink, (D)elete, (L)ist, (Q)uit",
          keyboard).toLowerCase();

      if (userCommand.equals("p") || userCommand.equals("c") || userCommand.equals("s")) {
        menuItems.add(inputMenuItem(userCommand, keyboard));
        System.out.println("Done");
      } else if (userCommand.equals("d")) {
        final String itemNumber = askStringInput("Enter the menu item number", keyboard);

        if (menuItems.removeIf(item -> item.itemNumber.equals(itemNumber)))
          System.out.println("Done");
        else
          System.out.println("Not found");
      } else if (userCommand.equals("l")) {
        listMenuItems(menuItems);
        System.out.println("Done");
      } else {
        userCommand = "q";
      }
    } while (!userCommand.toLowerCase().equals("q"));
  }

  private static void listMenuItems(List<MenuItemFactory.MenuItem> menuItems) {
    for (MenuItemFactory.MenuItem menuItem : menuItems) {
      System.out.println(menuItem);
    }
  }

  private static MenuItemFactory.MenuItem inputMenuItem(String type, Scanner keyboard) {
    // p, c, or s
    type = type.toLowerCase();

    final String itemNumber = askStringInput("Enter the menu item number", keyboard);
    final String size = askStringInput("Enter the size", keyboard);

    if (type.equals("p")) {
      // Pizza
      final String base = askStringInput("Enter the base", keyboard);
      final boolean extraCheese = askStringInput("Enter extra cheese", keyboard).toLowerCase().equals("yes");
      final boolean extraGarlic = askStringInput("Enter extra garlic", keyboard).toLowerCase().equals("yes");

      return MenuItemFactory.pizza(itemNumber, size, base, extraCheese, extraGarlic);
    } else if (type.equals("c")) {
      // Curry
      final String curryType = askStringInput("Enter the curry type", keyboard);

      return MenuItemFactory.curry(itemNumber, size, curryType);
    } else {
      // Soft Drink
      final String flavour = askStringInput("Enter the flavour", keyboard);
      final String containerType = askStringInput("Enter whether it is a bottle or can", keyboard);

      return MenuItemFactory.softDrink(itemNumber, size, flavour, containerType);
    }
  }

  private static String askStringInput(String caption, Scanner keyboard) {
    System.out.println(caption);
    return keyboard.nextLine().trim();
  }
}
