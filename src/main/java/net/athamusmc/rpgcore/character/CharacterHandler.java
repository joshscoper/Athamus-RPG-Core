package net.athamusmc.rpgcore.character;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.util.InventoryHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CharacterHandler {

    private final Main main;

    public CharacterHandler(Main main){
        this.main = main;
    }

    public void unloadCharacter(Player player, Character character){
        if (character != null && main.getCharacterMap().containsKey(player)) {
            try {
                String location = player.getLocation().getWorld().getName() + ":" + player.getLocation().getBlockX() + ":" + player.getLocation().getBlockY() + ":" + player.getLocation().getBlockZ();
                character.setLevel(player.getLevel());
                character.setXp(Float.floatToIntBits(player.getExp()));
                character.setLocation(location);
                character.setInventoryString(InventoryHandler.saveInventory(player));
                player.getInventory().clear();
                character.setLocation(location);
                player.teleport(Bukkit.getWorld(main.getConfigManager().getFile().getString("settings.modules.characters.lobby-world")).getSpawnLocation());
                player.getInventory().clear();
                player.setLevel(0);
                player.setExp(0);
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2);
                player.setHealth(2);
                main.getDatabaseManager().updateCharacter(character);
                player.sendMessage(main.format("&2&lSystem &7>> &e" + character.getCharacterID() + " &chas been unloaded."));
                main.getCharacterMap().remove(player, character.getCharacterID());
            } catch (SQLException e){
                e.printStackTrace();
            }


        }
    }
    public void loadCharacter(Player player, int characterID){
        try {
            if (main.getCharacterMap().containsKey(player)){
                unloadCharacter(player,main.getDatabaseManager().findCharacterByID(player,characterID));
            }
            Character character = main.getDatabaseManager().findCharacterByID(player, characterID);
            player.setLevel(character.getLevel());
            player.setExp(character.getXp());
            Location tpLoc;
            InventoryHandler.loadInventory(player,main);
            if (character.getLocation().equals(main.getConfigManager().getFile().getString("settings.modules.character.spawn-world"))){
                tpLoc = Bukkit.getWorld(character.getLocation()).getSpawnLocation();
            } else {
                String str2loc[] = character.getLocation().split("\\:");
                tpLoc = new Location(main.getServer().getWorld(str2loc[0]),0,0,0 );
                tpLoc.setX(Double.parseDouble(str2loc[1]));
                tpLoc.setY(Double.parseDouble(str2loc[2]));
                tpLoc.setZ(Double.parseDouble(str2loc[3]));
                player.teleport(tpLoc);
            }
            main.getCharacterMap().put(player,character);
            player.sendMessage(main.format("&2&lSystem &7>> &e" + character.getCharacterID() + " &ahas been loaded."));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCharacter(Player player, int characterSlot,String className) {
        Character newCharacter = new Character(characterSlot, player.getUniqueId(), main, className, 1, 0, 1, InventoryHandler.saveInventory(player),
                1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1,
                1, 1, 1,main.getConfigManager().getFile().getString("settings.modules.character.spawn-world"),1,3);
        try {
            main.getDatabaseManager().createCharacter(newCharacter);
            main.getCharacterMap().put(player,newCharacter);
            player.sendMessage(main.format("&2&lSystem &7>> &e" + newCharacter.getCharacterID() + " &ahas been created."));
            loadCharacter(player, newCharacter.getCharacterID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCharacter(Player player, Character character){
        unloadCharacter(player,character);
        try {
            player.sendMessage(main.format("&2&lSystem &7>> &e" + character.getCharacterID() + " &chas been deleted."));
            main.getDatabaseManager().deletePlayerStats(character);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }




}
