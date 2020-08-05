package java8features;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.time.StopWatch;

public class ThreadStepMonitor {
    private Map<Long, ThreadState> map = new ConcurrentHashMap<>();

    private Map<String, ThreadStats> stats = new LinkedHashMap<>();

    public void start(String stepId) {
        Long id = Thread.currentThread().getId();
        ThreadState threadState;
        if (!map.containsKey(id)) {
            threadState = new ThreadState();
            map.put(id, threadState);
        } else {
            threadState = map.get(id);
        }
        threadState.start(stepId);
    }

    public void stop() {
        Long id = Thread.currentThread().getId();
        ThreadState threadState;
        if (!map.containsKey(id)) {
            threadState = new ThreadState();
        } else {
            threadState = map.get(id);
        }
        long time = threadState.stop();
        updateStats(threadState.getCurrentStep(), time);
    }

    private void updateStats(String id, long time) {
        ThreadStats stat;
        if (stats.containsKey(id)) {
            stat = stats.get(id);
            stat.setAvgTime((stat.getAvgTime() + time) / 2);
            stat.setMinTime(Math.min(stat.getMinTime(), time));
            stat.setMaxTime(Math.max(stat.getMaxTime(), time));
        } else {
            stat = new ThreadStats(id);
            stat.setAvgTime(time);
            stat.setMinTime(time);
            stat.setMaxTime(time);
            stats.put(id, stat);
        }
    }

    public void printStats() {
        System.out.println("************************************************************************");
        int cnt = 1;
        for (String stepId : stats.keySet()) {
            System.out.println((cnt++) + ") " + stepId + "\t\t" +
                            DurationFormatUtils.formatDuration(stats.get(stepId).getAvgTime(), "H:MM:ss") +
                            "\t\t" + DurationFormatUtils.formatDuration(stats.get(stepId).getMinTime(), "H:MM:ss") +
                            "\t\t" + DurationFormatUtils.formatDuration(stats.get(stepId).getMaxTime(), "H:MM:ss"));
        }

        System.out.println("************************************************************************");
    }

}

class ThreadState {
    private int count = 0;
    private String currentStep;
    private StopWatch sw = new StopWatch();

    public String getCurrentStep() {
        return currentStep;
    }

    public void start(String id) {
        sw.start();
        currentStep = id;
        count++;
    }

    public int getCount() {
        return count;
    }

    public long stop() {
        if (currentStep == null) {
            throw new RuntimeException();
        }
        sw.stop();
        long time = sw.getTime();
        sw.reset();
        return time;
    }
}

class ThreadStats {
    private String id;

    private long maxTime = Long.MIN_VALUE;
    private long minTime = Long.MAX_VALUE;
    private long avgTime;

    public ThreadStats(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public long getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(long avgTime) {
        this.avgTime = avgTime;
    }
}
