import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";

  public String print(Invoice invoice, HashMap<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder result = new StringBuilder();
    result.append(String.format("Statement for %s\n", invoice.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      Double thisAmount = 0.0;// un double pour les montants

      switch (play.type) {
        case TRAGEDY:
          thisAmount = 400.00;// 400.0 au lieu de 40000
          if (perf.audience > 30) {
            thisAmount += 10 * (perf.audience - 30);// 10 au lieu de 1000
          }
          break;
        case COMEDY:
          thisAmount = 300.00;// 300.0 au lieu de 30000
          if (perf.audience > 20) {
            thisAmount += 100 + 5.0 * (perf.audience - 20); // 100 au lieu de 10000 et 5.0 au lieu de 500
          }
          thisAmount += 3.00 * perf.audience; // 3.0 au lieu de 300
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
      totalAmount += thisAmount;
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result.toString();
  }

}
