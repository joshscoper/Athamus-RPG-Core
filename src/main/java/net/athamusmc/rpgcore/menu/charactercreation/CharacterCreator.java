package net.athamusmc.rpgcore.menu.charactercreation;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.Character;
import org.bukkit.entity.Player;

public class CharacterCreator {

    private int characterSlot;

    private final Main main;
    private final Player player;

    public CharacterCreator(Player player, Main main){
        this.main = main;
        this.player = player;
    }

    public void startCreator(int characterSlot){
        main.getMenuManager().getPlayerSession(player).newMenu(new ClassMenu(main, 9, main.format("&2&lClass Selection"),characterSlot));
    }

    public void finishCreator(int characterSlot, String className){
        main.getCharacterHandler().createCharacter(player, characterSlot, className);
        main.getCreatorManager().removeCharacterCreator(player);
        player.closeInventory();
    }



}
