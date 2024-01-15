
package chart;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;


public class Client implements Sensor {
    double value=0 ;

    @Override
    public Double get_temp_value() {
      value += (new Random().nextDouble()*10)%10-5  ;
      return value ;
    }
    public static void main(String[] args) throws Exception{
        // Assuming your current working directory is the project directory
        String relativePath = "src/main/resources/Data_temp.csv";
        String fileName = System.getProperty("user.dir") + "/" + relativePath;
        File file = new File(fileName);
        PrintWriter pw = new PrintWriter(fileName);
        for(int i=0;i<5;i++){
        pw.write(new Client().get_temp_value()+"");
        pw.append(",");
        }
        pw.close();
        
       /* Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        while(sc.hasNext()){
            System.out.println(sc.next());
        }
        sc.close();*/
    }
    
    
    
}
