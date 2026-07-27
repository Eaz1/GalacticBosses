package me.eaz.galacticbosses.commands;

import me.eaz.galacticbosses.GalacticBosses;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BossTopCommand implements CommandExecutor {

    private final GalacticBosses plugin;

    public BossTopCommand(GalacticBosses plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!plugin.getBossManager().hasBoss()) {
            sender.sendMessage(ChatColor.RED + "There is no active Galactic Boss.");
            return true;
        }

        sender.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "=== Boss Damage ===");

        int position = 1;

        for (Map.Entry<UUID, Double> entry :
                plugin.getRewardManager().getDamageMap().entrySet().stream()
                        .sorted(Map.Entry.<UUID, Double>comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toList())) {

            OfflinePlayer player = Bukkit.getOfflinePlayer(entry.getKey());

            sender.sendMessage(
                    ChatColor.LIGHT_PURPLE + "#" + position +
                    ChatColor.GRAY + " - " +
                    ChatColor.WHITE + (player.getName() == null ? "Unknown" : player.getName()) +
                    ChatColor.GRAY + " : " +
                    ChatColor.GREEN + String.format("%.1f", entry.getValue())
            );

            if (++position > 10) {
                break;
            }
        }

        return true;
    }
            }
