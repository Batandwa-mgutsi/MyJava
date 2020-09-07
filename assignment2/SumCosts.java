import java.util.Scanner;

import org.junit.Test;

/**
 * Program to calculate the sum of entered amounts.
 * 
 * The user has to enter D or Done to print out the sum.
 */
public class SumCosts {
  public static void main(String[] args) {
    final Scanner keyboard = new Scanner(System.in);
    final Currency rand = new Currency("R", "ZAR", 100);

    Money sum = new Money("R0", rand);
    boolean quit = false;
    do {
      System.out.println("Enter an amount or '[D]one' to print the sum and quit:");
      final String input = keyboard.nextLine();

      if (input.toLowerCase().equals("d") || input.toLowerCase().equals("done")) {
        quit = true;
      } else if (input.trim().length() != 0) {
        final Money amount = new Money(input, rand);
        sum = sum.add(amount);
      }
    } while (!quit);

    System.out.println(sum.toString());
    keyboard.close();
  }

  @Test
  public void test() {

  }
}
