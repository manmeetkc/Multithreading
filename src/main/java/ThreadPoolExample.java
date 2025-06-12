import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {
    private final int taskId;

    public Worker(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is processing task: " + taskId);
        try {
            Thread.sleep(2000); // Simulate task execution time 
        } catch (InterruptedException e) {
            System.out.println("Task interrupted: " + e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " finished task: " + taskId);
    }
}


public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads 
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit 5 tasks to the thread pool 
        for (int i = 1; i <= 5; i++) {
            executorService.submit(new Worker(i));
        }

        // Shutdown the executor service 
        executorService.shutdown();
    }
}