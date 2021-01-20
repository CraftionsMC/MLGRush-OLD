package net.craftions.mlg.events;

import net.craftions.mlg.Mlg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventBlockPlace implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(!(event.getBlock().getLocation().getY() > 0 && event.getBlock().getLocation().getY() < 12)) {
            event.setCancelled(true);
        }else {
            Mlg.locs.add(event.getBlock().getLocation());
        }
    }
}
