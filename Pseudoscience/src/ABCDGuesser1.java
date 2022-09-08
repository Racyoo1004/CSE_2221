import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Calculate the four numbers based on Jager Formula to find the closest set of
 * numbers of exponents to a number user provided. Prompt the user to input a
 * positive number (constant) and four positive numbers higher than 1. Loop each
 * number of w,x,y,z to find sets of a,b,c,d which calculates the least
 * difference with the constant number user provided. Print out the result with
 * percentage of error and exponents.
 *
 * @author Yoojin Jeong
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        boolean validNum = false;
        double userNum = 0;

        //Loop until the user type a positive real number
        while (!validNum) {
            out.print("Enter a positive number to start: ");
            String userNumStr = in.nextLine();

            //Check either user typed a non-number or non-positive number
            if (!FormatChecker.canParseDouble(userNumStr)) {
                out.println("ERROR: You should enter a number!");
            } else {
                userNum = Double.parseDouble(userNumStr);
                if (userNum <= 0) {
                    out.println("ERROR: Number should be higher than 0");
                } else {
                    validNum = true;
                }
            }
        }
        return userNum;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        boolean validNum = false;
        double userNum = 0;

        //Loop until the user type a positive real number
        while (!validNum) {
            out.print("Enter a postive number other than 1 to start: ");
            String userNumStr = in.nextLine();

            //Check either user typed a non-number or non-positive number
            if (!FormatChecker.canParseDouble(userNumStr)) {
                out.println("ERROR: You should enter a number!");
            } else {
                userNum = Double.parseDouble(userNumStr);
                if (userNum <= 1.0) {
                    out.println("ERROR: Number should be higher than 1.0");
                } else {
                    validNum = true;
                }
            }
        }
        return userNum;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param userConstant
     *            user input of constant
     * @param w
     *            user input of w
     * @param x
     *            user input of x
     * @param y
     *            user input of y
     * @param z
     *            user input of z
     * @param out
     *            the output stream
     */
    private static void calculate(double userConstant, double w, double x,
            double y, double z, SimpleWriter out) {
        //Setup indexes of array, numbers of abcd, an array, and array length
        int a = 0, b = 0, c = 0, d = 0;
        double aNumber = 0, bNumber = 0, cNumber = 0, dNumber = 0;
        final double[] abcdArray = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2,
                -1.0 / 3, -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1.0, 2.0, 3.0,
                4.0, 5.0 };
        int arrayLength = abcdArray.length - 1;
        //Calculate with initial array indexes and set it as a least error array set
        double leastDifference = ((Math.pow(w, abcdArray[0]))
                * (Math.pow(x, abcdArray[0])) * (Math.pow(y, abcdArray[0]))
                * (Math.pow(z, abcdArray[0]))) - userConstant;
        //Loop until get the least different set of array
        while (d <= arrayLength) {
            while (c <= arrayLength) {
                while (b <= arrayLength) {
                    while (a <= arrayLength) {
                        double calculation = ((Math.pow(w, abcdArray[a]))
                                * (Math.pow(x, abcdArray[b]))
                                * (Math.pow(y, abcdArray[c]))
                                * (Math.pow(z, abcdArray[d]))) - userConstant;
                        //If new calculation has less difference,
                        //set it as a least different set
                        if (Math.abs(calculation) < Math.abs(leastDifference)) {
                            leastDifference = calculation;
                            aNumber = abcdArray[a];
                            bNumber = abcdArray[b];
                            cNumber = abcdArray[c];
                            dNumber = abcdArray[d];
                        }
                        a++;
                    }
                    b++;
                    a = 0;
                }
                c++;
                b = 0;
            }
            d++;
            c = 0;
        }
        //Print out the result of the calculation
        final double relativeError = (leastDifference / userConstant) * 100;
        //If the error is over 1%, print out the error result,
        //otherwise, print out the rest of the abcd exponents
        final double error = 0.1;
        if (relativeError > error) {
            out.println("There are no numbers within 1% of error");
        } else {
            out.println("Percentage of error: " + relativeError + "%");
            out.println("The exponent of the 1st number: " + aNumber);
            out.println("The exponent of the 2nd number: " + bNumber);
            out.println("The exponent of the 3rd number: " + cNumber);
            out.println("The exponent of the 4th number: " + dNumber);
        }
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

        //Prompt user to input their favorite numbers
        out.println("User's Constant Number");
        double userSingleNumber = getPositiveDouble(in, out);
        out.println("User's First Number");
        double userNumberW = getPositiveDoubleNotOne(in, out);
        out.println("User's Second Number");
        double userNumberX = getPositiveDoubleNotOne(in, out);
        out.println("User's Third Number");
        double userNumberY = getPositiveDoubleNotOne(in, out);
        out.println("User's Fourth Number");
        double userNumberZ = getPositiveDoubleNotOne(in, out);

        //Calculate and print out the result based on the number user inputed
        calculate(userSingleNumber, userNumberW, userNumberX, userNumberY,
                userNumberZ, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
