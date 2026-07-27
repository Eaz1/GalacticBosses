package me.eaz.galacticbosses.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;

public final class BossUtils {

    private BossUtils() {
    }

    public static Wither spawnBoss(Location location, String name, double health) {

        Wither wither = (Wither) location.getWorld().spawnEntity(location, EntityType.WITHER);

        wither.setCustomName(name);
        wither.setCustomNameVisible(true);
        wither.setRemoveWhenFarAway(false);

        if (wither.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        }

        wither.setHealth(health);

        return wither;
    }

    public static Player getNearestPlayer(Location location) {

        World world = location.getWorld();

        if (world == null) {
            return null;
        }

        Player nearest = null;
        double closest = Double.MAX_VALUE;

        for (Player player : world.getPlayers()) {

            double distance = player.getLocation().distanceSquared(location);

            if (distance < closest) {
                closest = distance;
                nearest = player;
            }
        }

        return nearest;
    }
            }
