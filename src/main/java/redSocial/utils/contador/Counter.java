package redSocial.utils.contador;
public class Counter {
    private int count;

    public Counter() {
        count = 0;
    }

    public synchronized void increment() {
        count++;
        if (count>60){
            count=0;
        }
    }

    public synchronized int getVal() {
        return count;
    }
}
