// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Client.Repl;

//import Client.Repl;
public class Main {
        public static void main(String[] args) {
            var serverUrl = "http://localhost:8080";

            new Repl(serverUrl).run();
        }

    }

