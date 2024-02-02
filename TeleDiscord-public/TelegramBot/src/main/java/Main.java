import Application.TelegramBot;
import Services.Receiver;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

// TODO: Find some way to hide telegramBot key.
public class Main {
    public static void main(String[] args) throws IOException {
        //Build TelegramBot
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(TelegramBot.getInstance());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        //Build HttpListener (Receiver)
        Receiver receiver = new Receiver(6969);
        System.out.println("Starting Receiver...");
        receiver.start();
        System.out.println("Receiver Started!");
    }
}