
public class Clock {
  public enum Meridiem {
    None, AM, PM,
  };

  final int hours;
  final int minutes;
  final Meridiem meridiem;

  public Clock(final int _hours, final int _minutes, final Meridiem _meridiem) {
    this.hours = _hours;
    this.minutes = _minutes;
    this.meridiem = _meridiem;
  }

  public boolean is24Hours() {
    return this.meridiem == Meridiem.None;
  }

  @Override
  public String toString() {
    return String.format("Clock -> %d:%d %s", hours, minutes, meridiem);
  }

  /**
   * Produces a `Clock` instance from the given clock string.
   * 
   * 24 hours clocks have no meridiem.
   * 
   * @param _source is the source string. Accepted format is: [h]h:mm [am|pm]]
   * @return Clock instance
   */
  public static Clock parseFromString(final String _source) {
    // Using algorithm derived from:
    // https://onlineconversion.com/date_12-24_hour.htm

    final String source = _source.trim();

    // Separate the hours and minutes, and the meridiem-if give.
    final String[] units = source.split("[:\\s]");
    final int hours = Integer.parseInt(units[0].trim());
    final int minutes = Integer.parseInt(units[1].trim());
    final Meridiem meridiem = units.length > 2
        ? (units[2].trim().equals("am") ? Meridiem.AM : (units[2].trim().equals("pm") ? Meridiem.PM : Meridiem.None))
        : Meridiem.None;

    if (meridiem != Meridiem.None) {
      // We are parsing a 12 hour clock to 24 hour clock
      if (meridiem == Meridiem.AM && hours == 12) {
        return new Clock(0, minutes, Meridiem.None);
      } else if (meridiem == Meridiem.PM && hours >= 1 && hours <= 11) {
        return new Clock(hours + 12, minutes, Meridiem.None);
      } else {
        return new Clock(hours, minutes, Meridiem.None);
      }
    } else {
      // We are parsing a 24 hour clock to 24 hour clock
      if (hours == 0) {
        return new Clock(12, minutes, Meridiem.AM);
      } else if (hours >= 13 && hours <= 23) {
        return new Clock(hours - 12, minutes, Meridiem.PM);
      } else {
        return new Clock(hours, minutes, hours == 12 ? Meridiem.PM : Meridiem.AM);
      }
    }
  }

  /**
   * TESTS
   * 
   * NB: Assertions are disabled in Java by default, enable them with -ea
   * parameter to 'java'
   */
  public static void main(String[] args) {
    // 12 hr - 24 hr tests
    Clock clock = Clock.parseFromString("12:00 am");
    assert clock.hours == 0;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.None;
    assert clock.is24Hours() == true;

    clock = Clock.parseFromString("1:00 am");
    assert clock.hours == 1;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.None;
    assert clock.is24Hours() == true;

    clock = Clock.parseFromString("11:00 am");
    assert clock.hours == 11;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.None;
    assert clock.is24Hours() == true;

    clock = Clock.parseFromString("12:00 pm");
    assert clock.hours == 12;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.None;
    assert clock.is24Hours() == true;

    clock = Clock.parseFromString("10:00 pm");
    assert clock.hours == 22;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.None;
    assert clock.is24Hours() == true;

    clock = Clock.parseFromString("11:00 pm");
    assert clock.hours == 23;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.None;
    assert clock.is24Hours() == true;

    // 24 hr - 12 hour tests
    clock = Clock.parseFromString("00:00");
    assert clock.hours == 12;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.AM;
    assert clock.is24Hours() == false;

    clock = Clock.parseFromString("01:00");
    assert clock.hours == 1;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.AM;
    assert clock.is24Hours() == false;

    clock = Clock.parseFromString("11:00");
    assert clock.hours == 11;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.AM;
    assert clock.is24Hours() == false;

    clock = Clock.parseFromString("12:00");
    assert clock.hours == 12;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.PM;
    assert clock.is24Hours() == false;

    clock = Clock.parseFromString("22:00");
    assert clock.hours == 10;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.PM;
    assert clock.is24Hours() == false;

    clock = Clock.parseFromString("23:00");
    assert clock.hours == 11;
    assert clock.minutes == 0;
    assert clock.meridiem == Meridiem.PM;
    assert clock.is24Hours() == false;

    System.out.println("All tests passed!");
  }

}
