package net.craftions.mlg;

import net.craftions.mlg.events.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Mlg extends JavaPlugin {

    public static Boolean isStarted = false;
    public static String prefix = "[§4MLG§r] ";
    public static Location spawn_blue = new Location(Bukkit.getWorld("world"), 24, 10, 8);
    public static Location spawn_red = new Location(Bukkit.getWorld("world"), -8, 10, 8);
    public static Location spawn = new Location(Bukkit.getWorld("world"), 60, 10, 60);
    public static Integer pointsRed = 0;
    public static Integer pointsBlue = 0;
    public static Player player1 = null;
    public static Player player2 = null;
    public static ArrayList<Location> locs = new ArrayList<Location>();
    public static Mlg mlg;

    @Override
    public void onEnable() {
        mlg = this;
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new EventPlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new EventBlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new EventBlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerDamage(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new EventFoodLevelChange(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerQuit(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        end();
    }

    public static void start(){
        spawn_red.setYaw(270);
        spawn_blue.setYaw(90);
        isStarted = true;
        Player p1 = null;
        Player p2 = null;
        Boolean t = false;
        for(Player p : Bukkit.getOnlinePlayers()){
            try {
                if(t){
                    p2 = p;
                }else {
                    p1 = p;
                    t = true;
                }
            }catch (NullPointerException ex ){ }
        }
        p1.teleport(spawn_red);
        p2.teleport(spawn_blue);
        player1 = p1;
        player2 = p2;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(mlg, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Rot: " + pointsRed + " §e- §r§bBlau: " + pointsBlue));
                }
            }
        }, 20L, 1*20L);
    }

    public static void processPointUpdate(){
        if(pointsRed == 10 || pointsBlue == 10){
            String won = "";
            if(pointsRed == 10){
                won = "§cRot§r";
            }else{
                won = "§bBlau§r";
            }
            Bukkit.broadcastMessage(prefix + won + " hat die Runde gewonnen!");
            Bukkit.getScheduler().scheduleSyncDelayedTask(mlg, new Runnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload confirm");
                }
            }, 2*20L);
        }else {
            for(Location l : locs){
                l.getBlock().setType(Material.AIR);
            }
            locs.clear();
            player1.teleport(spawn_red);
            player2.teleport(spawn_blue);
            resetInventory();
        }
    }

    public static void resetInventory(){
        ItemStack stick = new ItemStack(Material.STICK);
        ItemStack blocks = new ItemStack(Material.SANDSTONE, 64);
        ItemStack pick = new ItemStack(Material.WOODEN_PICKAXE);
        stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
        player1.getInventory().clear();
        player2.getInventory().clear();
        player1.getInventory().addItem(stick, blocks, pick);
        player2.getInventory().addItem(stick, blocks, pick);
    }

    public static void end(){
        for(Location l : locs){
            l.getBlock().setType(Material.AIR);
        }
        locs.clear();
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer("Die Runde ist vorbei!");
        }
    }
}
