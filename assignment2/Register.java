
/**
 * A Register stores a collectionof Tickets. A Ticket may be retrieved given its
 * ID.
 */
public class Register {

  private Ticket[] tickets = new Ticket[100];
  private int numTickets = 0;

  /**
   * Stores the given ticket in the register.
   */
  public void add(Ticket ticket) {
    tickets[numTickets++] = ticket;
  }

  /**
   * Determine whether a ticket with the given ID is in the collection.
   */
  public boolean contains(String ticketID) {
    for (int index = 0; index < numTickets; index++) {
      if (tickets[index].ID().equals(ticketID))
        return true;
    }

    return false;
  }

  /**
   * Get the Ticket with the given ID from the collection.
   * 
   * Tickets can be retrieved many times.
   */
  public Ticket retrieve(String ticketID) {
    int ticketIndex = -1;
    for (int index = 0; index < numTickets; index++) {
      if (tickets[index].ID().equals(ticketID)) {
        ticketIndex = index;
        break;
      }
    }

    if (ticketIndex >= 0) {
      return tickets[ticketIndex];
    } else {
      return null;
    }
  }
}
