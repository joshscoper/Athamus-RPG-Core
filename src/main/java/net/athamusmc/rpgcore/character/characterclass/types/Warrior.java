package net.athamusmc.rpgcore.character.characterclass.types;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.characterclass.AthamusClass;
import net.athamusmc.rpgcore.character.characterclass.skilltrees.Tree;
import net.athamusmc.rpgcore.character.equipment.ArmorType;
import net.athamusmc.rpgcore.character.equipment.WeaponType;

import java.util.Arrays;
import java.util.List;

public class Warrior extends AthamusClass {
    public Warrior(Main main) {
        super(main);
    }

    @Override
    public List<ArmorType> wearableArmorTypes() {
        return Arrays.asList(ArmorType.PLATE);
    }

    @Override
    public List<WeaponType> usableWeaponTypes() {
        return Arrays.asList(WeaponType.SWORD,WeaponType.HAMMER);
    }

    @Override
    public Tree.TreeName skillTreeOne() {
        return null;
    }

}