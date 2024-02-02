package Utils;

import Application.TelegramBot;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpMessageHandler implements HttpHandler {
    public HttpMessageHandler() {
    }
    TelegramBot telegramBot = TelegramBot.getInstance();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //Handle POST requests
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            try {
                InputStream inputStream = exchange.getRequestBody();
                String messageReceived = new String(inputStream.readAllBytes());
                String response = "Telegram Received!";

                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();

                //Build Final string
                TextMessage textMessage = new Gson().fromJson(messageReceived, TextMessage.class);
                String finalMessage = textMessage.getUserName() + ":  " + textMessage.getText();
                telegramBot.onHttpUpdateReceived(finalMessage);

            } catch (IOException e) {
            }
        }
    }
}
