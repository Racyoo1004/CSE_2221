import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Yoojin Jeong
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */
    @Test
    public void testIsWitness_2_4() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(4);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    public void testIsWitness_4_33() {
        NaturalNumber w = new NaturalNumber2(4);
        NaturalNumber n = new NaturalNumber2(33);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /*
     * Tests of isPrime2
     */
    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void testIsPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void testIsPrime2_5() {
        NaturalNumber n = new NaturalNumber2(5);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void testIsPrime2_17() {
        NaturalNumber n = new NaturalNumber2(17);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void testIsPrime2_19() {
        NaturalNumber n = new NaturalNumber2(19);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void testIsPrime2_37() {
        NaturalNumber n = new NaturalNumber2(37);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    /*
     * Tests of generateNextLikelyPrime
     */
    @Test
    public void testgenerateNext_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber expected = new NaturalNumber2(3);
        CryptoUtilities.generateNextLikelyPrime(n);
        int result = n.compareTo(expected);
        assertEquals(result, 0);
    }

    @Test
    public void testgenerateNext_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber expected = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);
        int result = n.compareTo(expected);
        assertEquals(result, 0);
    }

    @Test
    public void testgenerateNext_21() {
        NaturalNumber n = new NaturalNumber2(21);
        NaturalNumber expected = new NaturalNumber2(23);
        CryptoUtilities.generateNextLikelyPrime(n);
        int result = n.compareTo(expected);
        assertEquals(result, 0);
    }
}
