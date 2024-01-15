
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.rmi.*;
import javax.imageio.ImageIO;
import java.rmi.registry.*;

public class Server {

   

    public static void main(String[] args) throws IOException {

        try {
//             // RMI part
//            Filter skeleton = new FilterRemote();
//            Naming.rebind("rmi://localhost:9800/Filtres", skeleton);
//            System.out.println("Server is ready ...");

            ////////////////// Socket part
            //-----------------Receiving------------//
            ServerSocket serverSocket = new ServerSocket(7800);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            System.out.println("Reading:.....");
            byte[] sizeAr = new byte[4];
            inputStream.read(sizeAr);
            int size1 = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
            byte[] imageAr = new byte[size1];
            inputStream.read(imageAr);
            BufferedImage image3 = ImageIO.read(new ByteArrayInputStream(imageAr));
            System.out.println("Received ......");
            ImageIO.write(image3, "jpg", new File("src/test3.jpg"));
            //-------------------Aply_filter------------------//

            BufferedImage image4 = ImageIO.read(new File("src/test3.jpg"));
            int height = image4.getHeight();
            int width = image4.getWidth();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int p = image4.getRGB(x, y);
                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;
                    int avg = (r + g + b) / 3;
                    p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                    image4.setRGB(x, y, p);
                }
            }

            ImageIO.write(image4, "jpg", new File("src/test4.jpg"));


            //---------------sending-----------------//
            OutputStream output = socket.getOutputStream();
            BufferedImage image = ImageIO.read(new File("src/test5.jpg"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            output.write(size);
            output.write(byteArrayOutputStream.toByteArray());
            output.flush();
            System.out.println("sending ....");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
