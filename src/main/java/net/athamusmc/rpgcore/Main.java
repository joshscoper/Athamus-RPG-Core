package net.athamusmc.rpgcore;

import net.athamusmc.rpgcore.character.Character;
import net.athamusmc.rpgcore.character.CharacterHandler;
import net.athamusmc.rpgcore.listeners.PlayerInteract;
import net.athamusmc.rpgcore.listeners.PlayerJoin;
import net.athamusmc.rpgcore.listeners.PlayerQuit;
import net.athamusmc.rpgcore.managers.DatabaseManager;
import net.athamusmc.rpgcore.managers.FileManager;
import net.athamusmc.rpgcore.menu.MenuManager;
import net.athamusmc.rpgcore.menu.charactercreation.CharacterCreatorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    //Character Map
    private Map<Player, Character> characterMap;

    //Character
    private CharacterHandler characterHandler;
    private CharacterCreatorManager creatorManager;

    //Files Initialization
    private FileManager configManager;
    private FileManager langManager;

    //Database Initialization
    private DatabaseManager databaseManager;

    //Menu
    private MenuManager menuManager;

    @Override
    public void onEnable() {
        initFiles();
        databaseManager = new DatabaseManager(this);
        characterHandler = new CharacterHandler(this);
        creatorManager = new CharacterCreatorManager(this);
        menuManager = new MenuManager(this);
        initDatabase();
        characterMap = new HashMap<>();
        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    public void registerEvents(){
        new PlayerJoin(this);
        new PlayerInteract(this);
        new PlayerQuit(this);
    }

    public void initDatabase(){
        try {
        databaseManager.getConnection();
            Bukkit.getLogger().info("Successfully established a connection to the database!");
        } catch (SQLException e){
            Bukkit.getLogger().severe("Failed to connect to the database!");
        }
    }

    public void initFiles(){
        configManager = new FileManager(this,"config.yml");
        langManager = new FileManager(this, "lang.yml");
        configManager.setupFile();
        langManager.setupFile();
    }

    public String format(String input){return ChatColor.translateAlternateColorCodes('&',input);}

    public FileManager getConfigManager() {
        return configManager;
    }
    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public Map<Player, Character> getCharacterMap() {
        return characterMap;
    }

    public FileManager getLangManager() {
        return langManager;
    }

    public CharacterHandler getCharacterHandler() {
        return characterHandler;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public CharacterCreatorManager getCreatorManager() {
        return creatorManager;
    }
}
