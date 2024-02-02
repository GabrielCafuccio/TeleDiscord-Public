package application;

import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.List;

public class DiscordBot {
    public static DiscordBot discordBot = null;
    private static JDA jda;

    public static DiscordBot getInstance(){
        if(discordBot == null){
            discordBot = new DiscordBot();
        }
        return discordBot;
    }

    public void start() throws InterruptedException {
        final String Token = "YOUR BOT TOKEN HERE";

        JDABuilder jdaBuilder = JDABuilder.createDefault(Token);

        this.jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new ReadyEventListener(), new MessageEventListener(), new InteractionEventListener(), new GuildVoiceJoinEventListener())
                .build();

        jda.upsertCommand("listen-to-channel", "Choose a text channel to listen")
                .addOption(OptionType.CHANNEL, "canal", "channel in which to listen", true)
                .setGuildOnly(true)
                .queue();

        jda.upsertCommand("listened-channel", "Channel that is being currently listened")
                .setGuildOnly(true)
                .queue();

        jda.awaitReady();
    }

    public static JDA getJda() {
        return jda;
    }
}
