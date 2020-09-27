/**
 * A `TariffTable` records parking tariffs for a pay-to-stay car park.
 */
public class TariffTable {

  ParkingTariff[] parkingTariffs;
  int nextInsertIndex = 0;

  /**
   * Creates a `TariffTable` with the given maximum number of entries.
   */
  public TariffTable(int maxSize) {
    parkingTariffs = new ParkingTariff[maxSize];
  }

  /**
   * Adds the tariff for the given period to the table. The period must directly
   * follow, and be adjacent to, that for the previous tariff entered.
   * 
   * If the period does not follow or is not adjacent then an
   * {@code IllegalArgumentException}
   */
  public void addTariff(TimePeriod period, Money tariff) {
    if (nextInsertIndex > 0) {
      // Get the last non null element to use as our previous element
      ParkingTariff prev = parkingTariffs[nextInsertIndex - 1];
      if (!prev.timePeriod().precedes(period)) {
        throw new IllegalArgumentException("TimePeriod:addTariff(): precondition not met.");
      } else if (!prev.timePeriod().adjacent(period)) {
        throw new IllegalArgumentException("Tariff periods must be adjacent.");
      }
    }

    final ParkingTariff insert = new ParkingTariff(period, tariff);
    parkingTariffs[nextInsertIndex++] = insert;
  }

  /**
   * Returns the tariff for the given length of stay.
   * 
   * This is the tariff assigned to the {@code ParkingTariff} instance whose
   * `includes(Duration)` method returns true.
   */
  public Money getTariff(Duration lengthOfStay) {
    for (int index = 0; index < nextInsertIndex; index++) {
      if (parkingTariffs[index] != null && parkingTariffs[index].timePeriodIncludes(lengthOfStay))
        return parkingTariffs[index].tariff();
    }

    return null;
  }

  /**
   * Returns a String representation of this `TariffTable` instance in the form:
   * 
   * <pre>
   * <timePeriod_0> 
   * ... 
   * <timePeriod_n>
   * </pre>
   * 
   * An empty String is returned when there is no tariffs entered.
   */
  @Override
  public String toString() {
    final StringBuilder out = new StringBuilder();

    for (int index = 0; index < nextInsertIndex; index++) {
      if (parkingTariffs[index] != null)
        out.append(parkingTariffs[index].toString() + "\n");
    }

    return out.toString().trim();
  }
}
