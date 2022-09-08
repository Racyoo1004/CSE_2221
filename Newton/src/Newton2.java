import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Repeatedly ask the user whether they want to calculate another square root.
 * Error of the square root of the input has to be within 0.01%. Since division
 * by 0 is undefined, it should not be calculated.
 *
 * @author Yoojin Jeong
 *
 */
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            number except 0 to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        final double error = 0.0001;
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

        boolean again = true;

        while (again) {
            //ask user to input a number to calculate
            out.print("Enter a number to calculate square root: ");
            double x = in.nextDouble();

            //dividing by zero is undefined
            if (x == 0) {
                out.println("undefined");
            } else {
                out.println(sqrt(x));
            }

            out.print("Do you want to continue ('y' if you want to): ");
            String answer = in.nextLine();
            if (!answer.equals("y")) {
                //if 'y', ask to enter a number again
                again = false;
            }
            /*
             * Close input and output streams
             */
        }
        in.close();
        out.close();
    }
}
