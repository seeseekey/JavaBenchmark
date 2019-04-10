package net.seeseekey.JavaBenchmark;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Benchmark for getting a element from list with different methods
 */
public class ElementFromListTests {

    private static Logger logger = LoggerFactory.getLogger(new Exception().fillInStackTrace().getStackTrace()[0].getClassName());

    private static DecimalFormat decimalFormat;

    private static int runs = 75000;

    static {
        decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        decimalFormat.setMaximumFractionDigits(340);
    }

    /**
     * Class for holding elements
     */
    static class Element {
        public final String Key;
        public final String Value;

        public Element(String key, String value) {
            Key = key;
            Value = value;
        }
    }

    /**
     * Creates a list with approximately 125000 elements
     *
     * @return A list with elements
     */
    private List<Element> getElements() {

        List<Element> elements = new ArrayList<>();

        // Add 75000 elements
        for (int i = 0; i < 75000; i++) {
            elements.add(new Element(String.valueOf(i), String.valueOf(i)));
        }

        elements.add(new Element("Suppe", "Löffel"));
        elements.add(new Element("Wasser", "Flüssigkeit"));
        elements.add(new Element("Käse", "Gelb"));
        elements.add(new Element("Huhn", "Ei"));

        // Add 50000 elements
        for (int i = 0; i < 50000; i++) {
            elements.add(new Element(String.valueOf(i), String.valueOf(i)));
        }

        return elements;
    }


    /**
     * Iterate list without for each and get element
     */
    @Test
    void testIterateListWithoutForEach() {

        // Init
        List<Element> elements = getElements();
        StopWatch stopWatch = new StopWatch();

        // Benchmarking
        stopWatch.start();

        // Get element
        Element specificElement = null;

        for (int i = 0; i < runs; i++) {

            for (int j = 0; j < elements.size(); j++) {

                Element element = elements.get(j);

                if ("Huhn".equals(element.Key)) {
                    specificElement = element;
                    break;
                }
            }
        }

        stopWatch.stop();

        // Output
        logger.info("");
        logger.info("Iterate list (without for each)");

        logger.info("Element key: " + specificElement.Key);
        logger.info("Element value: " + specificElement.Value);

        logger.info("Time in seconds (total): " + stopWatch.getSeconds());
        logger.info("Time in seconds (per run): " + decimalFormat.format(stopWatch.getSeconds() / runs));
    }

    /**
     * Iterate list and get element
     */
    @Test
    void testIterateList() {

        // Init
        List<Element> elements = getElements();
        StopWatch stopWatch = new StopWatch();

        // Benchmarking
        stopWatch.start();

        // Get element
        Element specificElement = null;

        for (int i = 0; i < runs; i++) {

            for (Element element : elements) {

                if ("Huhn".equals(element.Key)) {
                    specificElement = element;
                    break;
                }
            }
        }

        stopWatch.stop();

        // Output
        logger.info("");
        logger.info("Iterate list");

        logger.info("Element key: " + specificElement.Key);
        logger.info("Element value: " + specificElement.Value);

        logger.info("Time in seconds (total): " + stopWatch.getSeconds());
        logger.info("Time in seconds (per run): " + decimalFormat.format(stopWatch.getSeconds() / runs));
    }

    /**
     * Stream list, find first and get element
     */
    @Test
    void testStreamListAndFindFirst() {

        // Init
        List<Element> elements = getElements();
        StopWatch stopWatch = new StopWatch();

        // Benchmarking
        stopWatch.start();

        // Get element via stream api
        Element specificElement = null;

        for (int i = 0; i < runs; i++) {
            specificElement = elements.stream()
                    .filter(element -> "Huhn".equals(element.Key))
                    .findFirst()
                    .orElse(null);
        }

        stopWatch.stop();

        // Output
        logger.info("");
        logger.info("Stream list and find first");

        logger.info("Element key: " + specificElement.Key);
        logger.info("Element value: " + specificElement.Value);

        logger.info("Time in seconds (total): " + stopWatch.getSeconds());
        logger.info("Time in seconds (per run): " + decimalFormat.format(stopWatch.getSeconds() / runs));
    }

    /**
     * Stream list, find any and get element
     */
    @Test
    void testStreamListAndFindAny() {

        // Init
        List<Element> elements = getElements();
        StopWatch stopWatch = new StopWatch();

        // Benchmarking
        stopWatch.start();

        // Get element via stream api
        Element specificElement = null;

        for (int i = 0; i < runs; i++) {
            specificElement = elements.stream()
                    .filter(element -> "Huhn".equals(element.Key))
                    .findAny()
                    .orElse(null);
        }

        stopWatch.stop();

        // Output
        logger.info("");
        logger.info("Stream list and find any");

        logger.info("Element key: " + specificElement.Key);
        logger.info("Element value: " + specificElement.Value);

        logger.info("Time in seconds (total): " + stopWatch.getSeconds());
        logger.info("Time in seconds (per run): " + decimalFormat.format(stopWatch.getSeconds() / runs));
    }

    /**
     * Parallel stream list, find first and get element
     */
    @Test
    void testParallelStreamListAndFindFirst() {

        // Init
        List<Element> elements = getElements();
        StopWatch stopWatch = new StopWatch();

        // Benchmarking
        stopWatch.start();

        // Get element via stream api
        Element specificElement = null;

        for (int i = 0; i < runs; i++) {
            specificElement = elements.parallelStream()
                    .filter(element -> "Huhn".equals(element.Key))
                    .findFirst()
                    .orElse(null);
        }

        stopWatch.stop();

        // Output
        logger.info("");
        logger.info("Parallel stream list and find first");

        logger.info("Element key: " + specificElement.Key);
        logger.info("Element value: " + specificElement.Value);

        logger.info("Time in seconds (total): " + stopWatch.getSeconds());
        logger.info("Time in seconds (per run): " + decimalFormat.format(stopWatch.getSeconds() / runs));
    }

    /**
     * Parallel stream list, find any and get element
     */
    @Test
    void testParallelStreamListAndFindAny() {

        // Init
        List<Element> elements = getElements();
        StopWatch stopWatch = new StopWatch();

        // Benchmarking
        stopWatch.start();

        // Get element via stream api
        Element specificElement = null;

        for (int i = 0; i < runs; i++) {
            specificElement = elements.parallelStream()
                    .filter(element -> "Huhn".equals(element.Key))
                    .findAny()
                    .orElse(null);
        }

        stopWatch.stop();

        // Output
        logger.info("");
        logger.info("Parallel stream list and find any");

        logger.info("Element key: " + specificElement.Key);
        logger.info("Element value: " + specificElement.Value);

        logger.info("Time in seconds (total): " + stopWatch.getSeconds());
        logger.info("Time in seconds (per run): " + decimalFormat.format(stopWatch.getSeconds() / runs));
    }
}
