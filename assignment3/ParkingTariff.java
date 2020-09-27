
/**
 * A ParkingTariff stores a time period and the tariff (Money) a vehicle will be
 * charged if its duration of stay falls in the period.
 */
public class ParkingTariff {

  private TimePeriod timePeriod;
  private Money tariff;

  public ParkingTariff(TimePeriod timePeriod, Money tariff) {
    this.timePeriod = timePeriod;
    this.tariff = tariff;
  }

  /**
   * The Time Period for this {@code ParkingTariff} instance.
   */
  public TimePeriod timePeriod() {
    return this.timePeriod;
  }

  /**
   * The tariff for this {@code ParkingTariff} instance.
   */
  public Money tariff() {
    return this.tariff;
  }

  /**
   * Returns true if, and only if, the given `duration` falls within this
   * ParkingTariff time period i.e if {@code this.timePeriod.includes(duration)}
   * returns true. False is returned otherwise.
   */
  public boolean timePeriodIncludes(Duration duration) {
    return this.timePeriod.includes(duration);
  }

  /**
   * Returns a String representation of this `ParkingTariff` object in the form:
   * <timePeriod>:<tariff>
   */
  @Override
  public String toString() {
    return String.format("%s : %s", this.timePeriod.toString(), this.tariff.toString());
  }
}
