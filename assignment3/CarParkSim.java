import java.util.Arrays;
import java.util.Scanner;

/**
 * The CarParkSim class contains the main car park simulation method. It creates
 * and manipulates the main objects, and handles user I/O.
 *
 * @author Stephan Jamieson and ...
 * @version 14/7/2019
 */
public class CarParkSim {
  static Scanner keyboard = new Scanner(System.in);
  static Register register = new Register();
  static Clock clock = new Clock(Time.MIDNIGHT);

  public static void main(final String[] args) {

    System.out.println("Car Park Simulator");
    // YOUR CODE HERE.
    System.out.printf("The current time is %s.\n", clock.examine().toString());
    System.out.println("Commands: advance {minutes}, arrive, depart, quit.");
    System.out.print(">");
    String userCommand = keyboard.nextLine().trim().toLowerCase();
    while (!userCommand.equals("quit")) {
      System.out.print(handleUserCommand(userCommand));
      System.out.print(">");
      userCommand = keyboard.nextLine();
    }
    System.out.println("Goodbye.");
  }

  /**
   * Handles the given user command and returns console output to be printed.
   * 
   * All returned console output ends with a newline.
   * 
   * @param userCommand is the command to handle, should be `advance`, `arrive`,
   *                    or `depart`.
   * 
   *                    arrive: Record the arrival of a vehicle and causes a
   *                    ticket to be issued.
   * 
   *                    depart: Records the depart of a vehicle and causes the
   *                    duration of the stay to be computed. `depart [id]`.
   * 
   *                    advance: Advances the simulated current time by the given
   *                    number of minutes. Time is simulated. Initially it is
   *                    midnight (00:00:00). `advance minutes`.
   */
  public static String handleUserCommand(String userCommand) {
    final String[] tokens = userCommand.trim().split(" ");
    final String mainCommad = tokens[0];
    String[] args = new String[] {};
    if (tokens.length > 1) {
      args = Arrays.copyOfRange(tokens, 1, tokens.length);
    }

    if (mainCommad.equals("advance")) {
      // Advance the clock, print the current time.
      clock.advance(new Duration("minute", Integer.parseInt(args[0])));
      return String.format("The current time is %s.\n", clock.examine().toString());
    } else if (mainCommad.equals("arrive")) {
      // Create a new ticket, add it to the register, print details of ticket issued.
      final Ticket ticket = new Ticket(clock.examine(), UIDGenerator.makeUID());
      register.add(ticket);
      return String.format("Ticket issued: %s.\n", ticket.toString());
    } else if (mainCommad.equals("depart")) {

      if (register.contains(args[0])) {
        final Ticket ticket = register.retrieve(args[0]);
        return String.format("Ticket details: %s.\nCurrent time: %s.\nDuration of stay: %s.\n", ticket.toString(),
            clock.examine().toString(), Duration.format(ticket.age(clock.examine()), "hour", "minute", "second"));
      } else {
        return "Invalid ticket ID.\n";
      }
    } else {
      return "That command is not recognised.\nCommands: advance <minutes>, arrive, depart, quit.\n";
    }
  }
}
