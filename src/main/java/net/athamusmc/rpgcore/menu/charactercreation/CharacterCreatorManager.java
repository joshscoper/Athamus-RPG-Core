package net.athamusmc.rpgcore.menu.charactercreation;

import net.athamusmc.rpgcore.Main;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CharacterCreatorManager {

    public Map<Player, CharacterCreator> creatorMap = new HashMap<>();

    private final Main main;

    public CharacterCreatorManager(Main main) {
        this.main = main;
    }

    public void newCharacterCreator(Player p) {
        CharacterCreator creator = new CharacterCreator(p, main);
        creatorMap.put(p, creator);
       // creator.startCreator();
    }

    public Map<Player, CharacterCreator> getCreatorMap(){
        return creatorMap;
    }

    public boolean hasCreator(Player p){
        return creatorMap.containsKey(p);
    }

    public CharacterCreator getCharacterCreator(Player p) {
        return creatorMap.get(p);
    }

    public void removeCharacterCreator(Player p) {
        creatorMap.remove(p);
    }


}
