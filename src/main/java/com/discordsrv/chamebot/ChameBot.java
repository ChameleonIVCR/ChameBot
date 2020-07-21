package com.discordsrv.chamebot;

import com.discordsrv.chamebot.utils.Responses;

import github.scarsz.discordsrv.DiscordSRV;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;

public class ChameBot extends JavaPlugin {

    private DiscordSRVListener discordsrvListener = new DiscordSRVListener();

    @Override
    public void onEnable() {
        Responses.initializeConfig(this);
        DiscordSRV.api.subscribe(discordsrvListener);
    }

    @Override
    public void onDisable() {
        DiscordSRV.api.unsubscribe(discordsrvListener);
    }
}