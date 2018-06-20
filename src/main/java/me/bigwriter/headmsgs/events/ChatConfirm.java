package me.bigwriter.headmsgs.events;

import me.bigwriter.headmsgs.Main;
import me.bigwriter.headmsgs.objects.Object;
import me.bigwriter.headmsgs.utils.DataManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Iterator;

public class ChatConfirm implements Listener {

    Main plugin = Main.getPlugin();
    DataManager data = DataManager.getInstance();

    @EventHandler
    public void onConfirm(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (plugin.verify.containsKey(p.getUniqueId())) {
                for (Iterator<String> i = plugin.name.iterator(); i.hasNext();) {
                    String ss = i.next();
                    if (e.getMessage().startsWith("cancel")) {
                    p.sendMessage(plugin.getConfig().getString("Msgs.CreateCancel").replace("&", "§"));
                    e.getRecipients().remove(p);
                    plugin.verify.remove(p.getUniqueId());
                    i.remove();
                    return;
                }
                Location loc = plugin.verify.get(p.getUniqueId());
                Object obj = new Object(p, loc);
                System.out.println(obj.getLoc());
                String format = String.valueOf(loc.getWorld().getName()) + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch() + "->" + p.getName() + "->" + e.getMessage();
                if (data.getData().isSet("Heads." + ss)) {
                    e.getPlayer().sendMessage(plugin.getConfig().getString("Msgs.ContainsDB").replace("&", "§"));
                      i.remove();
                    plugin.verify.remove(p.getUniqueId());
                    e.getRecipients().remove(p);
                    return;
                }
                data.getData().set("Heads." + ss, format);
                try {
                    data.getData().set("Heads." + ss, format);
                    data.saveData();
                    e.getPlayer().sendMessage(plugin.getConfig().getString("Msgs.CreateComplete").replace("&", "§").replace("{head_name}", ss));
                    e.getRecipients().remove(p);
                    plugin.verify.remove(p.getUniqueId());
              i.remove();
                } catch (Exception ex) {
                    e.getPlayer().sendMessage("§cAn error occurred. Please tell an administrator.");
                    ex.printStackTrace();
                }
            }
        }
    }
}
