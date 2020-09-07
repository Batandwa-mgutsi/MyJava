import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test for the time and duration classes.
 */
public class TestOfTime {

  // Tests for the Time class

  @Test
  public void testTimeInitialValue() {
    Time time = new Time("13:45");
    assertEquals("Check that a time object does actually store the time value provided as a parameter during creating",
        "13:45:00", time.toString());
  }

  @Test
  public void testTimeSubtraction() {
    Time time1 = new Time("13:45");
    Time time2 = new Time("13:00");

    Duration diff = time1.subtract(time2);
    assertEquals("Check that subtracting an earlier TIme from a later Time produces a Duration of the correct length",
        45, diff.intValue("minutes"));

  }

  @Test
  public void testTimeSubtractionWithSelf() {
    Time time1 = new Time("13:45");

    Duration diff = time1.subtract(time1);
    assertEquals("Check that subtracting a Time from itself produces a zero duration", 0, diff.intValue());
  }

  // Tests for the Duration class intVallue method
  @Test
  public void acceptsMilliseconds() {
    final Duration duration = new Duration("milliseconds", 500);
    assertEquals("Check that the int value method works with a parameter of milliseconds", 500,
        duration.intValue("milliseconds"));
  }

  @Test
  public void acceptsSeconds() {
    final Duration duration = new Duration("seconds", 500);
    assertEquals("Check that the int value method works with a parameter of seconds", 500,
        duration.intValue("seconds"));
  }

  @Test
  public void acceptsMinutes() {
    final Duration duration = new Duration("minutes", 500);
    assertEquals("Check that the int value method works with a parameter of minutes", 500,
        duration.intValue("minutes"));
  }

  @Test
  public void acceptsHours() {
    final Duration duration = new Duration("hours", 500);
    assertEquals("Check that the int value method works with a parameter of hours", 500, duration.intValue("hours"));
  }
}
