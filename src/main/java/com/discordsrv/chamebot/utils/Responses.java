package com.discordsrv.chamebot.utils;

import com.discordsrv.chamebot.ChameBot;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Responses {
    private static ChameBot pl;
    private static FileConfiguration responses;
    private static ConfigurationSection triggers;
    private static ConfigurationSection messagefrom;

    public static String getResponse(String ymlpath){
        Object response = responses.get(ymlpath);
        if (response instanceof String) {
            return responses.getString(ymlpath);
        } else if (response instanceof List) {
            String[] messages = responses.getList(ymlpath).stream().toArray(String[]::new);
            int randomindex = new Random().nextInt(messages.length);
            return messages[randomindex];
        } else {
            return null;
        }
    }
    
    public static String[] getResponseArray(String ymlpath){
        Object response = responses.get(ymlpath);
        if (response instanceof String) {
            String[] str = new String[1];
            str[0] = responses.getString(ymlpath);
            return str;
        } else if (response instanceof List) {
            return responses.getList(ymlpath).stream().toArray(String[]::new);
        } else {
            return null;
        }
    }

    public static ConfigurationSection getPlayersToListenTo(){
        return messagefrom;
    }

    public static ConfigurationSection getTriggers(){
        return triggers;
    }

    public static void initializeConfig(ChameBot plugin) {
        pl = plugin;
        responses = pl.getConfig();

        List<String> zonalstartwithanswers = Arrays.asList("im 100% gae for chame", "i don’t fart, i just shit my pants", "i'm internally suffering");
        responses.addDefault("startswith.!ip", "idk anymore man");
        responses.addDefault("startswith.!zonal", zonalstartwithanswers);
        responses.addDefault("messagefrom.525195173949472778", "cum.thanks professor aya");
        responses.addDefault("messagefrom.730044523710906408", "cum.thanks professor aya");
        responses.options().copyDefaults(true);
        pl.saveConfig();
        triggers = responses.getConfigurationSection("startswith");
        messagefrom = responses.getConfigurationSection("messagefrom");
    }
}