package CHelper.src.algo;

import net.egork.chelper.util.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

public class TaskManager {
    private List<Thread> tasks = new ArrayList<>();

    public void acceptTask(Runnable task) {
        tasks.add(new Thread(task));
    }

    private void startAllTasks() {
        tasks.stream().forEach(Thread::start);
    }

    private void waitForAllToComplete() {
        tasks.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void removeAllTasks() {
        tasks = new ArrayList<>();
    }

    public void completeAllTasks() {
        startAllTasks();
        waitForAllToComplete();
        removeAllTasks();
    }

    private LongAdder counter = new LongAdder();

    public void cyclicInputReader(final int testCases,
                                  final int noOfThreads,
                                  InputReader inputReader, final int k) {
        for (int thread = 0; thread < noOfThreads; thread++) {
            final int threadIndex = thread;
            acceptTask(() -> {
                for (int i = threadIndex; i < testCases; i = i + noOfThreads) {
                    if (inputReader.readInt() % k == 0) {
                        counter.increment();
                    }
                }
            });
        }
        completeAllTasks();
    }

    public LongAdder getCounter() {
        return counter;
    }
}
