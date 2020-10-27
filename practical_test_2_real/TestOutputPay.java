import java.util.Scanner;

/**
 * CSC1016S Practical Test 2 code given to students
 *
 * @version 1.0
 */
public class TestOutputPay {

    private TestOutputPay() {}
    
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);

        OutputPay outputPay = null;
        final Currency currency = new Currency("R", "ZAR", 100);
        
        while(input.hasNextLine()) {
            final Scanner parse = new Scanner(input.nextLine());
            if (parse.hasNext()) {
                String token = parse.next();
                if (token.equals("Construct")) {
                    parse.next().equals("OutputPay");
                    parse.next().equals("starting");
                    final Date startDate = new Date(parse.next());
                    parse.next().equals("with");
                    final Money pieceRate = new Money(parse.next(), currency);
                    parse.next().equals("and");
                    final int target = parse.nextInt();
                    outputPay = new OutputPay(startDate, pieceRate, target);
                }
                else if (token.equals("Call")) {
                    token = parse.next();
                    if (token.equals("period()")) {
                        System.out.println(outputPay.period());
                    }
                    else if (token.equals("amount()")) {
                        System.out.println(outputPay.amount());
                    }
                    else if (token.equals("logOutput()")) {
                        parse.next().equals("withj");
                        final int pieces = parse.nextInt();
                        parse.next().equals("with");
                        final Date date = new Date(parse.next());
                        outputPay.logOutput(pieces, date);
                    }
                    else {
                        System.out.printf("Method \"%s\" not recognised.\n", token);
                    }
                }
               else if (token.equals("Quit")) {
                    return;
                }
                else {
                    System.out.printf("Token \"%s\" not recognised.\n", token);
                }
            }
            System.out.println("Done");
        }         

    }

}
