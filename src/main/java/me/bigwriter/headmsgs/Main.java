package me.bigwriter.headmsgs;

import com.google.common.io.Resources;
import me.bigwriter.headmsgs.commands.EditHead;
import me.bigwriter.headmsgs.events.BreakHead;
import me.bigwriter.headmsgs.events.ChatConfirm;
import me.bigwriter.headmsgs.events.InteractHead;
import me.bigwriter.headmsgs.events.PlaceHead;
import me.bigwriter.headmsgs.utils.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

     DataManager data = DataManager.getInstance();
     public HashMap<UUID, Location> verify = new HashMap<UUID, Location>();
     public ArrayList<String> name = new ArrayList<String>();

    @Override
    public void onEnable() {
        data.setup(this);
        saveDefaultConfig();
        utf8("config.yml");
        utf8("heads.yml");
        getServer().getPluginManager().registerEvents(new ChatConfirm(), this);
        getServer().getPluginManager().registerEvents(new InteractHead(), this);
        getServer().getPluginManager().registerEvents(new PlaceHead(), this);
        getServer().getPluginManager().registerEvents(new BreakHead(), this);
        getCommand("edithead").setExecutor(new EditHead());

    }

    public static Main getPlugin() {
        return (Main) Bukkit.getServer().getPluginManager().getPlugin("HeadMsgs");
    }

    public void utf8(String config){
        try {
            final File file = new File(this.getDataFolder() + File.separator, config);
            final String allText = Resources.toString(file.toURI().toURL(), Charset.forName("UTF-8"));
            this.getConfig().loadFromString(allText);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
