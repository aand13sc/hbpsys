package com.github.aand13sc.hpbsys;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Event  implements Listener {
    public static Hpbsys plugin;
    public Event(Hpbsys instance) { plugin = instance; }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){
        Player player = e.getPlayer();
        if (player.getName().equals(Config.SYUYAKU_MCID)) {
            if (!Hpbsys.NOW) {
                Bukkit.getLogger().info(player.getName());
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Config.DAMADAYO_MESSAGE);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (Hpbsys.NOW) {
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.setFlying(true);
        }
        else {
            player.setGameMode(GameMode.CREATIVE);
        }
    }

    @EventHandler
    public  void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        if (Hpbsys.NOW) {
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.setFlying(true);
        }
        else {
            player.setGameMode(GameMode.CREATIVE);
        }
    }
}
