package Client;

import Client.websocket.NotificationHandler;
import webSocketMessages.Notification;

import java.util.Scanner;

import static Client.EscapeSequences.*;

public class Repl {
    private final ChessClient client;

    public Repl(String serverUrl) {
        client = new ChessClient(serverUrl);
    }

    public void run() {
        System.out.println("\uD83D\uDC36 Welcome to 240 chess. Type Help to get started.");
        System.out.print(client.help());

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
                result = client.eval(line);
                System.out.print(result);
            } catch (Throwable e) {
                System.out.print(e.getMessage());
            }
        }
        System.out.println();
    }

    /**public void notify(Notification notification) {
        System.out.println(RED + notification.message());
        printPrompt();
    }**/

    private void printPrompt() {
        System.out.print("\n" + RESET + ">>> " + GREEN);
    }

}