import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/**
 * Tests for the Ticket class.
 */
@DisplayName("Ticket test should")
public class TestOfTicket {
  Ticket instance = new Ticket(new Time("13:45"), "dummyId");

  @Test
  @DisplayName("Return the correct id")
  public void testReturnsCorrectId() {
    assertEquals("dummyId", instance.ID());
  }

  @Test
  @DisplayName("Return the correct age of the ticket")
  public void testReturnsCorrectAge() {
    assertEquals(new Duration("minutes", 60), instance.age(new Time("14:45")));
  }

  @Test
  @DisplayName("Return the correct string representation of the ticket")
  public void testReturnsCorrectStringRepresentation() {
    final String expected = "Ticket[id=dummyId, time=13:45:00]";
    assertEquals(expected, instance.toString());
  }
}
