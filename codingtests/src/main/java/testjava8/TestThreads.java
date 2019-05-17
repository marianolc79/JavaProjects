package testjava8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestThreads {

    private static Random rnd = new Random(System.currentTimeMillis());

    private static void time() {
        try {
            Thread.sleep(2000 + rnd.nextInt(5000));
        } catch (Exception e) {

        }
    }

    public static void process(int n) {
        time();
        System.out.println("[" + Thread.currentThread().getId() + "] Process " + n);
    }

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        ThreadStepMonitor mon = new ThreadStepMonitor();

        list.parallelStream().forEach(x -> {
            for (int i = 0; i < 2; i++) {
                mon.start("Process " + (i + 1));
                process(i + 1);
                mon.stop();
            }
        });

        mon.printStats();
    }
}
