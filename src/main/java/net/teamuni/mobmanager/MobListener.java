package net.teamuni.mobmanager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class MobListener implements Listener {
    private final FileConfiguration config;

    public MobListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onDamageEntity(EntityDamageEvent event) {
        if(event.getEntity() instanceof Mob
                && this.config.getBoolean("MOB_DAMAGE_REASON."+event.getCause().name())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpawnCreature(CreatureSpawnEvent event) {
        if(event.getEntity() instanceof Mob
                && this.config.getBoolean("MOB_SPAWN_REASON"+event.getSpawnReason().name())) {
            event.setCancelled(true);
        }
    }
}
