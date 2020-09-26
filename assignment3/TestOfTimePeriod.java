import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;

@DisplayName("TestOfTimePeriod")
@RunWith(Enclosed.class)
public class TestOfTimePeriod {

  final static Duration DurationTenMinutes = new Duration("minute", 10);
  final static Duration DurationFifteenMinutes = new Duration("minute", 15);
  final static Duration DurationTwentyMinutes = new Duration("minute", 20);

  @Nested
  @DisplayName("lowerBound() and UpperBound()")
  public static class Getters {
    @Test
    @DisplayName("Should return the lower and upper bound fields passed to the costructor respectively")
    public void lowerAndUpperBoundGettersShouldReturnCorrectFields() {
      final TimePeriod instance = new TimePeriod(null, Duration.ZERO);
      assertEquals(null, instance.lowerBound());
      assertEquals(Duration.ZERO, instance.upperBound());

      final TimePeriod timePeriod2 = new TimePeriod(Duration.ZERO, null);
      assertEquals(Duration.ZERO, timePeriod2.lowerBound());
      assertEquals(null, timePeriod2.upperBound());
    }
  }

  @Nested
  @DisplayName("public boolean includes(Duration duration)")
  public static class Includes {

    @Test
    public void returnsFalseIfDurationLessThanLowerBound() {
      final TimePeriod instance = new TimePeriod(DurationFifteenMinutes, DurationTwentyMinutes);
      assertFalse(instance.includes(DurationTenMinutes));
    }

    @Test
    public void returnsTrueIfDurationEqualsLowerBound() {
      final TimePeriod instance = new TimePeriod(DurationFifteenMinutes, DurationTwentyMinutes);
      assertTrue(instance.includes(DurationFifteenMinutes));
    }

    @Test
    public void returnsTrueIfDurationGreaterThanLowerBoundButLessThanUpperBound() {
      final TimePeriod instance = new TimePeriod(DurationTenMinutes, DurationTwentyMinutes);
      assertTrue(instance.includes(DurationFifteenMinutes));
    }

    @Test
    public void returnsFalseIfDurationEqualsUpperBoundBound() {
      final TimePeriod instance = new TimePeriod(DurationFifteenMinutes, DurationTwentyMinutes);
      assertFalse(instance.includes(DurationTwentyMinutes));
    }

    @Test
    public void returnsFalseIfDurationGreaterThanUpperBound() {
      final TimePeriod instance = new TimePeriod(DurationTenMinutes, DurationFifteenMinutes);
      assertFalse(instance.includes(DurationTwentyMinutes));
    }
  }

  @Nested
  @DisplayName("public boolean precedes(TimePeriod other)")
  public static class Precedes {

    @Test
    public void shouldReturnTrueIfThisUpperBoundLessThanOtherLowerBound() {
      final TimePeriod thiz = new TimePeriod(Duration.ZERO, DurationTenMinutes);
      final TimePeriod other = new TimePeriod(DurationFifteenMinutes, DurationTwentyMinutes);

      assertTrue(thiz.precedes(other));
    }

    @Test
    public void shouldReturnTrueIfThisUpperBoundEqualsOtherLowerBound() {
      final TimePeriod thiz = new TimePeriod(Duration.ZERO, DurationTenMinutes);
      final TimePeriod other = new TimePeriod(DurationTenMinutes, DurationTwentyMinutes);

      assertTrue(thiz.precedes(other));
    }

    @Test
    public void shouldReturnFalseIfThisUpperBoundGreaterThanOtherLowerBound() {
      final TimePeriod thiz = new TimePeriod(Duration.ZERO, DurationFifteenMinutes);
      final TimePeriod other = new TimePeriod(DurationTenMinutes, DurationTwentyMinutes);

      assertFalse(thiz.precedes(other));
    }
  }

  @Nested
  @DisplayName("public boolean adjacent(TimePeriod other)")
  @RunWith(Enclosed.class)
  public static class Adjacent {

    @Nested
    public static class WhenThisLowerBoundIsNotEqualOtherUpperBound {

      @Test
      public void shouldReturnTrueIfThisUpperBoundEqualsOtherLowerBound() {
        final TimePeriod thiz = new TimePeriod(Duration.ZERO, DurationFifteenMinutes);
        final TimePeriod other = new TimePeriod(DurationFifteenMinutes, DurationTwentyMinutes);

        assertTrue(thiz.adjacent(other));
      }

      @Test
      public void shouldReturnFalseIfThisUpperBoundLowerThanOtherLowerBound() {
        final TimePeriod thiz = new TimePeriod(Duration.ZERO, DurationTenMinutes);
        final TimePeriod other = new TimePeriod(DurationFifteenMinutes, DurationTwentyMinutes);

        assertFalse(thiz.adjacent(other));
      }

      @Test
      public void shouldReturnFalseIfThisUpperBoundGreaterThanOtherLowerBound() {
        final TimePeriod thiz = new TimePeriod(Duration.ZERO, DurationFifteenMinutes);
        final TimePeriod other = new TimePeriod(DurationTenMinutes, DurationTwentyMinutes);

        assertFalse(thiz.adjacent(other));
      }
    }

    @Nested
    public static class WhenThisUpperBoundIsNotEqualOtherLowerBound {

      @Test
      public void shouldReturnTrueIfThisLowerBoundEqualsOtherUpperBound() {
        final TimePeriod thiz = new TimePeriod(DurationTenMinutes, DurationTwentyMinutes);
        final TimePeriod other = new TimePeriod(Duration.ZERO, DurationTenMinutes);

        assertTrue(thiz.adjacent(other));
      }

      @Test
      public void shouldReturnFalseIfThisLowerBoundLessThanOtherUpperBound() {
        final TimePeriod thiz = new TimePeriod(DurationTenMinutes, DurationTwentyMinutes);
        final TimePeriod other = new TimePeriod(Duration.ZERO, DurationFifteenMinutes);

        assertFalse(thiz.adjacent(other));
      }

      @Test
      public void shouldReturnFalseIfThisLowerBoundGreaterThanOtherUpperBound() {
        final TimePeriod thiz = new TimePeriod(DurationFifteenMinutes, DurationTwentyMinutes);
        final TimePeriod other = new TimePeriod(Duration.ZERO, DurationTenMinutes);

        assertFalse(thiz.adjacent(other));
      }
    }
  }

  @Nested
  @DisplayName("public String toString()")
  public static class ToString {

    @Test
    @DisplayName("Should return correct string representation in the form: \"[<duration> .. <duration>]\" ")
    public void shouldReturnCorrectStringRepresentation() {
      final TimePeriod instance = new TimePeriod(DurationTenMinutes, new Duration("minute", 90));
      assertEquals("[10 minutes .. 1 hour 30 minutes]", instance.toString());
    }

    @Test
    public void shouldStringifyZeroDurationToZeroMinutesNotSeconds() {
      final TimePeriod instance = new TimePeriod(Duration.ZERO, Duration.ZERO);
      assertEquals("[0 minutes .. 0 minutes]", instance.toString());
    }
  }
}
