package net.athamusmc.rpgcore.character.characterclass;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.Character;
import net.athamusmc.rpgcore.util.InventoryHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassManager implements Listener {

    private Map<CharacterClass, AthamusClass> classMap = new HashMap<>();
    private List<Player> combatMenu = new ArrayList<>();

    private final Main main;


    public ClassManager(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
        loadClasses();
    }

    public AthamusClass getClass(CharacterClass type){
        return classMap.get(type);
    }

    public void loadClasses(){
        for (CharacterClass type : CharacterClass.values()){
            try {
                classMap.put(type, type.getCharacterClass().getConstructor(Main.class).newInstance(main));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e){
        Player p = e.getPlayer();
        if (main.getCharacterMap().containsKey(p)) {
            e.setCancelled(true);
            toggleCombatInventory(p);
        }
    }

    public void toggleCombatInventory(Player p){
        if (combatMenu.contains(p)){
            combatMenu.remove(p);
            InventoryHandler.loadInventory(p,main);
        } else {
            combatMenu.add(p);
            Character character = main.getCharacterMap().get(p);
            InventoryHandler.saveInventory(p);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {
                    ItemStack[] clear = {new ItemStack(Material.AIR), new ItemStack(Material.AIR)};

                    p.getInventory().setContents(clear);
                    Inventory  charInv = InventoryHandler.loadInventory(p.getPlayer(), main);
                    p.getInventory().setHelmet(charInv.getContents()[charInv.getContents().length - 2]);
                    p.getInventory().setChestplate(charInv.getContents()[charInv.getContents().length - 3]);
                    p.getInventory().setLeggings(charInv.getContents()[charInv.getContents().length - 4]);
                    p.getInventory().setBoots(charInv.getContents()[charInv.getContents().length - 5]);
                }
            }, 1L);
        }
    }

    public boolean isInCombatMenu(Player p){
        return combatMenu.contains(p);
    }



}