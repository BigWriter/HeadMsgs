package me.bigwriter.headmsgs.events;

import me.bigwriter.headmsgs.Main;
import me.bigwriter.headmsgs.utils.DataManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractHead implements Listener {

    Main plugin = Main.getPlugin();
    DataManager data = DataManager.getInstance();


    @EventHandler
    public void onHeadInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null
                && e.getClickedBlock().getType().equals(Material.SKULL)) {
            for (String s : data.getData().getConfigurationSection("Heads").getKeys(false)) {
                String[] split = data.getData().getString("Heads." + s).split(";");
                String[] infos = data.getData().getString("Heads." + s).split("->");
                String name = String.valueOf(infos[1]);
                String msg = String.valueOf(infos[2].replace("&", "§"));
                Location loc = new Location(plugin.getServer().getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), 0.0f, 0.0f);
                Location locbr = new Location(plugin.getServer().getWorld(b.getLocation().getWorld().getName()), b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ(), 0.0f, 0.0f);
                String format = String.valueOf(loc.getWorld().getName()) + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ();
                if (locbr.equals(loc)) {
                    if (p.isSneaking()) {
                        if (p.hasPermission("headmsgs.admin")) {
                            for(String br : plugin.getConfig().getStringList("Msgs.HeadInfo")){
                              p.sendMessage(br.replace("&", "§").replace("{head_name}", s).replace("{player_name}", name).replace("{msg}", msg).replace("{location}", format));
                            }
                            return;
                        }
                    }
                    p.sendMessage(msg);
                    p.playSound(p.getLocation(), Sound.CLICK, 3f, 3f);
                }
            }
        }
    }
}
