import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Yoojin Jeong
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        //set boolean to check value existence
        boolean value = true;
        NaturalNumber num1 = new NaturalNumber2();
        NaturalNumber num2 = new NaturalNumber2();

        //check each child of exp to see if it has attribute value or another child
        for (int i = 0; i < exp.numberOfChildren(); i++) {
            //check if child has another child value
            if (exp.child(i).numberOfChildren() > 0) {
                //if there is a value, use recursion to get num2
                if (!value) {
                    num2 = evaluate(exp.child(i));
                } else {
                    num1 = evaluate(exp.child(i));
                    value = false;
                }
                //check the name of the child and get attribute value
            } else if (exp.child(i).label().equals("number")) {
                if (!value) {
                    num2 = new NaturalNumber2(Integer
                            .parseInt(exp.child(i).attributeValue("value")));
                } else {
                    num1 = new NaturalNumber2(Integer
                            .parseInt(exp.child(i).attributeValue("value")));
                    value = false;
                }
            }
        }

        //check the name of the tag and calculate using proper method.
        if (exp.label().equals("plus")) {
            num1.add(num2);
        } else if (exp.label().equals("minus")) {
            num1.subtract(num2);
        } else if (exp.label().equals("times")) {
            num1.multiply(num2);
        } else if (exp.label().equals("divide")) {
            //denominator cannot be 0 when dividing
            if (num2.isZero()) {
                Reporter.fatalErrorToConsole("Cannot be divided by 0.");
            } else {
                num1.divide(num2);
            }
        }

        return num1;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
