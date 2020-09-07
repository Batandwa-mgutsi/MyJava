import java.util.ArrayList;
import java.util.List;

/**
 * A Register stores a collectionof Tickets. A Ticket may be retrieved given its
 * ID.
 */
public class Register {

  private List<Ticket> tickets = new ArrayList<Ticket>();

  /**
   * Stores the given ticket in the register.
   */
  public void add(Ticket ticket) {
    tickets.add(ticket);
  }

  /**
   * Determine whether a ticket with the given ID is in the collection.
   */
  public boolean contains(String ticketID) {
    for (Ticket ticket : tickets) {
      if (ticket.ID().equals(ticketID))
        return true;
    }

    return false;
  }

  /**
   * Get the Ticket with the given ID from the collection.
   * 
   * The ticket will be removed from the current list of tickets.
   */
  public Ticket retrieve(String ticketID) {
    int ticketIndex = -1;
    for (int index = 0; index < tickets.size(); index++) {
      if (tickets.get(index).ID().equals(ticketID)) {
        ticketIndex = index;
        break;
      }
    }

    if (ticketIndex >= 0) {
      return tickets.remove(ticketIndex);
    } else {
      return null;
    }
  }
}
