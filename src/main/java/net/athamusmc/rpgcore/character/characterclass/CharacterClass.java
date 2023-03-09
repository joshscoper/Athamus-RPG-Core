package net.athamusmc.rpgcore.character.characterclass;

import net.athamusmc.rpgcore.character.characterclass.types.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CharacterClass {

    WARRIOR(2, new ItemStack(Material.BLAZE_ROD), Warrior.class),
    MAGE(2, new ItemStack(Material.BLAZE_ROD), Mage.class),
    ROGUE(2, new ItemStack(Material.BLAZE_ROD), Rogue.class),
    HUNTER(2, new ItemStack(Material.BLAZE_ROD), Hunter.class),
    PALADIN(2, new ItemStack(Material.BLAZE_ROD), Paladin.class);

    private int modelData;
    private ItemStack display;
    private String icon;
    private Class<? extends AthamusClass> characterClass;

    CharacterClass(int modelData, ItemStack display, Class<? extends AthamusClass> characterClass) {
        this.modelData = modelData;
        this.display = display;
        this.characterClass = characterClass;
    }

    public int getModelData() {
        return modelData;
    }

    public String getIcon(){
        return icon;
    }

    public ItemStack getDisplay(){
        return display;
    }

    public Class<? extends AthamusClass> getCharacterClass() {
        return characterClass;
    }
}
