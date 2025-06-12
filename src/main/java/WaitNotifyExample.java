public class WaitNotifyExample {

    // Shared resource: counter with one-slot buffer
    static class Counter {
        private String dish;
        private boolean empty = true;

        // Kitchen puts dish on counter
        public synchronized void put(String newDish) throws InterruptedException {
            // If counter is full, wait
            while (!empty) {
                wait(); // release lock and wait
            }

            // Put dish
            dish = newDish;
            empty = false;
            System.out.println("Kitchen produced: " + dish);

            // Notify waiter that a dish is ready
            notify();
        }

        // Waiter takes dish from counter
        public synchronized String take() throws InterruptedException {
            // If counter is empty, wait
            while (empty) {
                wait(); // release lock and wait
            }

            // Take dish
            String servedDish = dish;
            dish = null;
            empty = true;
            System.out.println("Waiter served: " + servedDish);

            // Notify kitchen that counter is empty
            notify();

            return servedDish;
        }
    }

    /**
     *
     * our counter object is locked, only take or put can be called one at a time.
     * wait is just to give control to another thread that can potentially change the state of empty
     */

    public static void main(String[] args) {
        Counter counter = new Counter();

        // Kitchen thread → produces dishes
        Thread kitchen = new Thread(() -> {
            String[] dishes = {"Pizza", "Burger", "Pasta"};
            try {
                for (String dish : dishes) {
                    counter.put(dish);
                    Thread.sleep(500); // Simulate cooking time
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Waiter thread → consumes dishes
        Thread waiter = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    counter.take();
                    Thread.sleep(800); // Simulate serving time
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        kitchen.start();
        waiter.start();
    }
}
