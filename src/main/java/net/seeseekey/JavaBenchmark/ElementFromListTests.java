package net.seeseekey.JavaBenchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark for getting a element from list with different methods
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ElementFromListTests {

    // Holding list elements
    private List<Element> ELEMENTS;

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


    @Setup
    public void setup() {
        ELEMENTS = getElements();
    }

    /**
     * Iterate list without for each and get element
     */
    @Benchmark
    public Element testIterateListWithoutForEach() {

        for (int j = 0; j < ELEMENTS.size(); j++) {

            Element element = ELEMENTS.get(j);

            if ("Huhn".equals(element.Key)) {
                return element;
            }
        }

        return null;
    }

    /**
     * Iterate list without for each and get element
     */
    @Benchmark
    public Element testIterateListWithIterator() {

        Iterator<Element> iterator = ELEMENTS.iterator();

        while (iterator.hasNext()) {

            Element element = iterator.next();

            if ("Huhn".equals(element.Key)) {
                return element;
            }
        }

        return null;
    }

    /**
     * Iterate list and get element
     */
    @Benchmark
    public Element testIterateList() {

        for (Element element : ELEMENTS) {

            if ("Huhn".equals(element.Key)) {
                return element;
            }
        }

        return null;
    }

    /**
     * Stream list, find first and get element
     */
    @Benchmark
    public Element testStreamListAndFindFirst() {

        return ELEMENTS.stream()
                .filter(element -> "Huhn".equals(element.Key))
                .findFirst()
                .orElse(null);
    }

    /**
     * Stream list, find any and get element
     */
    @Benchmark
    public Element testStreamListAndFindAny() {

        return ELEMENTS.stream()
                .filter(element -> "Huhn".equals(element.Key))
                .findAny()
                .orElse(null);
    }

    /**
     * Parallel stream list, find first and get element
     */
    @Benchmark
    public Element testParallelStreamListAndFindFirst() {

        return ELEMENTS.parallelStream()
                .filter(element -> "Huhn".equals(element.Key))
                .findFirst()
                .orElse(null);
    }

    /**
     * Parallel stream list, find any and get element
     */
    @Benchmark
    public Element testParallelStreamListAndFindAny() {

        return ELEMENTS.parallelStream()
                .filter(element -> "Huhn".equals(element.Key))
                .findAny()
                .orElse(null);
    }
}
