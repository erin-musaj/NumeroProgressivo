import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String sh = "localhost";
        int port = 6969;

        try (Socket s = new Socket(sh, port)){
            System.out.println("connesso al server");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));

            String line = "";
            while (!(line = ui.readLine()).equals("END")) {
                out.println(line);
                System.out.println("Echo dal server: " + in.readLine());
            }


            in.close();
            out.close();
            System.out.println("Connessione con il server chiusa.");

        } catch (IOException e) {
            System.err.println("Errore nella comunicazione: " + e.getMessage());
        }
    }
}