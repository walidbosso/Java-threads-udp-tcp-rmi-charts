package procon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author packardbell
 */
public class Depot {

    protected static final int MAX_SIZE = 5;
    protected final List<String> messages = new ArrayList<>();

    public synchronized void produce() throws Exception {
        while (messages.size() == MAX_SIZE) {
            wait();
        }
        String data = LocalDateTime.now().toString(); /*product*/
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
