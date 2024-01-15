
import java.rmi.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import javax.swing.ImageIcon;
import javax.swing.*;

public class Client {

    // show image fonction use Jframe 
    public void showImage(File myimage, String s_or_rmi) throws IOException {
        BufferedImage image = ImageIO.read(myimage);

        JLabel picLabel = new JLabel(new ImageIcon(image));
        JFrame frame = new JFrame(s_or_rmi);
        frame.setSize(image.getWidth(), image.getHeight());
        frame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setSize(image.getWidth(), image.getHeight());
        panel.setVisible(true);
        panel.add(picLabel);
        frame.add(panel);

    }

    public static void main(String[] args) throws NotBoundException {
        Client client = new Client();
        try {

            File myimage = new File("src/image.jpg");
//            // RMI part 
//            Filter stub = (Filter) Naming.lookup("rmi://localhost:9800/Filtres");
//            File image1 = stub.Aplyfiltre(myimage, "red");// red not use yet
//            client.showImage(image1, "using RMI");

            //--------------- Socket part-------------//
            //--------------sending--------------//
            Socket socket = new Socket("localhost", 7800);
            OutputStream output = socket.getOutputStream();
            BufferedImage image = ImageIO.read(myimage);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            output.write(size);
            output.write(byteArrayOutputStream.toByteArray());
            output.flush();
            System.out.println("sending ....");

            //---------------reciving----------------//
            // receive the filter_image from server
            InputStream inputStream = socket.getInputStream();
            System.out.println("Reading: " + System.currentTimeMillis());
            byte[] sizeAr = new byte[4];
            inputStream.read(sizeAr);
            int size1 = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
            byte[] imageAr = new byte[size1];
            inputStream.read(imageAr);
            BufferedImage image3 = ImageIO.read(new ByteArrayInputStream(imageAr));
            System.out.println("Received ");
            ImageIO.write(image3, "jpg", new File("src/filter_image.jpg"));

            // display image use jframe
            client.showImage(new File("src/filter_image.jpg"), "using SOCKET");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
