package net.craftions.mlg.events;

import net.craftions.mlg.Mlg;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        try{
            if(e.getTo().getY() < 0){
                if(Mlg.player1.equals(e.getPlayer())){
                    ItemStack stick = new ItemStack(Material.STICK);
                    ItemStack blocks = new ItemStack(Material.SANDSTONE, 64);
                    ItemStack pick = new ItemStack(Material.WOODEN_PICKAXE);
                    stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
                    pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
                    Mlg.player1.getInventory().clear();
                    Mlg.player1.getInventory().addItem(stick, blocks, pick);
                    e.getPlayer().teleport(Mlg.spawn_red);
                }else {
                    ItemStack stick = new ItemStack(Material.STICK);
                    ItemStack blocks = new ItemStack(Material.SANDSTONE, 64);
                    ItemStack pick = new ItemStack(Material.WOODEN_PICKAXE);
                    stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
                    pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
                    Mlg.player2.getInventory().clear();
                    Mlg.player2.getInventory().addItem(stick, blocks, pick);
                    e.getPlayer().teleport(Mlg.spawn_blue);
                }
            }
        }catch (Exception ex){

        }
    }
}
