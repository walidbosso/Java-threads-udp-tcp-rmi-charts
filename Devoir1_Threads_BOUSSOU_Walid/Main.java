
public class Main {

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.setName("Producer");
        producer.start();

        Consumer consumer = new Consumer(producer);
        consumer.setName("Consumer");
        consumer.start();
		/*join if i want*/
        
        Consumer consumer1 = new Consumer(producer);
        consumer1.setName("Consumer1");
        consumer1.start();
        
        
    }
}
