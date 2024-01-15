import java.io.File;
import java.rmi.*;
import java.rmi.Remote;
 // this interface is common aspect between the client and the server in RMI
public interface Filter extends Remote{
    public File Aplyfiltre(File myimage , String filtre)throws RemoteException;
}
