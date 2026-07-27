package me.eaz.galacticbosses.listeners;

import me.eaz.galacticbosses.GalacticBosses;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Map;
import java.util.UUID;

public class BossDeathListener implements Listener {

    private final GalacticBosses plugin;

    public BossDeathListener(GalacticBosses plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBossDeath(EntityDeathEvent event) {

        if (!(event.getEntity() instanceof Wither)) {
            return;
        }

        if (!plugin.getBossManager().hasBoss()) {
            return;
        }

        Wither boss = plugin.getBossManager().getBoss();

        if (!event.getEntity().getUniqueId().equals(boss.getUniqueId())) {
            return;
        }

        event.getDrops().clear();
        event.setDroppedExp(0);

        UUID winner = plugin.getRewardManager().getTopDamager();

        for (Map.Entry<UUID, Double> entry : plugin.getRewardManager().getDamageMap().entrySet()) {

            Player player = Bukkit.getPlayer(entry.getKey());

            if (player == null) {
                continue;
            }

            // Money reward
            double reward = plugin.getConfig().getDouble("boss.participant-reward", 5000);

            if (reward > 0 && plugin.getEconomy() != null) {
                plugin.getEconomy().depositPlayer(player, reward);
            }

            // Configurable commands
            for (String command : plugin.getConfig().getStringList("boss.participant-commands")) {
                Bukkit.dispatchCommand(
                        Bukkit.getConsoleSender(),
                        command.replace("%player%", player.getName())
                );
            }

            player.sendMessage(ChatColor.GREEN + "You received your participation rewards!");
        }

        if (winner != null) {

            Player player = Bukkit.getPlayer(winner);

            if (player != null) {

                double reward = plugin.getConfig().getDouble("boss.top-damage-reward", 10000);

                if (reward > 0 && plugin.getEconomy() != null) {
                    plugin.getEconomy().depositPlayer(player, reward);
                }

                // Configurable commands
                for (String command : plugin.getConfig().getStringList("boss.top-damage-commands")) {
                    Bukkit.dispatchCommand(
                            Bukkit.getConsoleSender(),
                            command.replace("%player%", player.getName())
                    );
                }

                Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD
                        + player.getName()
                        + ChatColor.LIGHT_PURPLE
                        + " dealt the most damage!");
            }
        }

        plugin.getRewardManager().clear();

        if (plugin.getBossManager().hasBoss()) {
            plugin.getBossManager().getBoss().remove();
        }

        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The Galactic Wither has been defeated!");
    }
            }
