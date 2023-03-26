package matriceTP;

import java.io.*;
import java.net.*;

public class MatrixServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2434);
            System.out.println("Serveur démarré");

            while (true) {
                Socket client = server.accept();
                System.out.println("Client connected from " + client.getInetAddress());

             // Créer des flux d'entrée et de sortie
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);

             // Demander le nom d'utilisateur et le mot de passe
                out.println("Entrez votre nom d'utilisateur: ");
                String username = in.readLine();
                out.println("Tapez votre mot de passe: ");
                String password = in.readLine();

             // Effectue une authentification de base
                if (username.equals("zineb") && password.equals("12345")) {
                    out.println("Authentification réussie. Saisissez la taille de la matrice : ");
                    int size = Integer.parseInt(in.readLine());

                 // Réception de la matrice du client
                    double[][] matrix = new double[size][size];
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            out.println("Entrez la valeur de l'élément (" + i + ", " + j + "): ");
                            matrix[i][j] = Double.parseDouble(in.readLine());
                        }
                    }

                 // Effectue la multiplication matricielle
                    double[][] result = multiply(matrix, matrix);

                 // Renvoie le résultat au client
                    out.println("Résultat:");
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            out.print(result[i][j] + "\t");
                        }
                        out.println();
                    }
                } else {
                    out.println("Authentification échouée. Fermeture de la connexion.");
                    client.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static double[][] multiply(double[][] a, double[][] b) {
        int n = a.length;
        int m = b[0].length;
        int p = b.length;
        double[][] c = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0.0;
                for (int k = 0; k < p; k++) {
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }
        return c;
    }
}
