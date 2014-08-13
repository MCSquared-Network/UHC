package me.jjtcool.uhc.lobby;

import ca.wacos.nametagedit.NametagAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created with IntelliJ IDEA.
 * User: JJtCool
 * Date: 13/08/14
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public class updatePlayerNametag implements Listener {
    public static void updateNametag(Player player) {

            if(player.hasPermission("mcsq.donator.supporter")){
                String prefix1 = new String(ChatColor.BLUE + "" + ChatColor.BOLD + "Supporter " + ChatColor.WHITE);
                NametagAPI.setPrefix(player.getName(), prefix1);
            }
            if(player.hasPermission("mcsq.donator.captain")){
                String prefix2 = new String(ChatColor.GREEN + "" + ChatColor.BOLD + "Captain " + ChatColor.WHITE);
                NametagAPI.setPrefix(player.getName(), prefix2);
            }
            if(player.hasPermission("mcsq.donator.ultra")){
                String prefix3 = new String(ChatColor.AQUA + "" + ChatColor.BOLD + "Ultra " + ChatColor.WHITE);
                NametagAPI.setPrefix(player.getName(), prefix3);
            }
            if(player.hasPermission("mcsq.donator.hero")){
                String prefix4 = new String(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Hero " + ChatColor.WHITE);
                NametagAPI.setPrefix(player.getName(), prefix4);
            }
            if (player.hasPermission("mcsq.staff.mod")){
                String prefix5 = new String(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Mod " + ChatColor.WHITE);
                NametagAPI.setPrefix(player.getName(), prefix5);
            }
            if(player.hasPermission("mcsq.staff.admin")){
                String prefix6 = new String(ChatColor.GOLD + "" + ChatColor.BOLD + "Admin " + ChatColor.WHITE);
                NametagAPI.setPrefix(player.getName(), prefix6);
            }
            if(player.hasPermission("mcsq.staff.operator")){
                String prefix7 = new String(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Operator " + ChatColor.WHITE);
                NametagAPI.setPrefix(player.getName(), prefix7);
            }
            if(player.hasPermission("mcsq.staff.owner")){
                String prefix8 = new String(ChatColor.RED + "" + ChatColor.BOLD + "Owner " + ChatColor.WHITE);
                NametagAPI.setPrefix(player.getName(), prefix8);
            }

    }
}
