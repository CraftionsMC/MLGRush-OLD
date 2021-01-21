/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.mlg.events;

import net.craftions.mlg.Mlg;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventPlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(e.getPlayer().equals(Mlg.player1) || e.getPlayer().equals(Mlg.player2)){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.kickPlayer("Ein Spieler hat die Runde verlassen!");
            }
            Mlg.end();
        }
    }

}
