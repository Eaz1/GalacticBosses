package me.eaz.galacticbosses.commands;

import me.eaz.galacticbosses.GalacticBosses;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BossInfoCommand implements CommandExecutor {

    private final GalacticBosses plugin;

    public BossInfoCommand(GalacticBosses plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(ChatColor.DARK_PURPLE + "===== Galactic Boss =====");
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Boss Active: "
                + (plugin.getBossManager().hasBoss() ? ChatColor.GREEN + "Yes" : ChatColor.RED + "No"));

        if (plugin.getBossManager().hasBoss()) {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Health: "
                    + (int) plugin.getBossManager().getBoss().getHealth()
                    + "/" +
                    (int) plugin.getBossManager().getBoss().getMaxHealth());
        }

        return true;
    }
}
