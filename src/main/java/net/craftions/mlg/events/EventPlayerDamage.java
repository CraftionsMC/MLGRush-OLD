package net.craftions.mlg.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventPlayerDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setDamage(0d);
    }
}
