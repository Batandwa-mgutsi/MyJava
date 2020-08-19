import java.util.Locale;
import java.util.Scanner;

//
// Program that dispays a conversion table for feet and inches to metres
// Batandwa Mgutsi
// MGTBAT001
// 19/08/2020

// Example:
//
//
// Enter the minimum number of feet (not less than 0):
// 5
// Enter the maximum number of feet (not more than 30):
// 9
//    |    0"    1"    2"    3"    4"    5"    6"    7"    8"    9"   10"   11"
// 5' | 1.524 1.549 1.575 1.600 1.626 1.651 1.676 1.702 1.727 1.753 1.778 1.803
// 6' | 1.829 1.854 1.880 1.905 1.930 1.956 1.981 2.007 2.032 2.057 2.083 2.108
// 7' | 2.134 2.159 2.184 2.210 2.235 2.261 2.286 2.311 2.337 2.362 2.388 2.413
// 8' | 2.438 2.464 2.489 2.515 2.540 2.565 2.591 2.616 2.642 2.667 2.692 2.718
// 9' | 2.743 2.769 2.794 2.819 2.845 2.870 2.896 2.921 2.946 2.972 2.997 3.023

public class ImperialMetric {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter the minimum number of feet (not less than 0):");
    int minFeet = keyboard.nextInt();
    System.out.println("Enter the maximum number of feet (not more than 30):");
    int maxFeet = keyboard.nextInt();

    if (maxFeet > 9)
      System.out.print(" ");
    System.out.print("   |    0\"    1\"    2\"    3\"    4\"    5\"    6\"    7\"    8\"    9\"   10\"   11\"");

    for (int feet = minFeet; feet <= maxFeet; feet++) {
      System.out.printf("\n%d' %s|", feet, maxFeet > 9 && feet < 10 ? " " : "");
      for (int inches = 0; inches <= 11; inches++) {
        System.out.printf(Locale.ROOT, "%6.3f", (feet * 12 + inches) * 0.0254);
      }
    }

    keyboard.close();
  }
}