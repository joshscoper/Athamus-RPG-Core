package net.athamusmc.rpgcore.listeners;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.menu.charactercreation.CharacterSelectionMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteract implements Listener {

    private final Main main;

    public PlayerInteract(Main main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        Entity clicked = event.getRightClicked();

        //Character Select & Creation
        if (clicked.getName().equals(main.format(main.getConfig().getString("settings.modules.characters.creation-npc")))){
            if (main.getMenuManager().getPlayerSession(player) == null){
                main.getMenuManager().createMenuSession(player);
            }
            main.getMenuManager().getPlayerSession(player).newMenu(new CharacterSelectionMenu(main, 9, main.format(main.getLangManager().getFile().getString("en.menu-title.character-selection")), player));
        }
    }

}
