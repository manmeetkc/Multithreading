class MyThread extends Thread{

    @Override
    public void run(){
        for(int i = 0; i<=10; i++){
            System.out.println(this.getName() + "running "+ i);
        }

    }



}
class MyRunnable implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i<=10; i++){
            System.out.println(Thread.currentThread() + "running "+ i);
        }
    }
}
public class MultipleThreads {
        public static void main(String[] args) {
            Thread t1 = new Thread(new MyRunnable());
            Thread t2 = new Thread(new MyRunnable());
            //start method calls run method
            //cannot call start method on same thread twice
            //create thread again if i want to run it again
            t1.start();
            t2.start();

        }



}
