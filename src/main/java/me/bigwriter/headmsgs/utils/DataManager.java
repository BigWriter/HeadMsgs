package me.bigwriter.headmsgs.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class DataManager {

    static DataManager instance;
    Plugin p;
    FileConfiguration data;
    File dfile;

    static {
        DataManager.instance = new DataManager();
    }

    public static DataManager getInstance() {
        return DataManager.instance;
    }

    public void setup(final Plugin p) {
        this.dfile = new File(p.getDataFolder(), "heads.yml");
        if (!this.dfile.exists()) {
            try {
                this.dfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create heads.yml!");
            }
        }
        this.data = (FileConfiguration) YamlConfiguration.loadConfiguration(this.dfile);
    }

    public FileConfiguration getData() {
        return this.data;
    }

    public void saveData() {
        try {
            this.data.save(this.dfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save heads.yml!");
        }
    }

    public void reloadData() {
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dfile);
    }

}
