package redSocial.utils.contador;

public class Read extends Thread{
    private static final int MAX = 1000000000;
    private Counter myCounter;
    protected int sec;
    protected int min;
    protected int hours;

    public Read(Counter c) {
        myCounter = c;
    }

    public synchronized void run() {
        for (int i = 0; i < MAX; i++) {

            synchronized (myCounter){
                sec= myCounter.getVal();
                System.out.println(hours+":"+min+":"+sec);
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

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
