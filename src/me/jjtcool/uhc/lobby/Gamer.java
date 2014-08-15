package me.jjtcool.uhc.lobby;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: JJtCool
 * Date: 08/08/14
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "mcsquared.gamer")
public class Gamer {

    @Id
    @Column(name = "playerName")
    private String playerName;

    @Column(name = "rank")
    private String rank;

    @Column(name = "deaths")
    private int deaths;

    @Column(name = "squares")
    private int squares;

    @Column(name ="uuid")
    private String uuid;

    public boolean stacker;

    public String username;

    public String playerScroller;

    public String particleType;

    public Gamer() {
    }

    public String getParticleType() {
        return particleType;
    }

    public void setParticleType(String particleType) {
        this.particleType = particleType;
    }

    public String getPlayerScroller() {
        return playerScroller;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getStacker() {
        return stacker;
    }

    public void setStacker(boolean stacker){
        this.stacker = stacker;
    }

    public Gamer(String playerName) {
        this.playerName = playerName;
    }

    public int getSquares(){
        return squares;
    }

    public String getPlayerUUID() {
        return uuid;
    }

    public String getRank(){
        return rank;
    }

    public String getPlayerName() {
        return playerName;
    }

    /**public void setKills(int kills) {
     this.kills = kills;
     }

     public int getKills() {
     return kills;
     }

     public int getDeaths() {
     return deaths;
     }

     public void setDeaths(int deaths) {
     this.deaths = deaths;
     }      **/



    @Override
    public String toString() {
        return "Gamer{" +
                "playerName='" + playerName + '\'' +
                ", squares=" + squares +
                ", rank=" + rank +
                '}';
    }

}