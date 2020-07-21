package com.discordsrv.chamebot;

import com.discordsrv.chamebot.utils.Responses;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.ListenerPriority;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.*;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Member;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.dependencies.jda.api.entities.User;
import github.scarsz.discordsrv.util.DiscordUtil;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class DiscordSRVListener {

    @Subscribe(priority = ListenerPriority.MONITOR)
    public void discordMessageReceived(DiscordGuildMessageReceivedEvent event) {
        
        if (event.getMember() == null || DiscordUtil.getJda() == null || event.getAuthor().equals(DiscordUtil.getJda().getSelfUser()))
            return;
        String message = event.getMessage().getContentRaw().toLowerCase();
        //if starts with
        for (String trigger : Responses.getTriggers().getKeys(false)){
            if (getFirstWord(message).equals(trigger)){
                DiscordUtil.sendMessage(event.getChannel(), DiscordUtil.strip(Responses.getResponse("startswith."+trigger)));
                return;
            }
        }
        for (String user : Responses.getPlayersToListenTo().getKeys(false)){
            if (event.getAuthor().getId().equals(user)){
                for (String response : Responses.getResponseArray("messagefrom."+user)){
                    if(message.contains(response.split("\\.")[0])){
                        DiscordUtil.sendMessage(event.getChannel(), DiscordUtil.strip(response.split("\\.")[1]));
                        return;
                    }
                }
            }
        }

    }

    private String getFirstWord(String text) {

        int index = text.indexOf(' ');
      
        if (index > -1) {
            return text.substring(0, index).trim();
        } else {
      
            return text;
        }
      }
}
