import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyHtml;


public class StatementPrinterTests {
  Customer customer = new Customer("BigCo", 1522, 200);

    @Test
    void exampleStatementPrinttotext() {
    
        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", Play.playType.TRAGEDY));
        plays.put("as-like",  new Play("As You Like It",Play.playType.COMEDY));
        plays.put("othello",  new Play("Othello", Play.playType.TRAGEDY));

        Invoice invoice = new Invoice(customer.name, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays,customer);
        verify(result);
    }
      @Test
    void exampleStatementtohtml() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", Play.playType.TRAGEDY));
        plays.put("as-like",  new Play("As You Like It",Play.playType.COMEDY));
        plays.put("othello",  new Play("Othello", Play.playType.TRAGEDY));

        Invoice invoice = new Invoice(customer.name, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.toHTML(invoice, plays,customer);

        verifyHtml(result);
    }


}
