import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        int i = -1;

        try (
                //Mette il server in ascolto
                ServerSocket ss = new ServerSocket(6969);
             ){
            System.out.println("server in ascolto");

            //Accetta la connessione del client
            Socket cs = ss.accept();

            System.out.println("Conesso a " + cs.getInetAddress());

            //crea il buffer per ricevere i dati
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            //crea il buffer per inviare dati
            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);

            String inputLine;
            while (!(inputLine = in.readLine()).equals("END")) {
                i++;
                out.println(i);
            }

            in.close();
            out.close();
            cs.close();
            System.out.println("Connessione con il client chiusa.");

        } catch (IOException e) {
            System.err.println("Errore nella comunicazione: " + e.getMessage());
        }
    }
}