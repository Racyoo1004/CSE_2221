import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Ask the user for the input number and the epsilon value. Calculate a square
 * root of the number within the error range(epsilon). Interpret negative input
 * as meaning that it is time to quit.
 *
 * @author Yoojin Jeong
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error.
     *
     * @param x
     *            number except 0 to compute square root of
     * @param error
     *            to get square root of x within this percentage
     * @return estimate of square root
     */
    private static double sqrt(double x, double error) {
        //get x and error from the user
        double r = x;

        while (Math.abs(r * r - x) / x >= error) {
            //estimate closest square root within an error
            r = (r + x / r) / 2;
        }
        return r;
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

        int input = 0;
        while (input >= 0) {
            //get x and error from the user
            out.print("Enter a number: ");
            int newNum = in.nextInteger();
            out.print("Enter episilon value: ");
            double error = in.nextDouble();

            out.println("Square root: " + sqrt(newNum, error));
            out.print("Enter a value(Negative to quit): ");
            //if negative number, stop the loop
            input = in.nextInteger();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
