package utils;

import application.DiscordBot;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HttpMessageHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //Handle POST requests
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            try {
                InputStream inputStream = exchange.getRequestBody();
                String messageReceived = new String(inputStream.readAllBytes());
//                System.out.println("POST Message Received: " + messageReceived);

                //Write response
                String response = "Discord Received!";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();

                //Build message for discord chat
                TextMessage textMessage = new Gson().fromJson(messageReceived, TextMessage.class);
                String finalMessage = textMessage.getUserName() + ":  " + textMessage.getText();

                //Write message to discord chat
                DiscordBot discordBot = DiscordBot.getInstance();
                JDA jda = discordBot.getJda();

                List<TextChannel> channels = jda.getTextChannelsByName("general", true);
                channels.get(0).sendMessage(finalMessage).queue();
            } catch (IOException e) {
            }
        }

    }
}
