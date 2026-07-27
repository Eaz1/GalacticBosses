package me.eaz.galacticbosses.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.eaz.galacticbosses.GalacticBosses;
import org.bukkit.OfflinePlayer;

public class GalacticBossesExpansion extends PlaceholderExpansion {

    private final GalacticBosses plugin;

    public GalacticBossesExpansion(GalacticBosses plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "galacticbosses";
    }

    @Override
    public String getAuthor() {
        return "Eaz";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if (params.equalsIgnoreCase("boss_active")) {
            return plugin.getBossManager().hasBoss() ? "true" : "false";
        }

        if (params.equalsIgnoreCase("boss_health")) {
            if (!plugin.getBossManager().hasBoss()) {
                return "0";
            }

            return String.valueOf((int) plugin.getBossManager().getBoss().getHealth());
        }

        if (params.equalsIgnoreCase("boss_max_health")) {
            if (!plugin.getBossManager().hasBoss()) {
                return "0";
            }

            return String.valueOf((int) plugin.getBossManager().getBoss().getMaxHealth());
        }

        return null;
    }
                                  }
