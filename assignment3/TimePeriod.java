
/**
 * A TimePeriod is a Duration range. It has an inclusive lower bound, i, and an
 * exlusive upper bound, u. A Duration, d, falls within the range if i<=d<u.
 */
public class TimePeriod {
  private Duration lowerBound;
  private Duration upperBound;

  public TimePeriod(Duration lowerBound, Duration upperBound) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  /**
   * The lower bound for this time period.
   */
  public Duration lowerBound() {
    return this.lowerBound;
  }

  /**
   * The upper bound for this time period.
   */
  public Duration upperBound() {
    return this.upperBound;
  }

  /**
   * Returns true if, and only if, the given `duration` falls within this time
   * period i.e if this.lowerBound() <= duration < this.upperBound(). False is
   * returned otherwise.
   */
  public boolean includes(Duration duration) {
    final int leftComparison = this.lowerBound.compareTo(duration);
    final int rightComparison = duration.compareTo(this.upperBound);

    return (leftComparison <= 0) && (rightComparison < 0);
  }

  /**
   * Returns true if, and only if, this time period precedes the `other` time
   * period. i.e if this.upperBound() <= other.lowerBound(). False is returned
   * otherwise.
   */
  public boolean precedes(TimePeriod other) {
    return this.upperBound.compareTo(other.lowerBound) <= 0;
  }

  /**
   * Returns true if, and only if, this time period is adjacent to the `other`
   * time period. i.e if this.upperBound() is equal to `other.lowerBound(), or
   * this.lowerBound() is equal to `other.upperBound()`.
   */
  public boolean adjacent(TimePeriod other) {
    return (this.upperBound.compareTo(other.lowerBound) == 0) || (this.lowerBound.compareTo(other.upperBound) == 0);
  }

  /**
   * Returns a String representation of this TimePeriod object in the form:
   * "[<duration> .. <duration>]"
   */
  public String toString() {
    final String lowerBoundString = Duration.format(this.lowerBound, "hour", "minute");
    final String upperBoundString = Duration.format(this.upperBound, "hour", "minute");
    return String.format("[%s .. %s]", lowerBoundString, upperBoundString);
  }
}
