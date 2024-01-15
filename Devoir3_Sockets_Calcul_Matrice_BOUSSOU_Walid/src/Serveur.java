
/*ici joue le role de recpteur - mat2 while true stock 2eme matrice, traiter et send resultat somme, il faut travailler comme un chat*/
import java.net.*;

public class Serveur {

    public static void main(String args[]) throws Exception {

        /* Etablir un connexion, il faut ecrire cet meme port qu'on a choisi dans client/emeteur */
        DatagramSocket ds = new DatagramSocket(8000);

        int[][] mat1 = new int[3][3];
        int[][] mat2 = new int[3][3];
        int[][] somme = new int[3][3];
        int[][] soust = new int[3][3];

        System.out.println("\nPremiere Matrice");
        System.out.println("_________________");

        //while (true) {
        DatagramPacket msg = new DatagramPacket(
                new byte[512], 512);
        /*variable cree ou on va stocker data/Packet envoye par client, taille 512*/

        //receive 1st matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ds.receive(msg);

                String s = new String(msg.getData(), 0, msg.getLength());
                //msg.getData returns an array of bytes written in ascii as chars, ex:123 premier cellule est 1, 2eme est 2 etc
                //if we use new string(msg.getData) only, it will store a string containing an array form, not a "2" number, it gives a numberformatexception
                //a string is created from the first element "0" of getData to the last one "getLenth", a whole then is stored in "s"
                //now we can convert the full and correct form of string "123" to Integer or else it gives us an error in writing or a numberformatexception

                mat1[i][j] = Integer.parseInt(s);
                //byte[] n = msg.getData();
                //mat1[i][j] = n[0] - 48; //ascii et c'est un downcasting int est plus grand que byte il accepte de facon normal pas besoir de conversion
                System.out.print(mat1[i][j] + "\t");

                // System.out.println("Recu " + new String(msg.getData()) + " envoye sur leport " + msg.getAddress() + " : " + msg.getPort());
            }
            System.out.print("\n");
        }

        //receive 2nd matrix
        System.out.print(" \n \n \n");
        System.out.println("2eme Matrice");
        System.out.println("_________________");
        DatagramPacket msg2 = new DatagramPacket(
                new byte[512], 512);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                ds.receive(msg2);
                String s = new String(msg2.getData(), 0, msg2.getLength());
                mat2[i][j] = Integer.parseInt(s);

                System.out.print(mat2[i][j] + "\t");

            }
            System.out.print("\n");
        }

        System.out.print(" \n \n \n");
        System.out.println("Voici la somme des deux matrices");
        System.out.println("_________________");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                somme[i][j] = mat1[i][j] + mat2[i][j];

                System.out.print(somme[i][j] + "\t");

            }
            System.out.print("\n");

        }
        System.out.print(" \n \n \n");
        System.out.println("Et voici la soustraction des deux matrices (1er-2eme)");
        System.out.println("_________________");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                soust[i][j] = mat1[i][j] - mat2[i][j];

                System.out.print(soust[i][j] + "\t");

            }
            System.out.print("\n");

        }

        /*informer/valider qu'on a recu msg from client earlier we send it to client*/
        String texteReponse = " Bien, la somme et soutraction sont faite et affiche dans la page commande de serveur ";
        DatagramPacket reponse = new DatagramPacket(
                texteReponse.getBytes(), texteReponse.length(),
                msg.getAddress(), msg.getPort()); //il faut l'envoyer au port et adresse d'ou on a recu le packet DatagramPacket
        ds.send(reponse);

        //In case we canted to send result to be seen in clients command prompt
        
       /* for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               
                String s = Integer.toString(somme[i][j]);

                DatagramPacket envoi = new DatagramPacket(
                        s.getBytes(), s.length(),
                        msg.getAddress(), msg.getPort());
                ds.send(envoi);

            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               
                String s = Integer.toString(soust[i][j]);

                DatagramPacket envoi = new DatagramPacket(
                        s.getBytes(), s.length(),
                        msg.getAddress(), msg.getPort());
                ds.send(envoi);

            }
        }*/
    }
    //} //while true
}
