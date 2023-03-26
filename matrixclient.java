package matriceTP;

import java.io.*;
import java.net.*;

public class matrixclient {
    public static void main(String[] args) {
        try {
            Socket server = new Socket("localhost", 2434);

         // Créer des flux d'entrée et de sortie
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);

         // Recevoir l'invite du nom d'utilisateur et envoyer le nom d'utilisateur
            //prompt utilisé pour désigner un message  à l'utilisateur pour demander une entrée de données
            String prompt = in.readLine();
            System.out.print(prompt);
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String username = stdin.readLine();
            out.println(username);

         // Recevoir l'invite de mot de passe et envoyer le mot de passe
            prompt = in.readLine();
            System.out.print(prompt);
            String password = stdin.readLine();
            out.println(password);

         // Recevoir l'invite de taille de matrice et envoyer la taille de matrice
            prompt = in.readLine();
            System.out.print(prompt);
            int size = Integer.parseInt(stdin.readLine());
            out.println(size);

         // Recevoir l'invite des éléments de la matrice et envoyer les éléments de la matrice
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    prompt = in.readLine();
                    System.out.print(prompt);
                    double value = Double.parseDouble(stdin.readLine());
                    out.println(value);
                }
            }

         // Réception et affichage du résultat
            prompt = in.readLine();
            System.out.println(prompt);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    //double value = Double.parseDouble(in.readLine());

                	
                	
                	
                	double value = 0.0;
                	String input = in.readLine();
                	if (input != null && input.matches("-?\\d+(\\.\\d+)?")) {
                	    value = Double.parseDouble(input);
                	} else {
                	    System.out.println(" " + input);
                	}

                	
                	
                	
                    System.out.print(value + "\t");
                }
                System.out.println();
            }

            server.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
