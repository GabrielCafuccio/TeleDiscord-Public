package events;

import application.DiscordBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import services.Sender;

import java.io.IOException;
import java.net.URISyntaxException;

public class MessageEventListener extends ListenerAdapter {
    private static TextChannel textChannel = null;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if (textChannel == null) {
            return;
        }
        //If the author is not a bot
        //If the channel id is set
        if (!event.getAuthor().isBot() && textChannel.getId().equals(event.getChannel().getId())) {
            try {
                Sender sender = new Sender();
                sender.sendMessage(event.getAuthor().getEffectiveName(), event.getMessage().getContentRaw());
            } catch (IOException | URISyntaxException | InterruptedException ignored) {
            }
        }
    }

    public static void setTextChannel(String channel) {
        JDA jda = DiscordBot.getJda();
        textChannel = jda.getTextChannelById(channel);
    }

    public static String getListenedChannelName(){
        return textChannel.getName();
    }
}
