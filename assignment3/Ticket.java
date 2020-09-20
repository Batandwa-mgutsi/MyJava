/**
 * A Ticket object represents a car park ticket. it has a unique ID and time of
 * issue (24 hour clock).
 */
public class Ticket {
  private String id;
  private Time issueTime;

  public Ticket(Time currentTime, String ID) {
    this.id = ID;
    this.issueTime = currentTime;
  }

  /**
   * Obtain this Ticket's ID
   */
  public String ID() {
    return id;
  }

  /**
   * Obtain this ticket's age, i.e the issue time subtracted from the given time
   */
  public Duration age(Time currentTime) {
    return currentTime.subtract(issueTime);
  }

  /**
   * obtain a String representation of this Ticket object in the form:
   * "Ticket[id="ddddd", time="hh:mm:ss"]".
   */
  public String toString() {
    return String.format("Ticket[id=%s, time=%s]", this.id, this.issueTime);
  }
}
