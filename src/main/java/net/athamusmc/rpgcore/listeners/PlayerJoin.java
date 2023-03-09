package net.athamusmc.rpgcore.listeners;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.Character;
import net.athamusmc.rpgcore.menu.charactercreation.CharacterSelectionMenu;
import net.athamusmc.rpgcore.util.InventoryHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerJoin implements Listener {

    private final Main main;

    public PlayerJoin(Main main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        try {
            main.getDatabaseManager().initializeDatabase(player);
            if (main.getDatabaseManager().hasTable(player)){
                if (main.getDatabaseManager().playersCharacters(player) < 1){
                    main.getMenuManager().createMenuSession(player);
                    main.getMenuManager().getPlayerSession(player).newMenu(new CharacterSelectionMenu(main, 9, main.format(main.getLangManager().getFile().getString("en.menu-title.character-selection")), player));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
