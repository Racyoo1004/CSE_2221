import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 *
 * @author Yoojin Jeong
 *
 */
public class StringReassemblyTest {

    /**
     * Test method for
     * {@link StringReassembly#overlap (java.lang.String, java.lang.String, int)}.
     */
    @Test
    public void testOverLap() {
    }

    /**
     * Test method for
     * {@link StringReassembly#combination (java.lang.String, java.lang.String, int)}.
     */
    @Test
    public void testCombination() {
        // declare test variables
        String str1, str2, str3, str4, str5, str6;
        String result1, result2, result3;
        int overlap1, overlap2;
        final int overlap3 = 6;
        str1 = "abcdefg";
        str2 = "hijklmnop";
        str3 = "abcdefg";
        str4 = "fghijklmnop";
        str5 = "bbbbbb";
        str6 = "bbbbbbbbbbbbb";
        overlap1 = 0;
        overlap2 = 2;

        // temporarily store the results
        result1 = StringReassembly.combination(str1, str2, overlap1);
        result2 = StringReassembly.combination(str3, str4, overlap2);
        result3 = StringReassembly.combination(str5, str6, overlap3);

        // check results
        assertEquals("abcdefghijklmnop", result1);
        assertEquals("abcdefghijklmnop", result2);
        assertEquals("bbbbbbbbbbbbb", result3);
    }

    /**
     * Test method for
     * {@link StringReassembly#addToSetAvoidingSubstrings (components.set.Set, java.lang.String)}.
     */
    @Test
    public void testAddToSetAvoidingSubstrings() {
        String str1, str2, str3, str4, str5, str6, str7;
        Set<String> set1 = new Set1L<String>();
        // String that will be in set
        str1 = "Buck";
        str2 = "IDE";
        str3 = "Go Bucks";
        // Strings that will be tested
        str4 = "ID";
        str5 = "CKA";
        str6 = "AIDE";
        str7 = "Go Bucks; beat Michigan!";
        // add str1 str2 str3 to the set1
        set1.add(str1);
        set1.add(str2);
        set1.add(str3);
        // test str4
        StringReassembly.addToSetAvoidingSubstrings(set1, str4);
        assertFalse(set1.contains(str4));
        assertTrue(set1.contains(str2));
        // test str5
        StringReassembly.addToSetAvoidingSubstrings(set1, str5);
        assertTrue(set1.contains(str5));
        // test str6
        StringReassembly.addToSetAvoidingSubstrings(set1, str6);
        assertFalse(set1.contains(str2));
        assertTrue(set1.contains(str6));
        // test str7
        StringReassembly.addToSetAvoidingSubstrings(set1, str7);
        assertFalse(set1.contains(str3));
        assertTrue(set1.contains(str7));
    }

    /**
     * Test method for
     * {@link StringReassembly#linesFromInput (components.simplereader.SimpleReader)}.
     */
    @Test
    public void testLinesFromInput() {
        // set in as a file reader
        SimpleReader in = new SimpleReader1L("./data/cheer-8-2.txt");
        Set<String> test = new Set1L<>();
        // call linesFromInput and store to test
        test.add(StringReassembly.linesFromInput(in));
        // test whether there are terms that must be in the set
        assertTrue(test.contains("Bucks -- Beat"));
        assertTrue(test.contains("Go Bucks"));
        assertTrue(test.contains("Beat Mich"));
        assertTrue(test.contains("Michigan~"));
        assertTrue(test.contains("o Bucks -- B"));
    }
}
