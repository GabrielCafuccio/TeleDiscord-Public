import application.DiscordBot;
import events.HttpMessageReceiverEventListener;

import java.io.IOException;
//TODO: find some way to hide bot key.


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        DiscordBot discordBot = DiscordBot.getInstance();
        discordBot.start();

        //Build HttpListener (Receiver)
        HttpMessageReceiverEventListener receiver = new HttpMessageReceiverEventListener(6970);
        System.out.println("Starting Receiver...");
        receiver.start();
        System.out.println("Receiver Started!");
    }
}
