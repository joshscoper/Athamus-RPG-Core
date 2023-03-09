package net.athamusmc.rpgcore.listeners;

import net.athamusmc.rpgcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private final Main main;

    public PlayerQuit(Main main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (main.getCharacterMap().containsKey(player)){
            main.getCharacterHandler().unloadCharacter(player,main.getCharacterMap().get(player));
        }
    }
}
