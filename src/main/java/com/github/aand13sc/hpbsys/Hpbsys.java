package com.github.aand13sc.hpbsys;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Hpbsys extends JavaPlugin {

    public static Hpbsys plugin = null;

    @Override
    public void onEnable() {
        plugin = this;

        Commands command = new Commands();
        Objects.requireNonNull(getCommand("hapiba")).setExecutor(command);
        Objects.requireNonNull(getCommand("hapiba")).setTabCompleter(command);

        saveDefaultConfig();
        Config.load();

        getLogger().info("Hello!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye!");
    }
}
