import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;

@DisplayName("CarParkSim Class")
@RunWith(Enclosed.class)
public class TestOfCarParkSim {

  @Nested
  @DisplayName("handleUserCommand")
  public static class HandleUserCommand {
    @Test
    @DisplayName("Should return the unknown command error message on error")
    public void returnsErrorMessageOnUnknownCommand() {
      CarParkSim.register = new Register();
      CarParkSim.clock = new Clock(Time.MIDNIGHT);
      UIDGenerator.reset();

      final String userCommand = "unknown Command";
      final String expectedConsoleOut = "That command is not recognised.\nCommands: advance <minutes>, arrive, depart, quit.\n";
      assertEquals(expectedConsoleOut, CarParkSim.handleUserCommand(userCommand));
    }
  }

  @Nested
  @DisplayName("handleUserCommand(depart, {id})")
  public static class HandleUserCommandDepart {

    @Test
    @DisplayName("Should return 'Invalid ticket ID.' when requested to depart a ticket that was never recorded on arrival")
    public void departingWithoutArriving() {
      CarParkSim.register = new Register();
      CarParkSim.clock = new Clock(Time.MIDNIGHT);
      UIDGenerator.reset();

      final String userCommand = "depart 8000006";
      final String expectedConsoleOut = "Invalid ticket ID.\n";
      assertEquals(expectedConsoleOut, CarParkSim.handleUserCommand(userCommand));
    }

    @Test
    @DisplayName("Should return the correct ticket details, and duration of stay when departing with a correct ticket id")
    public void departingWithCorrectId() {
      CarParkSim.register = new Register();
      CarParkSim.clock = new Clock(Time.MIDNIGHT);
      UIDGenerator.reset();

      CarParkSim.handleUserCommand("arrive"); // id: 80000001, time: 00:00:00
      CarParkSim.handleUserCommand("arrive"); // id: 80000002, time: 00:00:00
      CarParkSim.handleUserCommand("arrive"); // id: 80000003, time: 00:00:00

      // We will ask for the 2nd ticket
      final String userCommand = "depart 80000002";
      final String expectedConsoleOut = "Ticket details: Ticket[id=80000002, time=00:00:00].\nCurrent time: 00:00:00.\nDuration of stay: 0 seconds.\n";
      assertEquals(expectedConsoleOut, CarParkSim.handleUserCommand(userCommand));
    }

    @Test
    @DisplayName("Should return the correct current time, and a duration of stay that is difference of the current time and arrival time")
    public void departingWithAccumulatedDuration() {
      CarParkSim.register = new Register();
      CarParkSim.clock = new Clock(Time.MIDNIGHT);
      UIDGenerator.reset();

      // Advance the clock by random minutes, in this case 10 - but any value less
      // than 60 will be fine
      CarParkSim.handleUserCommand("advance 10");

      CarParkSim.handleUserCommand("arrive"); // id: 80000001, time: 00:10:00
      CarParkSim.handleUserCommand("advance 20");

      final String userCommand = "depart 80000001";
      final String expectedConsoleOut = "Ticket details: Ticket[id=80000001, time=00:10:00].\nCurrent time: 00:30:00.\nDuration of stay: 20 minutes.\n";
      assertEquals(expectedConsoleOut, CarParkSim.handleUserCommand(userCommand));
    }
  }

  @Nested
  @DisplayName("handleUserCommand(arrive)")
  public static class HandleUserCommandArrive {
    @Test
    @DisplayName("It should return correct ticket ticket details in the format: Ticket issued: Ticket[id=..., time=hh:mm:ss].")
    public void shouldReturnCorrectTicketDetails() {
      CarParkSim.register = new Register();
      CarParkSim.clock = new Clock(Time.MIDNIGHT);
      UIDGenerator.reset();

      final String expectedConsoleOut = "Ticket issued: Ticket[id=80000001, time=00:00:00].\n";
      assertEquals(expectedConsoleOut, CarParkSim.handleUserCommand("arrive"));
    }
  }

  @Nested
  @DisplayName("handleUserCommand(advance)")
  public static class HandleUserCommandAdvance {
    @Test
    @DisplayName("It should return the correct time in format: The current time is hh:mm:ss.")
    public void shouldReturnCorrectCurrentTime() {
      CarParkSim.register = new Register();
      CarParkSim.clock = new Clock(Time.MIDNIGHT);
      UIDGenerator.reset();

      final String expectedConsoleOut = "The current time is 00:20:00.\n";
      assertEquals(expectedConsoleOut, CarParkSim.handleUserCommand("advance 20"));
    }
  }
}
