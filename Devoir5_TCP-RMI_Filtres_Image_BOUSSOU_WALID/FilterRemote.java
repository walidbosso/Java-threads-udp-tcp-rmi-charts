
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.imageio.ImageIO;

// this class implements the interface (RMI part server)
public class FilterRemote extends UnicastRemoteObject implements Filter {

    public FilterRemote() throws RemoteException {
        super();
    }

    @Override

    public File Aplyfiltre(File myimage, String filtre) {

        BufferedImage image = null;
        File outputfile = new File("src/filert_iamgeRMi.jpg");
        try {

            image = ImageIO.read(myimage);
            int height = image.getHeight();
            int width = image.getWidth();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int p = image.getRGB(x, y);
                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;
                    int avg = (r + g + b) / 3;
                    p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                    image.setRGB(x, y, p);
                }
            }

            // convert buffered image to file (outputfile)
            ImageIO.write(image, "jpg", outputfile);// output in file outputfile

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return outputfile;
    }
}
