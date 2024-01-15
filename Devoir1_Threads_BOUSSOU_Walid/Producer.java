
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {

    private static final int MAX_SIZE = 5;
    private final List<String> messages = new ArrayList<>(); /*je peux utiliser Queue(file) or LinkedList*/

    @Override
    public void run() {
        try {
            while (true) {
                produce();
            }
        } catch (Exception e) {
        }
    }

    private synchronized void produce() throws Exception {
        while (messages.size() == MAX_SIZE) {
            wait();
        }
        String data = LocalDateTime.now().toString();
        messages.add(data);
        System.out.println("Data got produced");
        notify();
    }

    public synchronized String consume() throws Exception {
        notify();
        while (messages.isEmpty()) {
            wait();
        }
        String data = messages.get(0);
        messages.remove(data);
        return data;
    }
}
