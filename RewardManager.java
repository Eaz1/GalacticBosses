package me.eaz.galacticbosses.rewards;

import me.eaz.galacticbosses.GalacticBosses;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RewardManager {

    private final GalacticBosses plugin;
    private final Map<UUID, Double> damageMap = new HashMap<>();

    public RewardManager(GalacticBosses plugin) {
        this.plugin = plugin;
    }

    public void addDamage(UUID player, double damage) {
        damageMap.put(player, damageMap.getOrDefault(player, 0.0) + damage);
    }

    public Map<UUID, Double> getDamageMap() {
        return damageMap;
    }

    public UUID getTopDamager() {
        return damageMap.entrySet()
                .stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public void clear() {
        damageMap.clear();
    }

    public void clearDamage() {
        clear();
    }

    public void rewardAllOnline(double amount) {
        if (plugin.getEconomy() == null) {
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            plugin.getEconomy().depositPlayer(player, amount);
            player.sendMessage("§aYou received $" + (int) amount + "!");
        }
    }
}
