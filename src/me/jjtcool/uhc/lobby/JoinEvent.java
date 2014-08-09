package me.jjtcool.uhc.lobby;

/**
 * Created with IntelliJ IDEA.
 * User: ${JJtCool}
 * Date: 08/08/14
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */

import ca.wacos.nametagedit.NametagAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

/**
 * Created with IntelliJ IDEA.
 * User: Richard
 * Date: 07/08/14
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class JoinEvent extends JavaPlugin implements Listener {

    private org.bukkit.scoreboard.Scoreboard board;

    public int squares;

    public String rank;

    private static Team players;

    private static Team supporter;

    private static Team captain;

    private static Team ultra;

    private static Team hero;

    private static Team mod;

    private static Team admin;

    private static Team manager;

    private static Team owner;


    public void createScoreboard(final Player player) {
        board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        final Objective obj = board.registerNewObjective("Kills", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        final Score score1 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + "" + ChatColor.GREEN + "Squares: "));

        final Score scoreSquares = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "" + Gamers.getGamer(player).getSquares()));

        final Score score2 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + "" + ChatColor.AQUA + "Rank: "));

        final Score scoreRank = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.AQUA + "" + Gamers.getGamer(player).getRank()));

        final Score score3 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + "" + ChatColor.AQUA + "Online: "));

        final Score scoreOnline = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.AQUA + "" + Bukkit.getOnlinePlayers() + " Players online"));

        final Score score4 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + "" + ChatColor.GREEN + "Server: "));

        final Score scoreServer = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "" + Bukkit.getServer().getName()));

        final Score score5 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + "" + ChatColor.YELLOW + "Website: "));

        final Score scoreWebsite = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "www.mc-sq.net"));

        final Scroller scroller = new Scroller("&lWelcome " + player.getName() + " To The MCSquared Network!", 16, 12, '&');

        new BukkitRunnable() {
            public void run() {
                try {
                    if (player != null) {
                        String next = scroller.next();
                        obj.setDisplayName(next);
                        player.setScoreboard(board);
                    }
                } catch (Exception e) {
                    this.cancel();
                }
            }
        }.runTaskTimer(this, 0L, 5L);

        players = board.registerNewTeam("players");
        supporter = board.registerNewTeam("supporter");
        captain = board.registerNewTeam("captain");
        ultra = board.registerNewTeam("ultra");
        hero = board.registerNewTeam("hero");
        mod = board.registerNewTeam("mod");
        admin = board.registerNewTeam("admin");
        manager = board.registerNewTeam("manager");
        owner = board.registerNewTeam("owner");

        supporter.setDisplayName(ChatColor.BOLD + "" + ChatColor.BLUE + "Supporter ");

        captain.setDisplayName(ChatColor.BOLD + "" + ChatColor.GREEN + "Captain ");

        ultra.setDisplayName(ChatColor.BOLD + "" + ChatColor.AQUA + "Ultra ");

        hero.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Hero ");

        mod.setDisplayName(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "Mod ");

        admin.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Admin ");

        manager.setDisplayName(ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Manager ");

        owner.setDisplayName(ChatColor.BOLD + "" + ChatColor.RED + "Owner ");

        score1.setScore(1);

        scoreSquares.setScore(2);

        score2.setScore(3);

        scoreRank.setScore(4);

        score3.setScore(5);

        scoreOnline.setScore(6);

        score4.setScore(7);

        scoreServer.setScore(8);

        score5.setScore(9);

        scoreWebsite.setScore(10);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        createScoreboard(player);
        String playerName = event.getPlayer().getName();
        Gamer gamer = Gamers.getGamer(player);
        String uuid = player.getUniqueId().toString();
        rank = gamer.getRank();
        squares = gamer.getSquares();
        if(player.hasPermission("mcsq.donator.supporter")){
            String prefix = new String(ChatColor.BOLD + "" + ChatColor.BLUE + "Supporter ");
            NametagAPI.setPrefix(playerName, prefix);
            supporter.addPlayer(player);
        }
        if(player.hasPermission("mcsq.donator.captain")){
            String prefix = new String(ChatColor.BOLD + "" + ChatColor.GREEN + "Captain ");
            NametagAPI.setPrefix(playerName, prefix);
            captain.addPlayer(player);
        }
        if(player.hasPermission("mcsq.donator.ultra")){
            String prefix = new String(ChatColor.BOLD + "" + ChatColor.AQUA + "Ultra ");
            NametagAPI.setPrefix(playerName, prefix);
            ultra.addPlayer(player);
        }
        if(player.hasPermission("mcsq.donator.hero")){
            String prefix = new String(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Hero ");
            NametagAPI.setPrefix(playerName, prefix);
            hero.addPlayer(player);
        }
        if (player.hasPermission("mcsq.staff.mod")){
            String prefix = new String(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "Mod ");
            NametagAPI.setPrefix(playerName, prefix);
            mod.addPlayer(player);
        }
        if(player.hasPermission("mcsq.staff.admin")){
            String prefix = new String(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Admin ");
            NametagAPI.setPrefix(playerName, prefix);
            admin.addPlayer(player);
        }
        if(player.hasPermission("mcsq.staff.manager")){
            String prefix = new String(ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Manager ");
            NametagAPI.setPrefix(playerName, prefix);
            manager.addPlayer(player);
        }
        if(player.hasPermission("mcsq.staff.owner")){
            String prefix = new String(ChatColor.BOLD + "" + ChatColor.RED + "Owner ");
            NametagAPI.setPrefix(playerName, prefix);
            owner.addPlayer(player);
        }

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) book.getItemMeta();
        bm.setTitle("§3§lMCSquared Network");
        bm.setAuthor("§3§lMCSquared Network");
        bm.setPages("§c-= " + "§a§lUHC" +
                " §c=-" + "\n§bWelcome to the MCSquared Network UHC! We have launched UHC as a pre-release to the official network, of which you can find out loads more on our website!"
                + "\n\n§9To find out more, and to add to your experience make sure you visit our website - §6mc-sq.net"
        );
        book.setItemMeta(bm);
        player.getInventory().addItem(book);
    }


}
