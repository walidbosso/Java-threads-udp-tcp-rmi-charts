package procon;

public class Consumer extends Thread {

    public Depot depot;

    public Consumer(Depot depot) {
        this.depot = depot;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String data = depot.consume();
                System.out.println(Thread.currentThread().getName() + " just consumed this data: " + data);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}
