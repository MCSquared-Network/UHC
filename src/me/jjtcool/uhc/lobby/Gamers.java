package me.jjtcool.uhc.lobby;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ${JJtCool}
 * Date: 08/08/14
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
public class Gamers {
    private static HashMap<Player, Gamer> map = new HashMap<Player, Gamer>();



    public static Gamer getGamer(Player player) {
        Gamer g = map.get(player);
        if (g == null) {
            g = new Gamer(player.getName());
            map.put(player, g);


        }
        return g;
    }

    public static void update(Gamer gamer) {

    }
}
