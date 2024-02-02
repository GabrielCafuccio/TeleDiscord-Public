package events;

import java.time.LocalDateTime;
import java.time.Duration;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import services.TimeTracking;

public class GuildVoiceJoinEventListener extends ListenerAdapter {
    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        super.onGuildVoiceUpdate(event);

        TimeTracking timeTracking = TimeTracking.getInstance();

        if(event.getChannelJoined() != null && event.getChannelLeft() != null){
//            System.out.println(event.getMember().getEffectiveName() + " (id = " + event.getMember().getId() + ") Moved Channel at "+ LocalDateTime.now());
            return;

        }
        if(event.getChannelJoined() != null){
//            System.out.println(event.getMember().getEffectiveName() + " (id = " + event.getMember().getId() + ") Joined a Channel at " + LocalDateTime.now());
            timeTracking.userJoin(event);
            return;
        }
        if(event.getChannelLeft() != null){
//            System.out.println(event.getMember().getEffectiveName() + " (id = " + event.getMember().getId() + ") Left a Channel at " + LocalDateTime.now());
            timeTracking.userDisconnect(event);
            return;
        }
    }
}

