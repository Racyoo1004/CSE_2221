import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.text.Text;
import components.text.Text1L;

/**
 * Unit test cases for {@link Glossary.java} that tests the functionalities for
 * the methods.
 *
 * @author Yoojin Jeong
 * @date 11/24/2020
 */
public class GlossaryTest {

    /**
     * Test method for
     * {@link Glossary#getTermMap(java.lang.String, components.queue.Queue)}.
     * Create a map including the keys(terms) and values(definitions). Add the
     * terms in the queue and update it by sorting it with lexical order.
     */
    @Test
    public void testGetTermMap() {
        // Get input file and other initial variables
        String fileName = "./data/terms.txt";
        Map<String, String> idealMap = new Map1L<>();
        Queue<String> idealTerms = new Queue1L<>();
        Queue<String> terms = new Queue1L<>();

        // Add keys(terms) and values(definitions) to the ideal map
        idealMap.add("meaning", "something that one wishes to convey, "
                + "especially by language");
        idealMap.add("term", "a word whose definition is in a glossary");
        idealMap.add("word", "a string of characters in a language, "
                + "which has at least one character");
        idealMap.add("definition",
                "a sequence of words that gives meaning to a term");
        idealMap.add("glossary", "a list of difficult or specialized terms, "
                + "with their definitions, usually near the end of a book");
        idealMap.add("language", "a set of strings of characters, "
                + "each of which has meaning");
        idealMap.add("book", "a printed or written literary work");

        // Add terms to the ideal queue
        idealTerms.enqueue("book");
        idealTerms.enqueue("definition");
        idealTerms.enqueue("glossary");
        idealTerms.enqueue("language");
        idealTerms.enqueue("meaning");
        idealTerms.enqueue("term");
        idealTerms.enqueue("word");

        // Call method to generate the result map
        Map<String, String> termMap = Glossary.getTermMap(fileName, terms);
        // Test the ideal map and generated map
        assertEquals(idealMap, termMap);
        // Test the ideal queue and updated queue
        assertEquals(idealTerms, terms);
    }

    /**
     * Test method for {@link Glossary#onlyWord(java.lang.String)}. Creates an
     * array of the definition words without any commas, spaces, or periods
     */
    @Test
    public void testNextWordRemoveComma() {
        // Get an array of definition with only words in it
        String s = "something that one wishes to convey, especially by language";
        String[] arr = Glossary.onlyWord(s);
        StringBuilder str = new StringBuilder();
        String expect = "something that one wishes to convey especially by language ";

        // Loop to add the array words as a string
        for (String x : arr) {
            str.append(x + " ");
        }
        String result = str.toString();

        // Test the string
        assertEquals(result, expect);
    }

    /**
     * Test method for {@link Glossary#onlyWord(java.lang.String)}. Creates an
     * array of the definition words without any commas, spaces, or periods
     */
    @Test
    public void testNextWordRemovePeriod() {
        // Get an array of definition with only words in it
        String s = "a word whose definition is in a glossary.";
        String[] arr = Glossary.onlyWord(s);
        StringBuilder str = new StringBuilder();
        String expect = "a word whose definition is in a glossary ";

        // Loop to add the array words as a string
        for (String x : arr) {
            str.append(x + " ");
        }
        String result = str.toString();

        // Test the string
        assertEquals(result, expect);
    }

    /**
     * Test method for {@link Glossary#onlyWord(java.lang.String)}. Creates an
     * array of the definition words without any commas, spaces, or periods
     */
    @Test
    public void testNextWordRemoveCommaAndPeriod() {
        // Get an array of definition with only words in it
        String s = "something that one wishes to convey, especially by language.";
        String[] arr = Glossary.onlyWord(s);
        StringBuilder str = new StringBuilder();
        String expect = "something that one wishes to convey especially by language ";

        // Loop to add the array words as a string
        for (String x : arr) {
            str.append(x + " ");
        }
        String result = str.toString();

        // Test the string
        assertEquals(result, expect);
    }

    /**
     * Test method for
     * {@link Glossary#onlyWord(java.lang.String, components.queue.Queue)}.
     * Creates a main html, which includes the term in unlisted order. Each term
     * includes a link to their definition html.
     */
    @Test
    public void testTermPage() {
        SimpleReader inIdeal = new SimpleReader1L("./idealResult/index.html");
        Queue<String> terms = new Queue1L<>();
        terms.enqueue("Buckeye");
        terms.enqueue("Columbus");
        terms.enqueue("OSU");
        terms.enqueue("Yoojin");

        Glossary.termPage("testResult", terms);

        SimpleReader inTest = new SimpleReader1L("./testResult/index.html");

        while (!(inIdeal.atEOS() && (inTest.atEOS()))) {
            // Store the first term and definition
            String idealString = inIdeal.nextLine();
            String testString = inTest.nextLine();
            // Check whether the reader reached to the end of the file
            assertEquals(idealString, testString);
        }

        // Surefire way to test whole sentences
        Text test = new Text1L();
        Text ideal = new Text1L();

        // Put everything in ideal index.html file to the Text ideal
        while (!inIdeal.atEOS()) {
            ideal.append(new Text1L(inIdeal.nextLine()));
        }
        // Put everything in test index.html file to the Test test
        while (!inTest.atEOS()) {
            test.append(new Text1L(inTest.nextLine()));
        }
        // Check whether both are same
        assertEquals(ideal, test);

        // Close streams
        inIdeal.close();
        inTest.close();

    }

    /**
     * Test method for
     * {@link Glossary#onlyWord(components.map.Map, components.queue.Queue, java.lang.String)}.
     * Creates a definition html, which includes the term with definition. If
     * any terms also include in the definition, replace the terms in the
     * definition with the link
     */
    @Test
    public void testDefinitionPage() {
        Map<String, String> termMap = new Map1L<>();
        termMap.add("OSU", "The ohio state university in Columbus.");
        termMap.add("Columbus", "One of the cities in Ohio.");
        termMap.add("Yoojin", "A student at OSU, who also lives in Columbus.");
        termMap.add("Buckeye", "Symbol of OSU.");

        Queue<String> terms = new Queue1L<>();
        terms.enqueue("OSU");
        terms.enqueue("Columbus");
        terms.enqueue("Yoojin");
        terms.enqueue("Buckeye");

        // Call pageGenerator
        while (terms.length() > 0) {
            Glossary.definitionPage(termMap, terms, "testResult");
        }

        // Loop until the terms in queue is empty
        while (terms.length() > 0) {
            String term = terms.dequeue();
            // Set up input stream based on the term such as term.html
            SimpleReader inTest = new SimpleReader1L(
                    "./testResult/" + term + ".html");
            SimpleReader inIdeal = new SimpleReader1L(
                    "./idealResult/" + term + ".html");

            // Loop until the end of the file
            while (!(inIdeal.atEOS() && (inTest.atEOS()))) {
                // Store the first term and definition
                String idealString = inIdeal.nextLine();
                String testString = inTest.nextLine();
                // Check whether the reader reached to the end of the file
                assertEquals(idealString, testString);
            }

            // Surefire way to test whole sentences
            Text test = new Text1L();
            Text ideal = new Text1L();

            // Put everything in ideal index.html file to the Text ideal
            while (!inIdeal.atEOS()) {
                ideal.append(new Text1L(inIdeal.nextLine()));
            }
            // Put everything in test index.html file to the Test test
            while (!inTest.atEOS()) {
                test.append(new Text1L(inTest.nextLine()));
            }
            // Check if both are same
            assertEquals(ideal, test);

            inIdeal.close();
            inTest.close();
        }
    }

}
