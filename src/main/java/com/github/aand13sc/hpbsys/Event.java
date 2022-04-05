package com.github.aand13sc.hpbsys;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class Event  implements Listener {
    public static Hpbsys plugin;
    public Event(Hpbsys instance) { plugin = instance; }

    // 対象者が入ってきちゃったら「まだだよ」的なメッセージ欲しい
    @EventHandler
    public void onJoin(PlayerLoginEvent e){
        Player player = e.getPlayer();
        if (player.getName().equals(Config.SYUYAKU_MCID)) {
            if (!Hpbsys.NOW) {
                Bukkit.getLogger().info(player.getName());
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Config.DAMADAYO_MESSAGE);
            }
        }
    }
}
