package me.bigwriter.headmsgs.commands;

import me.bigwriter.headmsgs.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class EditHead implements CommandExecutor {


    Main plugin = Main.getPlugin();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("edithead")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cYou can not run this command from the console.\n");
                return true;
            }
            Player p = (Player) sender;
            if (p.getItemInHand().getType() != Material.SKULL_ITEM) {
                p.sendMessage("§cYou need to have a headache to carry on.");
                return true;
            }
            if(args.length == 0 || args.length > 1){
             p.sendMessage("§cUse: /edithead <name>");
             return true;
            }
            String name = args[0];
            ItemStack is = p.getItemInHand();
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(name);
            if(im.hasLore()) {
                im.getLore().set(2, "§c:-)");
            }else{
                im.setLore(Arrays.asList("", "", "§c:-)"));
            }
            is.setItemMeta(im);
            p.setItemInHand(is);
            p.sendMessage("§aYou've made an edited head, just put it on the floor.");
            p.playSound(p.getLocation(), Sound.ANVIL_USE, 3f, 3f);
        }
        return false;
    }
}
