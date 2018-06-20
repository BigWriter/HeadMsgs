package me.bigwriter.headmsgs.events;

import me.bigwriter.headmsgs.Main;
import me.bigwriter.headmsgs.utils.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakHead implements Listener {

    Main plugin = Main.getPlugin();
    DataManager data = DataManager.getInstance();

    @EventHandler
    public void breakHead(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (e.getBlock().getType().equals(Material.SKULL)) {
            for (String s : data.getData().getConfigurationSection("Heads").getKeys(false)) {
                String[] split = data.getData().getString("Heads." + s).split(";");
                Location loc = new Location(plugin.getServer().getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), 0.0f, 0.0f);
                Location locbr = new Location(plugin.getServer().getWorld(b.getLocation().getWorld().getName()), b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ(), 0.0f, 0.0f);
                if (locbr.equals(loc)) {
                    if (p.isSneaking()) {
                        if (p.hasPermission("headmsgs.admin")) {
                            p.sendMessage(plugin.getConfig().getString("Msgs.BreakHead").replace("&", "§").replace("{head_name}", s));
                            data.getData().set("Heads." + s, null);
                            data.saveData();
                            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 2f, 2f);
                            return;
                        }
                    }
                }
            }
        }


    }


}
