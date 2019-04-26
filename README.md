# Java benchmark

Benchmark project for Java. Use the Java Microbenchmark Harness-Framework from OpenJDK.

# Usage

To use the benchmark a JAR file should be created:

> mvn clean package

After the JAR file is created, execute it in terminal:

> java -jar JavaBenchmark-1.0.0-SNAPSHOT-jar-with-dependencies.jar

After some minutes or hours, the benchmark shows the results:

    Result "net.seeseekey.JavaBenchmark.ElementFromListTests.testStreamListAndFindFirst":
      0.443 ±(99.9%) 0.028 ms/op [Average]
      (min, avg, max) = (0.403, 0.443, 0.538), stdev = 0.037
      CI (99.9%): [0.416, 0.471] (assumes normal distribution)
    
    # Run complete. Total time: 00:58:33
    
    REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
    why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
    experiments, perform baseline and negative tests that provide experimental control, make sure
    the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
    Do not assume the numbers tell you what you want them to tell.
    
    Benchmark                                                              Mode  Cnt  Score   Error  Units
    JavaBenchmark.ElementFromListTests.testIterateList                     avgt   25  0.373 ± 0.007  ms/op
    JavaBenchmark.ElementFromListTests.testIterateListWithIterator         avgt   25  0.389 ± 0.024  ms/op
    JavaBenchmark.ElementFromListTests.testIterateListWithoutForEach       avgt   25  0.390 ± 0.028  ms/op
    JavaBenchmark.ElementFromListTests.testParallelStreamListAndFindAny    avgt   25  0.302 ± 0.017  ms/op
    JavaBenchmark.ElementFromListTests.testParallelStreamListAndFindFirst  avgt   25  0.451 ± 0.051  ms/op
    JavaBenchmark.ElementFromListTests.testStreamListAndFindAny            avgt   25  0.528 ± 0.064  ms/op
    JavaBenchmark.ElementFromListTests.testStreamListAndFindFirst          avgt   25  0.443 ± 0.028  ms/op

## Authors

* seeseekey - https://seeseekey.net

## License

The project is licensed under MIT.