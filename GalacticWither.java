package me.eaz.galacticbosses.bosses;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wither;

public class GalacticWither {

    private final Wither wither;

    public GalacticWither(Location location, String name, double health) {
        this.wither = (Wither) location.getWorld().spawnEntity(location, EntityType.WITHER);

        wither.setCustomName(name);
        wither.setCustomNameVisible(true);
        wither.setRemoveWhenFarAway(false);

        if (wither.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        }

        wither.setHealth(health);
    }

    public Wither getEntity() {
        return wither;
    }

    public boolean isAlive() {
        return wither != null && !wither.isDead();
    }

    public void remove() {
        if (isAlive()) {
            wither.remove();
        }
    }
}
