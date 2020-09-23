package net.teamuni.mobmanager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Mobmanager extends JavaPlugin {
    private FileConfiguration message;

    @Override
    public void onEnable() {
        //create message file
        File messageFile = new File(this.getDataFolder(), "message.yml");
        if(!messageFile.exists()) {
            this.saveResource("message.yml", false);
        }
        this.message = YamlConfiguration.loadConfiguration(messageFile);

        //create config file
        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new MobListener(this.getConfig()), this);
        this.getLogger().info(this.message.getString("reload-complete"));
    }

    @Override
    public void onDisable() {

    }
}
