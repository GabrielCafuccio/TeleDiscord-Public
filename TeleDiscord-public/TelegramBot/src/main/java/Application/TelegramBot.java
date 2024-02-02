package Application;

import Services.Sender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;

public class TelegramBot extends TelegramLongPollingBot {
    private static TelegramBot myBotAppInstance = null;
    public static TelegramBot getInstance(){
        if (myBotAppInstance == null){
            myBotAppInstance = new TelegramBot();
        }
        return myBotAppInstance;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            try {
//                System.out.println(update.getMessage().getFrom().getUserName());

                Sender sender = new Sender();
                sender.setTextMessage(update.getMessage().getText());

                sender.setUserName(update.getMessage().getFrom().getUserName());

                sender.sendMessage();
            } catch (URISyntaxException | IOException | InterruptedException e){
                System.out.println(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "TeleDiscordBbot";
    }

    @Override
    public String getBotToken() {
        // TODO: Find some way to hide this key.
        return "YOUR BOT TOKEN HERE";
    }

    public void onHttpUpdateReceived(String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId("629403796");
        sendMessage.setText(message);

        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
