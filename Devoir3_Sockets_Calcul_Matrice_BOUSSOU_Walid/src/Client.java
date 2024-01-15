
import java.net.*;
import java.io.*;

public class Client {

    /* emeteur, on envoie une matrice, serveur vas recevoir la donne faire calcul et resend la resulta, client recevoir resultat et printf */
    public static void main(String args[]) throws Exception {

        //    int[][] mat = {{1, 48, 5}, {-7, 50, 7}, {5, 3, 1}};
        //    int[][] mat2 = {{-2, 4, 6}, {22, 1, 8}, {6, 4, 2}};
        int[][] mat = new int[3][3];
        int[][] mat2 = new int[3][3];

        //Remplir 1er matrice
        System.out.println("\nCommancant par la premiere matrice");
        System.out.println("_________________________________");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                System.out.print("\n Entrer la valeur de ligne " + i + " colonne " + j + " : ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                try {
                    String s = br.readLine();

                    mat[i][j] = Integer.parseInt(s);

                } catch (IOException e) // Gestionnaire de l'exception IOException.
                {
                    System.err.println("Erreur d'entree/sortie : " + e.getMessage());
                } catch (NumberFormatException e) // Gestionnaire de l'exception NumberFormatException.
                {
                    System.err.println("La valeur entree au clavier n'est pas un nombre : " + e.getMessage());
                }
            }
        }

        DatagramSocket ds = new DatagramSocket();

        //send first matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                /*getbytes n'accepte que de string pour ca il faut convertir les int, 
                c'est une methode existant dans la classe string, 
                seront transformer on byte[] contenant elmt par elmnt*/
                String s = Integer.toString(mat[i][j]);

                DatagramPacket envoi = new DatagramPacket(
                        s.getBytes(), s.length(),
                        InetAddress.getByName("localhost"), 8000);
                ds.send(envoi);

            }
        }

        //remplir 2eme matrice
        System.out.println("\nMaintenant la deuxiemme matrice");
        System.out.println("_________________________________");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                System.out.print("\n Entrer la valeur de ligne " + i + " colonne " + j + " : ");

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                try {
                    String s = br.readLine();
                    mat2[i][j] = Integer.parseInt(s);
                } catch (IOException e) // Gestionnaire de l'exception IOException.
                {
                    System.err.println("Erreur d'entrée/sortie : " + e.getMessage());
                } catch (NumberFormatException e) // Gestionnaire de l'exception NumberFormatException.
                {
                    System.err.println("La valeur entrée au clavier n'est pas un nombre : " + e.getMessage());
                }
            }
        }

        // turn for the 2nd matrix to send it
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String s = Integer.toString(mat2[i][j]);

                DatagramPacket envoi = new DatagramPacket(
                        s.getBytes(), s.length(),
                        InetAddress.getByName("localhost"), 8000);
                ds.send(envoi);

            }
        }

        //VALIDATION TOUT CORRECT
        DatagramPacket msg2 = new DatagramPacket(new byte[512], 512);
        ds.receive(msg2);
        System.out.println(" \n " + new String(msg2.getData()) + "\n Le port " + msg2.getAddress() + " : " + msg2.getPort());

        /* 
        System.out.println("\nLa somme des deux matrices :");
        System.out.println("_________________");

        //while (true) {
        DatagramPacket msg3 = new DatagramPacket(
                new byte[512], 512);

        //receive 1st matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ds.receive(msg3);

                String s = new String(msg3.getData(), 0, msg3.getLength());
               
                mat[i][j] = Integer.parseInt(s);
                System.out.print(mat[i][j] + "\t");
                 }
            System.out.print("\n");
        }

        //receive 2nd matrix
        System.out.print(" \n \n \n");
        System.out.println("La soustraction des deux matrices:");
        System.out.println("__________________");
        DatagramPacket msg4 = new DatagramPacket(
                new byte[512], 512);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                ds.receive(msg4);
                String s = new String(msg4.getData(), 0, msg4.getLength());
                mat2[i][j] = Integer.parseInt(s);

                System.out.print(mat2[i][j] + "\t");

            }
            System.out.print("\n");
        }*/
    }
}
