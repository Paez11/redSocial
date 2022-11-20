package redSocial.utils.contador;

public class Increment extends Thread{
    private static final int MAX = 1000000000;
    private Counter myCounter;
    protected int sec;
    protected int min;
    protected int hours;

    public Increment(Counter c) {
        myCounter = c;
    }

    public synchronized void run() {
        for (int i = 0; i < MAX; i++) {
            synchronized (myCounter){
                myCounter.increment();
                sec= myCounter.getVal();
                if (sec==60){
                    sec=0;
                    min++;
                    if (min==60){
                        min=0;
                        hours++;
                    }
                }
                try {
                    sleep(500);
                    myCounter.wait(0L,1);
                    myCounter.notifyAll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
