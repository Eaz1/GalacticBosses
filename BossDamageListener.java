package me.eaz.galacticbosses.listeners;

import me.eaz.galacticbosses.GalacticBosses;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BossDamageListener implements Listener {

    private final GalacticBosses plugin;

    public BossDamageListener(GalacticBosses plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBossDamage(EntityDamageByEntityEvent event) {

        if (!plugin.getBossManager().hasBoss()) {
            return;
        }

        if (!event.getEntity().getUniqueId().equals(plugin.getBossManager().getBoss().getUniqueId())) {
            return;
        }

        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();

        plugin.getRewardManager().addDamage(player.getUniqueId(), event.getFinalDamage());
    }
}
