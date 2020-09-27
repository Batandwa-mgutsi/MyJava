import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;

@DisplayName("TeriffTable class")
@RunWith(Enclosed.class)
public class TestOfTeriffTable {

  final static Duration DurationTenMinutes = new Duration("minute", 10);
  final static Duration DurationTwelveMinutes = new Duration("minute", 12);
  final static Duration DurationFifteenMinutes = new Duration("minute", 15);
  final static Duration DurationTwentyMinutes = new Duration("minute", 20);

  final static TimePeriod TimePeriodZeroToTenMinutes = new TimePeriod(Duration.ZERO, DurationTenMinutes);
  final static TimePeriod TimePeriodTenToFifteenMinutes = new TimePeriod(DurationTenMinutes, DurationFifteenMinutes);
  final static TimePeriod TimePeriodFifteenToTwentyMinutes = new TimePeriod(DurationFifteenMinutes,
      DurationTwentyMinutes);

  final static Currency StubRandCurrency = new Currency("R", "ZAR", 100);
  final static Money MoneyThreeRand = new Money("R3", StubRandCurrency);
  final static Money MoneyFourRand = new Money("R4", StubRandCurrency);
  final static Money MoneyFiveRand = new Money("R5", StubRandCurrency);

  @Nested
  @DisplayName("public void addTariff(TimePeriod period, Money tariff)")
  @RunWith(Enclosed.class)
  public static class AddTeriff {

    @Nested
    public static class WhenTableIsInitiallyEmpty {
      @Test
      public void shouldReturnWithoutThrowing() {
        final TariffTable instance = new TariffTable(5);
        assertDoesNotThrow(() -> {
          instance.addTariff(TimePeriodTenToFifteenMinutes, MoneyThreeRand);
        });
      }
    }

    @Nested
    @RunWith(Enclosed.class)
    public static class WhenTableIsNotEmpty {

      @Nested
      public static class WhenPreviousTariffPeriodDoesNotProceedGivenTariffPeriod {

        @Test
        public void shouldThrowIllegalArgumentExceptionIfTheyAreNotAdjacent() {
          final TariffTable instance = new TariffTable(5);
          instance.addTariff(TimePeriodFifteenToTwentyMinutes, MoneyThreeRand);

          Exception e = assertThrows(IllegalArgumentException.class, () -> {
            instance.addTariff(TimePeriodZeroToTenMinutes, MoneyThreeRand);
          });

          assertEquals("TimePeriod:addTariff(): precondition not met.", e.getMessage());
        }

        @Test
        public void shouldThrowIllegalArgumentExceptionIfTheyAreAdjacent() {
          final TariffTable instance = new TariffTable(5);
          instance.addTariff(TimePeriodTenToFifteenMinutes, MoneyThreeRand);

          Exception e = assertThrows(IllegalArgumentException.class, () -> {
            instance.addTariff(TimePeriodZeroToTenMinutes, MoneyThreeRand);
          });

          assertEquals("TimePeriod:addTariff(): precondition not met.", e.getMessage());
        }
      }

      @Nested
      public static class WhenPreviousTariffPeriodProceedsGivenTariffPeriod {

        @Test
        public void shouldThrowIllegalArgumentExceptionIfTheyAreNotAdjacent() {
          final TariffTable instance = new TariffTable(5);
          instance.addTariff(TimePeriodZeroToTenMinutes, MoneyThreeRand);

          Exception e = assertThrows(IllegalArgumentException.class, () -> {
            instance.addTariff(TimePeriodFifteenToTwentyMinutes, MoneyThreeRand);
          });

          assertEquals("TimePeriod:addTariff(): precondition not met.", e.getMessage());
        }

        @Test
        public void shouldNotThrowIfTheyAreAdjacent() {
          final TariffTable instance = new TariffTable(5);
          instance.addTariff(TimePeriodZeroToTenMinutes, MoneyThreeRand);

          assertDoesNotThrow(() -> {
            instance.addTariff(TimePeriodTenToFifteenMinutes, MoneyThreeRand);
          });
        }
      }
    }
  }

  @Nested
  @DisplayName("public Money getTariff(Duration lengthOfStay)")
  public static class GetTariff {

    @Test
    public void shouldReturnNullWhenTheTableIsEmpty() {
      final TariffTable instance = new TariffTable(5);
      assertNull(instance.getTariff(DurationTenMinutes));
    }

    @Test
    public void shouldReturnNullWhenNoneOfTheEnteredTariffPeriodsContainTheGivenLenghtOfStayDuration() {
      final TariffTable instance = new TariffTable(5);
      instance.addTariff(TimePeriodZeroToTenMinutes, MoneyThreeRand);
      instance.addTariff(TimePeriodTenToFifteenMinutes, MoneyThreeRand);

      assertNull(instance.getTariff(DurationTwentyMinutes));
    }

    @Test
    public void shouldReturnTheFirstPeriodTariffContainingTheGivenLenghtOfStay() {
      final TariffTable instance = new TariffTable(5);
      instance.addTariff(TimePeriodZeroToTenMinutes, MoneyThreeRand);
      instance.addTariff(TimePeriodTenToFifteenMinutes, MoneyFourRand);
      instance.addTariff(TimePeriodFifteenToTwentyMinutes, MoneyFiveRand);

      assertEquals(MoneyFourRand, instance.getTariff(DurationTwelveMinutes));
    }
  }

  @Nested
  @DisplayName("public String toString()")
  public static class ToString {

    final TimePeriod mockTimePeriod1 = new TimePeriod(Duration.ZERO, DurationTenMinutes) {
      @Override
      public String toString() {
        return "ThisIsMockTimePeriodOne";
      }
    };

    final Money mockMoney1 = new Money("R4", StubRandCurrency) {
      @Override
      public String toString() {
        return "ThisIsMockMoneyOne";
      }
    };

    final TimePeriod mockTimePeriod2 = new TimePeriod(DurationTenMinutes, DurationFifteenMinutes) {
      @Override
      public String toString() {
        return "ThisIsMockTimePeriodTwo";
      }
    };

    final Money mockMoney2 = new Money("R4", StubRandCurrency) {
      @Override
      public String toString() {
        return "ThisIsMockMoneyTwo";
      }
    };

    final TimePeriod mockTimePeriod3 = new TimePeriod(DurationFifteenMinutes, DurationTwentyMinutes) {
      @Override
      public String toString() {
        return "ThisIsMockTimePeriodThree";
      }
    };

    final Money mockMoney3 = new Money("R4", StubRandCurrency) {
      @Override
      public String toString() {
        return "ThisIsMockMoneyThree";
      }
    };

    @Test
    public void shouldReturnEmptyStringIfTheTableIsEmpty() {
      final TariffTable instance = new TariffTable(5);
      assertEquals("", instance.toString());
    }

    @Test
    @DisplayName("Should return the correct string representation in the form: <timePeriod_0>\n<timePeriod_1>\n...<timePeriod_n>\n")
    public void shouldReturnCorrectStringRepresentation() {
      final TariffTable instance = new TariffTable(5);
      instance.addTariff(mockTimePeriod1, mockMoney1);
      instance.addTariff(mockTimePeriod2, mockMoney2);
      instance.addTariff(mockTimePeriod3, mockMoney3);

      assertEquals(
          "ThisIsMockTimePeriodOne : ThisIsMockMoneyOne\nThisIsMockTimePeriodTwo : ThisIsMockMoneyTwo\nThisIsMockTimePeriodThree : ThisIsMockMoneyThree\n",
          instance.toString());
    }
  }
}
