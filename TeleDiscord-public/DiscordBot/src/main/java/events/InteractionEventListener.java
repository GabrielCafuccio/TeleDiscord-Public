package events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        event.deferReply().queue();

        if(event.getName().equals("listen-to-channel")){
            OptionMapping option = event.getOption("canal");
            if(option == null){
                event.reply("Channel not provided").queue();
                return;
            }
            String channel = option.getAsChannel().getId();
            MessageEventListener.setTextChannel(channel);

            event.getHook().sendMessage("Channel Set!").setEphemeral(true).queue();
        }

        else if (event.getName().equals("listened-channel")){
            String channelName = MessageEventListener.getListenedChannelName();
            if(channelName == null){
                event.getHook().sendMessage("No channel Being Listened").setEphemeral(true).queue();
            }
            event.getHook().sendMessage("Listening to: " + channelName).setEphemeral(true).queue();
        }
    }
}
