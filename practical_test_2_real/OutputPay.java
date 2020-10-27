import java.util.HashMap;
import java.util.Map;

public class OutputPay {
  final Currency ZAR = new Currency("R", "ZAR", 100);

  final Date startDate;
  final Money amountPerPiece;
  final int target;

  final HashMap<Date, Integer> logs = new HashMap<>();

  public OutputPay(Date startDate, Money amountPerPiece, int target) {
    this.startDate = startDate;
    this.amountPerPiece = amountPerPiece;
    this.target = target;
  }

  public Period period() {
    Date endDate = startDate;

    for (Map.Entry<Date, Integer> entry : logs.entrySet()) {
      if (entry.getKey().compareTo(endDate) > 0) {
        endDate = entry.getKey();
      }
    }

    return new Period(startDate, endDate);
  }

  public void logOutput(int piecesCount, Date newEndDate) {
    this.logs.put(newEndDate, piecesCount);
  }

  public Money amount() {
    int piecesCount = 0;

    for (Map.Entry<Date, Integer> entry : logs.entrySet()) {
      piecesCount += entry.getValue();
    }

    if (piecesCount <= target)
      return new Money("R0", ZAR);

    return amountPerPiece.multiply((double) piecesCount - target);
  }
}
