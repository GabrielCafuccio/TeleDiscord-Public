package services;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import utils.UserTracking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

//Singleton
public class TimeTracking {
    private static ArrayList<UserTracking> userTrackings = new ArrayList<>();
    private static ArrayList<UserTracking> userInSession = new ArrayList<>();

    private static TimeTracking timeTracking;

    public static TimeTracking getInstance() {
        if (timeTracking == null) {
            timeTracking = new TimeTracking();
        }
        return timeTracking;
    }

    public void userJoin(GuildVoiceUpdateEvent event) {
        ArrayList<UserTracking> trackingsListTemp = userTrackings.stream().filter(u -> u.getId().equals(event.getMember().getId())).collect(Collectors.toCollection(ArrayList::new));
        if (!trackingsListTemp.isEmpty()) {
            System.out.println("UserJoin: Existing user!");
            UserTracking user = trackingsListTemp.get(0);
            user.setTimeJoined(LocalDateTime.now());
            userInSession.add(user);
        }
        else{
            System.out.println("UserJoin: New user!");
            UserTracking user = new UserTracking(event.getMember().getEffectiveName(), event.getMember().getId(), LocalDateTime.now());
            userInSession.add(user);
        }
        System.out.println("UserJoin: Users In Session: " + userInSession);
    }

    public void userDisconnect(GuildVoiceUpdateEvent event) {
        ArrayList<UserTracking> userListTemp = userInSession.stream().filter(u -> u.getId().equals(event.getMember().getId())).collect(Collectors.toCollection(ArrayList::new));
        if (!userListTemp.isEmpty()) {
            UserTracking user = userListTemp.get(0);
            if (userTrackings.contains(user)) {
                user.sumTimeAccumulated();
            } else {
                user.sumTimeAccumulated();
                userTrackings.add(user);
            }
            userInSession.remove(user);
            System.out.println("UserDisconnect: User | time: " + user.getUsername() + " | " + user.getTimeAccumulated());
        }
    }
}