package com.github.aand13sc.hpbsys;

import jdk.internal.foreign.abi.Binding;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {
    public static String senderErrorMessage = "This command can't execute via server console!";

    private static final String[] SUBCOMMANDS = { "head", "start", "end", "help" };

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hapiba")) {

            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("こいつなんもできないよw");
                return true;
            }

            if (args[0].equalsIgnoreCase("init")) {
                for (Player p: Bukkit.getOnlinePlayers()) {
                    p.setGameMode(GameMode.CREATIVE);
                }
                for (World world: Bukkit.getWorlds()) {
                    world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
                    world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
                    world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                    world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                    world.setGameRule(GameRule.FALL_DAMAGE, false);
                    world.setGameRule(GameRule.KEEP_INVENTORY, true);
                    world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);
                    world.setGameRule(GameRule.SPAWN_RADIUS, 1);
                    world.setStorm(false);
                    world.setTime(6000);

                    for (Entity entity: world.getEntities()) {
                        if (!(entity instanceof Player)) {
                            entity.remove();
                        }
                    }
                }
                sender.sendMessage("[hapiba] init.");
                return true;
            }

            if (args[0].equalsIgnoreCase("start")) {
                // みんなアドベンチャーモードにして飛べるようにするよ
                for(Player p: Bukkit.getOnlinePlayers()) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255,
                                false, false, false));
                }
                // 対象者を光らせるよ
                Player target = Bukkit.getPlayer(Config.SYUYAKU_MCID);
                if (target != null){
                    target.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 255,
                                    false, false, false));
                }

                Hpbsys.NOW = true;

                sender.sendMessage("[hapiba] start.");
                return true;
            }

            if (args[0].equalsIgnoreCase("end")) {
                // みんなクリエイティブモードにするよ
                for(Player p: Bukkit.getOnlinePlayers()) {
                    p.setGameMode(GameMode.CREATIVE);
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                }
                Hpbsys.NOW = false;

                sender.sendMessage("[hapiba] end.");
                return true;
            }

            if (args[0].equalsIgnoreCase("head")) {
                // 頭あげる
                if (!(sender instanceof Player)) {
                    sender.sendMessage(senderErrorMessage);
                    return true;
                }
                if (args.length != 2) {
                    sender.sendMessage("[hapiba] usage: /hapiba head <Player name>");
                    return true;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                skullMeta.setOwningPlayer(target);
                skull.setItemMeta(skullMeta);

                ((Player) sender).getInventory().addItem(skull);

                return true;
            }

            else {
                return false;
            }
        }

        return false;
    }


    /*
    パクリ元:
        https://www.spigotmc.org/threads/tab-complete.160308/
    */
    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], Arrays.asList(SUBCOMMANDS), completions);
        Collections.sort(completions);
        return completions;
    }
}
