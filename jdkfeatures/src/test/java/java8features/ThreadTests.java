package java8features;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private int n;

    public Task(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println("Thread " + n);
    }

}

public class ThreadTests {

    private static void classicThreadTest() {
        Thread t1 = new Thread(new Task(1));
        Thread t2 = new Thread(new Task(2));
        Thread t3 = new Thread(new Task(3));

        t1.start();
        t2.start();
        t3.start();
    }

    private static void executorMonoTest() {
        List<Runnable> runnables = new ArrayList<Runnable>();

        runnables.add(new Task(1));
        runnables.add(new Task(2));
        runnables.add(new Task(3));

        ExecutorService execute = Executors.newSingleThreadExecutor();

        for (Runnable r : runnables) {
            execute.execute(r);
        }
        execute.shutdown();
    }

    private static void executorCachedTest() {
        List<Runnable> runnables = new ArrayList<Runnable>();

        runnables.add(new Task(1));
        runnables.add(new Task(2));
        runnables.add(new Task(3));

        ExecutorService execute = Executors.newCachedThreadPool();

        for (Runnable r : runnables) {
            execute.execute(r);
        }
        execute.shutdown();
    }

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        executorCachedTest();
        System.out.println(processors);
    }
}
