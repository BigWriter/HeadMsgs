package me.bigwriter.headmsgs.events;

import me.bigwriter.headmsgs.Main;
import me.bigwriter.headmsgs.utils.DataManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlaceHead implements Listener {

    Main plugin = Main.getPlugin();
    DataManager data = DataManager.getInstance();

    @EventHandler
    public void onHeadPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemStack is = e.getItemInHand();
        if (is.getType() == Material.SKULL_ITEM) {
            if (is.getItemMeta().getDisplayName() == null && is.getItemMeta().getLore() == null)
                return;
            ItemMeta meta = is.getItemMeta();
            Block b = e.getBlock();
            if (meta.getLore().get(2).contains("§c:-)")) {
                if (p.hasPermission("headmsgs.admin")) {
                    for (String confirm : plugin.getConfig().getStringList("Msgs.ConfirmName")) {
                        e.getPlayer().sendMessage(confirm.replace("&", "§"));
                    }
                    plugin.verify.put(e.getPlayer().getUniqueId(), b.getLocation());
                    plugin.name.add(meta.getDisplayName());
                }
            }
        }
    }


}
