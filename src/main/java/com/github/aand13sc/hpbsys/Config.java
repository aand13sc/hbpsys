package com.github.aand13sc.hpbsys;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static Hpbsys  plugin;
    public Config(Hpbsys instance){ plugin = instance; }

    public static String SYUYAKU_MCID;

    public static void load() {
        FileConfiguration config = Hpbsys.plugin.getConfig();
        SYUYAKU_MCID = config.getString("SYUYAKU_MCID");
    }
}
