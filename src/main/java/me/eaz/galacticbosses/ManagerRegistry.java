package me.eaz.galacticbosses.managers;

import me.eaz.galacticbosses.GalacticBosses;
import me.eaz.galacticbosses.bosses.BossManager;
import me.eaz.galacticbosses.rewards.RewardManager;

public class ManagerRegistry {

    private final GalacticBosses plugin;

    private final BossManager bossManager;
    private final RewardManager rewardManager;

    public ManagerRegistry(GalacticBosses plugin) {
        this.plugin = plugin;

        this.bossManager = new BossManager(plugin);
        this.rewardManager = new RewardManager(plugin);
    }

    public GalacticBosses getPlugin() {
        return plugin;
    }

    public BossManager getBossManager() {
        return bossManager;
    }

    public RewardManager getRewardManager() {
        return rewardManager;
    }
}
