

package procon;

public class Producer extends Thread {

  public Depot depot;
  
   public Producer(Depot depot){
        this.depot = depot;
    }

    @Override
    public void run() {
        try {
            while (true) {
                depot.produce();
            }
        } catch (Exception e) {
        }
    }

   
}
