package net.craftions.mlg.events;

import net.craftions.mlg.Mlg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventPlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(e.getTo().getY() < 0){
            if(Mlg.player1.equals(e.getPlayer())){
                e.getPlayer().teleport(Mlg.spawn_red);
            }else {
                e.getPlayer().teleport(Mlg.spawn_blue);
            }
        }
    }
}
