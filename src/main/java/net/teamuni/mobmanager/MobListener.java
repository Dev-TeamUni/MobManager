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
        //몹 데미지 상활별 제한
        if(event.getEntity() instanceof Mob
                && this.config.getBoolean("MOB_DAMAGE_REASON."+event.getCause().name())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpawnCreature(CreatureSpawnEvent event) {
        //월드별 스폰 제한 확인
        String world = event.getLocation().getWorld().getName();
        if(this.config.isSet(world)) {
            String reason = world + ".MOB_SPAWN_REASON." + event.getSpawnReason().name();
            String spawn = world + ".MOB_SPAWN." + event.getEntityType().name();
            if(this.config.isBoolean(spawn)) {
                event.setCancelled(!this.config.getBoolean(spawn));
            }
            if(this.config.isBoolean(reason)) {
                event.setCancelled(!this.config.getBoolean(reason));
            }
        }

        //몹 상황별 스폰 제한
        if(event.getEntity() instanceof Mob
                && this.config.getBoolean("MOB_SPAWN_REASON"+event.getSpawnReason().name())) {
            event.setCancelled(true);
        }

        //몹 종류별 스폰 제한
        if(event.getEntity() instanceof Mob
                && this.config.getBoolean("MOB_SPAWN."+event.getEntityType().name())) {
            event.setCancelled(true);
        }
    }
}
