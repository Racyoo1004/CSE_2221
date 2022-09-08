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
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        //set result as 0, initial value
        //set num1 and num2 as -1 which means that value is not assigned
        int result = 0;
        int num1 = -1;
        int num2 = -1;

        //check each child of exp to see if it has attribute value or another child
        for (int i = 0; i < exp.numberOfChildren(); i++) {
            //check if child has another child value
            if (exp.child(i).numberOfChildren() > 0) {
                //if num1 has a value, use recursion to get num2
                if (num1 != -1) {
                    num2 = evaluate(exp.child(i));
                } else {
                    num1 = evaluate(exp.child(i));
                }
                //check the name of the child and get attribute value
            } else if (exp.child(i).label().equals("number")) {
                if (num1 != -1) {
                    num2 = Integer
                            .parseInt(exp.child(i).attributeValue("value"));
                } else {
                    num1 = Integer
                            .parseInt(exp.child(i).attributeValue("value"));
                }
            }
        }

        //check the name of the tag and calculate with appropriate method
        if (exp.label().equals("plus")) {
            result = num1 + num2;
        } else if (exp.label().equals("minus")) {
            result = num1 - num2;
        } else if (exp.label().equals("times")) {
            result = num1 * num2;
        } else if (exp.label().equals("divide")) {
            //denominator cannot be 0 when dividing
            if (num2 == 0) {
                Reporter.fatalErrorToConsole("Cannot be divided by 0.");
            } else {
                result = num1 / num2;
            }
        }

        return result;
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
