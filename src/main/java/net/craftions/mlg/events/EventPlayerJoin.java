package net.craftions.mlg.events;

import net.craftions.mlg.Mlg;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventPlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
        if(Mlg.isStarted){
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            for(Player p : Bukkit.getOnlinePlayers()){
                if(!p.getGameMode().equals(GameMode.SPECTATOR)){
                    p.hidePlayer(e.getPlayer());
                }
            }
            e.getPlayer().teleport(Mlg.spawn_red);
        }else {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
            if(Bukkit.getOnlinePlayers().size() == 2){
                Bukkit.broadcastMessage(Mlg.prefix + "§2Das Spiel startet!");
                Mlg.start();
            }else {
                Bukkit.broadcastMessage(Mlg.prefix + "§cEs wird auf einen Spieler gewartet...");
                e.getPlayer().teleport(Mlg.spawn);
            }
        }
    }
}
