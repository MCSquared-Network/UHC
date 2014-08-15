package me.jjtcool.uhc.lobby;

/**
 * Created with IntelliJ IDEA.
 * User: JJtCool
 * Date: 08/08/14
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */

import ca.wacos.nametagedit.NametagAPI;
import com.dsh105.holoapi.api.Hologram;
import com.dsh105.holoapi.api.HologramFactory;
import com.gmail.filoghost.holograms.api.HolographicDisplaysAPI;
import me.confuser.barapi.BarAPI;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.bukkit.util.Vector;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Richard
 * Date: 07/08/14
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class JoinEvent extends JavaPlugin implements Listener {

    public static Scoreboard board;

    private static Team players;

    private static Team supporter;

    private static Team captain;

    private static Team ultra;

    private static Team hero;

    private static Team mod;

    private static Team admin;

    private static Team manager;

    private static Team owner;
    Location holoLocation = new Location(Bukkit.getServer().getWorld("uhclobby"), 44.5, 40, -187.5);

    public state.gamestate state;

    private List<Runnable> scrollers = new ArrayList<Runnable>();

    public void onEnable() {
        MySQL.openConnection();
        Bukkit.getConsoleSender().sendMessage("opening db connection " + MySQL.connection);
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(this, this);


        getCommand("setmynametag").setExecutor(new UpdateNameTagCommand());

        state = me.jjtcool.uhc.lobby.state.gamestate.B1;

        new BukkitRunnable() {
            public void run() {
                try {
                    for (Runnable scroller :scrollers) {
                        scroller.run();
                    }
                } catch (Exception e) {
                    this.cancel();
                }
            }
        }.runTaskTimer(this, 0L, 5L);
    }

    public void showBarChanging(final Player player) {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
         switch (state) {

                            case B1:
                                broadcast(ChatColor.AQUA + "" + ChatColor.BOLD + "Welcome " + ChatColor.YELLOW + "" + ChatColor.BOLD + player.getName() +
                                        ChatColor.AQUA + "" + ChatColor.BOLD + " to the "
                                        + ChatColor.GREEN + "" + ChatColor.BOLD + "MCSquared Network!", 100f);
                                state = me.jjtcool.uhc.lobby.state.gamestate.B2;
                                break;
                            case B2:
                                broadcast(ChatColor.AQUA + "" + ChatColor.BOLD + Bukkit.getOnlinePlayers().length + ChatColor.RED + "/" + ChatColor.AQUA + "" + ChatColor.BOLD + Bukkit.getMaxPlayers() + " Players Currently Online" , 80f);
                                //ChatColor.GREEN + "" + ChatColor.BOLD + "Check out our website - http://www.mc-sq.net", 80f
                                state = me.jjtcool.uhc.lobby.state.gamestate.B3;
                                break;
                            case B3:
                                broadcast(ChatColor.BLUE + "" + ChatColor.BOLD + "Tip: " + ChatColor.GREEN + "" + ChatColor.BOLD + "Visit our website to view your stats!", 60f);
                                state = me.jjtcool.uhc.lobby.state.gamestate.B4;
                                break;
                            case B4:
                                broadcast(ChatColor.GREEN + "" + ChatColor.BOLD + "Updates: " + ChatColor.RED + "" + ChatColor.BOLD + MySQL.getLatestUpdate(), 50f);
                                state = me.jjtcool.uhc.lobby.state.gamestate.B5;
                                break;
                            case B5:
                                broadcast(ChatColor.GREEN + "" + ChatColor.BOLD + "§l/hub" + ChatColor.AQUA + " §lTo Return To The Lobby!", 40f);
                                state = me.jjtcool.uhc.lobby.state.gamestate.B6;
                                break;
                            case B6:
                                broadcast(ChatColor.YELLOW + "" + ChatColor.BOLD + "Check for the latest updates on our website!", 20f);
                                state = me.jjtcool.uhc.lobby.state.gamestate.B7;
                                break;
                            case B7:
                                broadcast(ChatColor.GREEN + "" + ChatColor.BOLD + "PLAY.MC-SQ.NET", 0f);
                                state = me.jjtcool.uhc.lobby.state.gamestate.B1;
                                break;
                            //Incorporate function of retrieving string for BossBar from Database - Allows for quick updates in lobbies
            }
          }
        }, 100L, 100L);
    }



    @EventHandler
    public void serverPingEvent(ServerListPingEvent event) {
        Calendar calendar = Calendar.getInstance();
        event.setMotd("§f[" + ChatColor.AQUA + "" + ChatColor.BOLD + Bukkit.getOnlinePlayers().length + ChatColor.RED +
                "/" + ChatColor.AQUA + "" + ChatColor.BOLD + Bukkit.getMaxPlayers() + "§f]" + ChatColor.GREEN + "" + ChatColor.BOLD + " "
                + Bukkit.getServer().getServerName() + " - " + ChatColor.RED + "" + ChatColor.BOLD + "Server Time: " + ChatColor.YELLOW + "" + ChatColor.BOLD
                + calendar.get(calendar.HOUR_OF_DAY) + ":" + calendar.get(calendar.MINUTE) + ":" + calendar.get(calendar.SECOND)+
                (calendar.get(calendar.AM_PM) == calendar.AM ? " AM" : " PM") +
                "\n" + ChatColor.GREEN + "" + ChatColor.BOLD + "               MCSquared Network" );
    }

    @EventHandler
    public void playerLosesHunger(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void playerLosesHealth(EntityDamageEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void pickUpItem(PlayerDropItemEvent event) {
        if (!event.getPlayer().hasPermission("mcsq.staff")) {
                event.setCancelled(true);
        }
    }
    @EventHandler
    public void pickUpItem(PlayerPickupItemEvent event) {
        if (!event.getPlayer().hasPermission("mcsq.staff")) {
            event.setCancelled(true);
        }
    }


    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            MySQL.setPlayerOffline(player);
        }
        super.onDisable();
        MySQL.closeConnection();
    }

    public void broadcast(String message, float percent) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            BarAPI.setMessage(player, message, percent);
        }
    }

    public void createScoreboard(final Player player) {
        board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        final Objective obj = board.registerNewObjective("Kills", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        final Score score1 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.AQUA + "" + ChatColor.BOLD + "§lOnline:"));

        final Score scoreOnline = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "" + Bukkit.getOnlinePlayers().length));

        final Score score2 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "" + ChatColor.BOLD + "§lServer: "));

        final Score scoreServer = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + Bukkit.getServer().getServerName()));

        final Score score3 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.YELLOW + "" + ChatColor.BOLD +  "§lWebsite: "));

        final Score scoreWebsite = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "www.mc-sq.net"));

        final Scroller scroller =  new Scroller("&lWelcome " + player.getName() + " To The MCSquared Network!", 12, 8, '&');

        players = board.registerNewTeam("players");
        supporter = board.registerNewTeam("supporter");
        captain = board.registerNewTeam("captain");
        ultra = board.registerNewTeam("ultra");
        hero = board.registerNewTeam("hero");
        mod = board.registerNewTeam("mod");
        admin = board.registerNewTeam("admin");
        manager = board.registerNewTeam("manager");
        owner = board.registerNewTeam("owner");

        supporter.setDisplayName(ChatColor.BLUE + "");

        captain.setDisplayName(ChatColor.GREEN + "");

        ultra.setDisplayName(ChatColor.AQUA + "");

        hero.setDisplayName(ChatColor.LIGHT_PURPLE + "");

        mod.setDisplayName(ChatColor.DARK_PURPLE + "");

        admin.setDisplayName(ChatColor.GOLD + "");

        manager.setDisplayName(ChatColor.DARK_AQUA + "");

        owner.setDisplayName(ChatColor.RED + "");

        score1.setScore(6);

        scoreOnline.setScore(5);

        score2.setScore(4);

        scoreServer.setScore(3);

        score3.setScore(2);

        scoreWebsite.setScore(1);

        scrollers.add(new Runnable() {
            @Override
            public void run() {
                try {
                        String next = scroller.next();
                        obj.setDisplayName(next);
                        player.setScoreboard(board);

                } catch (Exception e) {
                }
            }
        });
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.WHITE + "[" + ChatColor.RED + "UHC" + ChatColor.WHITE + "] " + ChatColor.RED + event.getPlayer().getName() + " left");
        MySQL.setPlayerOffline(event.getPlayer());
        event.getPlayer().getInventory().clear();
        event.getPlayer().updateInventory();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        showBarChanging(player);
        event.getPlayer().getInventory().clear();
        event.getPlayer().updateInventory();
        createScoreboard(player);
        MySQL.setPlayerOnline(player);
        updatePlayerNametag.updateNametag(player);


        if (!MySQL.playerDataContainsPLayer(event.getPlayer())) {
            try {
                PreparedStatement newPlayer = MySQL.connection.prepareStatement("INSERT INTO `playerUHCData` values(?,?,?,?,?,?);");
                newPlayer.setString(1, event.getPlayer().getUniqueId().toString());
                newPlayer.setInt(2, 0);
                newPlayer.setInt(3, 0);
                newPlayer.setInt(4, 0);
                newPlayer.setString(5, event.getPlayer().getName());
                newPlayer.setInt(6, 0);
                newPlayer.execute();
                newPlayer.close();

            } catch (Exception e){
                e.printStackTrace();
            }

        }

        Location spawn = new Location(Bukkit.getWorld("uhclobby"), 34.5, 39, -187.5);
        spawn.setYaw(3);
        player.teleport(spawn);

        if (!event.getPlayer().hasPlayedBefore()) {
            event.setJoinMessage(ChatColor.WHITE + "[" + ChatColor.RED + "UHC" + ChatColor.WHITE + "] " + ChatColor.BOLD + "" + ChatColor.AQUA + player.getName() + ChatColor.YELLOW + " Welcome to " + ChatColor.RED + "UHC" );
        }
        else {
            event.setJoinMessage(ChatColor.WHITE + "[" + ChatColor.RED + "UHC" + ChatColor.WHITE + "] " + ChatColor.BOLD + "" + ChatColor.AQUA + player.getName() + ChatColor.GREEN + " joined" );
        }
        World holoWorld = player.getWorld();
        Location HoloPoint = new Location(holoWorld, 44.5, 40, -187.5);
        Location HoloPoint2 = new Location(holoWorld, 55.5, 39, -194.5);
        Location HoloPoint3 = new Location(holoWorld, 55.5, 39, -180.5);



        String playerName = event.getPlayer().getName();
        Gamer gamer = Gamers.getGamer(player);
        String uuid = player.getUniqueId().toString();
        HolographicDisplaysAPI.createIndividualHologram(this,
                HoloPoint,
                player,
                ChatColor.BOLD + "" + ChatColor.GREEN + "§l§aWelcome to the MCSquared Network", ChatColor.BOLD + "" + ChatColor.RED + "Server: §lUHC", ChatColor.AQUA + "Join a game by clicking a sign ahead!", ChatColor.YELLOW + "This is the pre-release for the MCSquared Network",
                ChatColor.YELLOW + "We are working hard to get everything ready!", ChatColor.LIGHT_PURPLE + "Check out our website - " + ChatColor.GOLD + "www.mc-sq.net");

        HolographicDisplaysAPI.createIndividualHologram(this, HoloPoint2, player,
                "§a§l" + player.getName() + " Statistics:", "§b§lKills: " + MySQL.getPlayerKills(player), "§b§lDeaths: " + MySQL.getPlayerDeaths(player), "§b§lGames Won: " + MySQL.getPlayerGamesWon(player), "§6Visit our website for more statistics!", "§d§lwww.mc-sq.net");
        HolographicDisplaysAPI.createIndividualHologram(this, HoloPoint3, player,
                "§a§l" + player.getName() + " Statistics:", "§b§lKills: " + MySQL.getPlayerKills(player), "§b§lDeaths: " + MySQL.getPlayerDeaths(player), "§b§lGames Won: " + MySQL.getPlayerGamesWon(player), "§6Visit our website for more statistics!", "§d§lwww.mc-sq.net");

        if (player.hasPermission("mcsq.player")) {
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