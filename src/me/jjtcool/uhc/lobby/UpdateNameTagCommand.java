package me.jjtcool.uhc.lobby;

import ca.wacos.nametagedit.NametagAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created with IntelliJ IDEA.
 * User: JJtCool
 * Date: 12/08/14
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public class UpdateNameTagCommand implements Listener, CommandExecutor{

    @EventHandler
    public boolean onCommand(CommandSender sender, Command command,
                             String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("setmynametag")) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Something went wrong. Try: /setmynametag <YourName>");
                }
                  else {
                    sender.sendMessage(ChatColor.RED + "Access denied... BUUUZZZZZ");
                }
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("setmynametag")) {
                        if (args[1].equalsIgnoreCase("owner")) {
                            String prefix8 = new String(ChatColor.RED + "" + ChatColor.BOLD + "Owner " + ChatColor.WHITE);
                            NametagAPI.resetNametag(((Player) sender).getPlayer().getName());
                            NametagAPI.setPrefix(player.getPlayer().getName(),prefix8);
                            ((Player) sender).getPlayer().sendMessage(ChatColor.YELLOW + "Updated your nametag prefix to:" + prefix8);

                        }
                    }
                    if (args[0].equalsIgnoreCase("setmynametag")) {
                        if (args[1].equalsIgnoreCase("operator")) {
                            String prefix7 = new String(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Operator " + ChatColor.WHITE);
                            NametagAPI.resetNametag(((Player) sender).getPlayer().getName());
                            NametagAPI.setPrefix(player.getPlayer().getName(),prefix7);
                            ((Player) sender).getPlayer().sendMessage(ChatColor.YELLOW + "Updated your nametag prefix to:" + prefix7);
                        }
                    }
                    if (args[0].equalsIgnoreCase("setmynametag")) {
                        if (args[1].equalsIgnoreCase("admin")) {
                            String prefix6 = new String(ChatColor.GOLD + "" + ChatColor.BOLD + "Admin " + ChatColor.WHITE);
                            NametagAPI.resetNametag(((Player) sender).getPlayer().getName());
                            NametagAPI.setPrefix(player.getName(),prefix6);
                            ((Player) sender).getPlayer().sendMessage(ChatColor.YELLOW + "Updated your nametag prefix to:" + prefix6);
                        }
                    }
                    if (args[0].equalsIgnoreCase("setmynametag")) {
                        if (args[1].equalsIgnoreCase("mod")) {
                            String prefix5 = new String(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Mod " + ChatColor.WHITE);
                            NametagAPI.resetNametag(((Player) sender).getPlayer().getName());
                            NametagAPI.setPrefix(player.getName(), prefix5);
                            ((Player) sender).getPlayer().sendMessage(ChatColor.YELLOW + "Updated your nametag prefix to:" + prefix5);
                        }
                    }
                }


                return true;
            }
        }
        return false;
    }
}
