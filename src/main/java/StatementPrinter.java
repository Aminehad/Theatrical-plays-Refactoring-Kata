import java.text.NumberFormat;
import java.util.*;

import Play.playType;


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
      if (Play.playType.COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
      totalAmount += thisAmount;

    }
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result.toString();
  }
/* 
  public String toHTML(Invoice invoice,HashMap<String, Play> plays) {

    int totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder result = new StringBuilder();
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
  
    // Génération du détail de la facture au format HTML
    result.append("<table>\n");
    result.append("<tr><th>Play</th><th>Seats</th><th>Amount</th></tr>\n");
    for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        result.append(String.format("  <tr><td>%s</td><td>%s</td><td>%s</td></tr>\n", play.name, perf.audience, frmt.format(totalAmount)));
    }
    result.append("</table>\n");
    result.append(String.format("<p>Amount owed is %s</p>\n", frmt.format(totalAmount)));
    result.append(String.format("<p>You earned %d credits</p>\n", volumeCredits));
    result.append("</body></html>\n");

    return result.toString();
}
*/
 


}
