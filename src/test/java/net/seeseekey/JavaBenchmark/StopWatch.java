package net.seeseekey.JavaBenchmark;

/**
 * Helper class for measure time
 */
public class StopWatch {

    private Long startTime;
    private Long stopTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        stopTime = System.nanoTime();
    }

    public double getSeconds() {

        if (stopTime == null) {
            // Get time if timer is running
            return (System.nanoTime() - startTime) / 1000000000.0; // Get seconds from nano seconds
        } else {
            // Get time if timer is not running
            return (stopTime - startTime) / 1000000000.0; // Get seconds from nano seconds
        }
    }
}
