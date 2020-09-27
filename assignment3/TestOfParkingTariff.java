import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;

@DisplayName("ParkingTariff class")
@RunWith(Enclosed.class)
public class TestOfParkingTariff {

  final static Currency StubRandCurrency = new Currency("R", "ZAR", 100);
  final static Duration DurationTenMinutes = new Duration("minute", 10);
  final static Money MoneyThreeRand = new Money("R3", StubRandCurrency);
  final static TimePeriod TimePeriodZeroToTenMinutes = new TimePeriod(Duration.ZERO, DurationTenMinutes);

  @Nested
  @DisplayName("public Money tariff()")
  public static class GetTariff {

    @Test
    public void shouldReturnTheTariffPassedToTheConstructor() {
      final ParkingTariff instance = new ParkingTariff(null, null);
      assertNull(instance.tariff());

      final Money money = MoneyThreeRand;
      final ParkingTariff instance2 = new ParkingTariff(null, money);
      assertEquals(money, instance2.tariff());
    }
  }

  @Nested
  @DisplayName("public TimePeriod timePeriod()")
  public static class GetTimePeriod {

    @Test
    public void shouldReturnTheTimeTariffPassedToTheConstructor() {
      final ParkingTariff instance = new ParkingTariff(null, null);
      assertNull(instance.timePeriod());

      final TimePeriod timePeriod = TimePeriodZeroToTenMinutes;
      final ParkingTariff instance2 = new ParkingTariff(TimePeriodZeroToTenMinutes, null);
      assertEquals(timePeriod, instance2.timePeriod());
    }
  }

  @Nested
  @DisplayName("public boolean timePeriodIncludes(Duration duration)")
  public static class TimePeriodIncludes {
    final TimePeriod notIncludesMockTimePeriod = new TimePeriod(Duration.ZERO, DurationTenMinutes) {
      @Override
      public boolean includes(Duration duration) {
        return false;
      };
    };
    final TimePeriod includesMockTimePeriod = new TimePeriod(Duration.ZERO, DurationTenMinutes) {
      @Override
      public boolean includes(Duration duration) {
        return true;
      };
    };

    @Test
    public void shouldReturnTheValueReturnedByGivenTimePeriodIncludes() {
      final ParkingTariff instance = new ParkingTariff(notIncludesMockTimePeriod, null);
      assertEquals(false, instance.timePeriodIncludes(new Duration("minute", 10)));

      final ParkingTariff instance2 = new ParkingTariff(includesMockTimePeriod, null);
      assertEquals(true, instance2.timePeriodIncludes(new Duration("minute", 10)));
    }
  }

  @Nested
  @DisplayName("public String toString()")
  public static class ToString {

    final Money mockMoney = new Money("R4", StubRandCurrency) {
      @Override
      public String toString() {
        return "ThisIsMockMoney";
      }
    };
    final TimePeriod mockTimePeriod = new TimePeriod(Duration.ZERO, DurationTenMinutes) {
      @Override
      public String toString() {
        return "ThisIsMockTimePeriod";
      }
    };

    @Test
    @DisplayName("Should return the correct string representation in the form: <timePeriod> : <tariff>")
    public void shouldReturnCorrectStringRepresentation() {
      final ParkingTariff instance = new ParkingTariff(mockTimePeriod, mockMoney);
      assertEquals("ThisIsMockTimePeriod : ThisIsMockMoney", instance.toString());
    }
  }
}
