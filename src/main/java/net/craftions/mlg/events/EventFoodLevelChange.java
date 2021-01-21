package net.craftions.mlg.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class EventFoodLevelChange implements Listener {

    @EventHandler
    public void onChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }
}
