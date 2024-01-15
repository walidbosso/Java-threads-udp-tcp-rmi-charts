public class Consumer extends Thread {

    private Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String data = producer.consume();
                System.out.println(Thread.currentThread().getName() + " just consumed this data: " + data);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}
