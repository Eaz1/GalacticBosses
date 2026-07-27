package me.eaz.galacticbosses;

import me.eaz.galacticbosses.bosses.BossManager;
import me.eaz.galacticbosses.commands.BossInfoCommand;
import me.eaz.galacticbosses.listeners.BossDamageListener;
import me.eaz.galacticbosses.rewards.RewardManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class GalacticBosses extends JavaPlugin {

    private static GalacticBosses instance;

    private BossManager bossManager;
    private RewardManager rewardManager;
    private Economy economy;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        if (!setupEconomy()) {
            getLogger().severe("Vault economy not found! Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        bossManager = new BossManager(this);
        rewardManager = new RewardManager(this);

        if (getCommand("bossinfo") != null) {
            getCommand("bossinfo").setExecutor(new BossInfoCommand(this));
        }

        Bukkit.getPluginManager().registerEvents(new BossDamageListener(this), this);

        // Voting is now handled entirely by GalacticVotes. When its vote
        // party threshold is reached, GalacticVotes calls this plugin's
        // BossManager directly (see VotePartyManager in GalacticVotes) -
        // no vote listener or vote event system is needed here anymore.

        getLogger().info("GalacticBosses enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GalacticBosses disabled!");
    }

    private boolean setupEconomy() {

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp =
                getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            return false;
        }

        economy = rsp.getProvider();
        return economy != null;
    }

    public static GalacticBosses getInstance() {
        return instance;
    }

    public BossManager getBossManager() {
        return bossManager;
    }

    public RewardManager getRewardManager() {
        return rewardManager;
    }

    public Economy getEconomy() {
        return economy;
    }
}
