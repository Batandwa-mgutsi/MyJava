import java.util.Scanner;

//
// Program that converts 12 hour times to 24 hour times, and 24 hour times to 12
// hour times.
// Batandwa Mgutsi
// MGTBAT001
// 16/08/2020

//  Examples:
//
//
// i. Enter a time ([h]h:mm [am|pm]):
// 00:00
// 12:00 am 
// 
// ii. Enter a time ([h]h:mm [am|pm]): 
// 12:00 am
// 00:00
// 
// iii. Enter a time ([h]h:mm [am|pm]):
// 4:00 pm
// 16:00
// 
// iv. Enter a time ([h]h:mm [am|pm]):
// 17:01
// 5:01 pm 
// 
// v. Enter a time ([h]h:mm [am|pm]): 
// 5:11 am
// 05:11
//

public class TimeConverter {

  public static void main(String[] args) {
    final Scanner keyboard = new Scanner(System.in);

    System.out.println("Enter a time ([h]h:mm [am|pm]):");
    final String time = keyboard.nextLine();
    final Clock clock = Clock.parseFromString(time);

    final String meridiamStr = clock.meridiem == Clock.Meridiem.AM ? "am"
        : (clock.meridiem == Clock.Meridiem.PM ? "pm" : "");

    // The hours for 24 times are printed with two digits, see examples above.
    final String hoursStr = clock.is24Hours() ? makeTwoDigits(clock.hours) : Integer.toString(clock.hours);
    System.out.printf("%s:%s %s", hoursStr, makeTwoDigits(clock.minutes), meridiamStr);

    keyboard.close();
  }

  private static String makeTwoDigits(int number) {
    // Credit:
    // https://stackoverflow.com/questions/14617865/how-do-get-numbers-to-display-as-two-digits-in-c
    return String.format("%02d", number);
  }
}