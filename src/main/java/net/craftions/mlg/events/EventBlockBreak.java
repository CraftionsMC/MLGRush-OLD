package net.craftions.mlg.events;

import net.craftions.mlg.Mlg;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EventBlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            if(!(e.getBlock().getType().equals(Material.SANDSTONE) && e.getBlock().getType().equals(Material.RED_WOOL) && e.getBlock().getType().equals(Material.BLUE_WOOL))){
                e.setCancelled(true);
            }else {
                if(e.getBlock().getType().equals(Material.RED_WOOL)){
                    e.setCancelled(true);
                    if(!e.getPlayer().equals(Mlg.player1)){
                        Mlg.pointsBlue++;
                        Mlg.processPointUpdate();
                    }
                }
                if(e.getBlock().getType().equals(Material.BLUE_WOOL)){
                    e.setCancelled(true);
                    if(!e.getPlayer().equals(Mlg.player2)){
                        Mlg.pointsRed++;
                        Mlg.processPointUpdate();
                    }
                }
            }
        }
    }
}
