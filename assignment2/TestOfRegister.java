import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Tests for the Register class.
 */
@DisplayName("Ticket test")
public class TestOfRegister {
  @Test
  @DisplayName("Should NOT contain a ticket that was never added with add()")
  public void testNotContainsNotAddedTicket() {
    final Register register = new Register();
    assertFalse(register.contains("notExists"));
  }

  @Test
  @DisplayName("Should contain a ticket that was added with add()")
  public void testContainsAddedTicket() {
    final Ticket ticket = new Ticket(new Time("13:45"), "tsr5");
    final Register register = new Register();
    register.add(ticket);
    assertTrue(register.contains("tsr5"));
  }

  @Test
  @DisplayName("Should retrieve the correct ticket when it does contain it")
  public void testRetrieveCorrectTicket() {
    final Ticket ticket1 = new Ticket(new Time("13:45"), "tsr5");
    final Ticket ticket2 = new Ticket(new Time("14:45"), "tsr19");
    final Register register = new Register();
    register.add(ticket1);
    register.add(ticket2);

    assertEquals(ticket2, register.retrieve("tsr19"));
  }

  @Test
  @DisplayName("Should allow retrieving the correct ticket more than once when it does contain it")
  public void testRetrieveCorrectTicketManyTimes() {
    final Ticket ticket1 = new Ticket(new Time("13:45"), "tsr5");
    final Ticket ticket2 = new Ticket(new Time("14:45"), "tsr19");
    final Register register = new Register();
    register.add(ticket1);
    register.add(ticket2);

    // Get the ticket more than once
    assertEquals(ticket2, register.retrieve("tsr19"));
    assertEquals(ticket2, register.retrieve("tsr19"));
  }

  @Test
  @DisplayName("Should STILL contain a ticket after it has been retrieved")
  public void testContainsRetrievedTicket() {
    final Ticket ticket = new Ticket(new Time("13:45"), "tsr5");
    final Register register = new Register();
    register.add(ticket);
    register.retrieve("tsr5");

    assertTrue(register.contains("tsr5"));
  }
}
