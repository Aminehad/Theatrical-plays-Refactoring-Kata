import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyHtml;


public class StatementPrinterTests {

    @Test
    void exampleStatementPrinttotext() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", Play.playType.TRAGEDY));
        plays.put("as-like",  new Play("As You Like It",Play.playType.COMEDY));
        plays.put("othello",  new Play("Othello", Play.playType.TRAGEDY));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);
        verify(result);
    }
      @Test
    void exampleStatementtohtml() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", Play.playType.TRAGEDY));
        plays.put("as-like",  new Play("As You Like It",Play.playType.COMEDY));
        plays.put("othello",  new Play("Othello", Play.playType.TRAGEDY));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.toHTML(invoice, plays);

        verifyHtml(result);
    }


}
