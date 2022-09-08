import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Repeatedly ask the user whether they want to calculate another square root.
 * Ask the user to input the value of epsilon. Calculate square root within the
 * error range(epsilon).
 *
 * @author Yoojin Jeong
 *
 */
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
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

        boolean again = true;

        while (again) {
            //get x and error from the user
            out.print("Enter a number to calculate square root: ");
            double x = in.nextDouble();
            out.print("Enter an epsilion value: ");
            double error = in.nextDouble();

            if (x == 0) {
                //dividing by zero is undefined
                out.println("undefined");
            } else {
                //print out a square root of x within the error
                out.println(sqrt(x, error));
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
