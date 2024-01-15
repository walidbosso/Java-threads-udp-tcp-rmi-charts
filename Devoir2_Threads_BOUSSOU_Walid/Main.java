package procon;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author packardbell
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Depot D = new Depot();
        
        Producer producer = new Producer(D);
        producer.setName("Producer");
        producer.start();

        Consumer consumer = new Consumer(D);
        consumer.setName("Consumer1");
        consumer.start();
        /*join if i want*/

        Consumer consumer1 = new Consumer(D);
        consumer1.setName("Consumer2");
        consumer1.start();
        
    }

}
