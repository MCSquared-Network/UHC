package me.jjtcool.uhc.lobby;

import com.dsh105.holoapi.api.Hologram;
import com.dsh105.holoapi.api.HologramFactory;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: JJtCool
 * Date: 08/08/14
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class HoloManager extends JavaPlugin {

    public static com.dsh105.holoapi.api.HoloManager getHoloManager() {
        return com.dsh105.holoapi.HoloAPI.getManager();
    }

    Hologram hologram = new HologramFactory(this)    // Replace "myPlugin" with your plugin instance
            .withLocation(new Vector(1, 1, 1), "uhclobby")
            .withText(ChatColor.GRAY + "====================")
            .withText(ChatColor.BOLD + "" + ChatColor.GREEN + "Welcome to the MCSquared Network " + ChatColor.BOLD + "" + ChatColor.RED + "UHC")
            .withText("")
            .withText(ChatColor.BOLD + "" + ChatColor.AQUA + "Enter the lobby by going through the portal!")
            .withText(ChatColor.RED + "<" + ChatColor.GRAY + "--------" + ChatColor.RED + ">")
            .withText("")
            .withText(ChatColor.BLUE + "Welcome to a network, with community at it's core. Here at MCSquared we strive to have the best " + ChatColor.BLUE +
                    "community as we feel that's the heart of a good server, along with the infrastructure needed to run the gamemodes players love.")
            .withText("")
            .withText(ChatColor.YELLOW + "This is a pre-release for the MCSquared Network, ")
            .withText(ChatColor.YELLOW + "we are working as hard as we can to get everything ready!")
            .withText(ChatColor.LIGHT_PURPLE + "Check out our website - " + ChatColor.BOLD + "" + ChatColor.GOLD + "www.mc-sq.net")
            .withText(ChatColor.AQUA + "Check out the Developers Blog - www.mc-sq.net/developersblog.html")
            .withText(ChatColor.GRAY + "====================")
            .build();

}
