import java.text.NumberFormat;
import java.util.*;


public class StatementPrinter {
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";
  

  public String print(Invoice invoice, HashMap<String, Play> plays,Customer customer) {
    int totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder result = new StringBuilder();
    int cus=customer.soldepoints; //get the solde points of the customer
    result.append(String.format("Statement for %s\n", invoice.customer.name));
    result.append(String.format("Your points %d \n", cus));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
       
      CalculClass calcul = new CalculClass();
      double thisAmount = calcul.calcul(perf, play,customer);

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (Play.playType.COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
      totalAmount += thisAmount;

    }
    if (cus>150){
      cus=cus-150;
      totalAmount-=15;
    }
    cus=cus+volumeCredits;
    //customer.setsoldepoints(cus); //set the solde points of the customer
    result.append(String.format("Amount owed is : %s\n", frmt.format(totalAmount)));
    result.append(String.format("You earned : %s points\n", volumeCredits));
    result.append(String.format("Points left : %s points\n", cus));
    return result.toString();
  }

  
  public String toHTML(Invoice invoice,HashMap<String, Play> plays,Customer customer) {

    int totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder result = new StringBuilder();
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    int cus=customer.soldepoints; //get the solde points of the customer 


    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      CalculClass calcul = new CalculClass();

      double thisAmount = calcul.calcul(perf, play,customer);
      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (Play.playType.COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      //result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
      totalAmount += thisAmount;

     
    }
     result.append(String.format("<p><strong>Invoice</strong> </p>\n ") );
     result.append(String.format("<p><strong>Client :</strong> %s </p>\n", invoice.customer.name));

    if (cus>150){
      cus=cus-150;
      totalAmount-=15;
    }
    cus=cus+volumeCredits;
    //customer.setsoldepoints(cus); //set the solde points of the customer
    
    // Génération du détail de la facture au format HTML
      result.append("<style>");
      result.append("table { border-collapse: collapse; width: 50%; }");
      result.append("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
      result.append("</style>");
      result.append("<table>\n");
      result.append("<tr><th>Play</th><th>Seats</th><th>Amount</th></tr>\n");
      double thisAmount = 0;
      for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        CalculClass calcul = new CalculClass();
        thisAmount = calcul.calcul(perf, play, customer);
        result.append(String.format("  <tr><td>%s</td><td>%s</td><td>%s</td></tr>\n", play.name, perf.audience, frmt.format(thisAmount)));
      }
      result.append(String.format("<tr><td><strong> Amount owed is</strong></td><td>%s</td></tr>\n", frmt.format(totalAmount)));
      result.append(String.format("<tr><td><strong>You earned</strong></td><td> %d points</td></tr>\n", volumeCredits));
      result.append(String.format("<tr><td><strong>Points left</strong></td><td> %d points</td></tr>\n", cus));
      result.append("</table>\n");
    
    
    result.append(" Payment is required under 30 days, we can brake your knees if you dont do so\n ");
    result.append("</body></html>\n");

    return result.toString();
}

 


}
