package net.athamusmc.rpgcore.character.characterclass.types;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.characterclass.AthamusClass;
import net.athamusmc.rpgcore.character.characterclass.skilltrees.Tree;
import net.athamusmc.rpgcore.character.equipment.ArmorType;
import net.athamusmc.rpgcore.character.equipment.WeaponType;

import java.util.List;

public class Paladin extends AthamusClass {
    public Paladin(Main main) {
        super(main);
    }

    @Override
    public List<ArmorType> wearableArmorTypes() {
        return null;
    }

    @Override
    public List<WeaponType> usableWeaponTypes() {
        return null;
    }

    @Override
    public Tree.TreeName skillTreeOne() {
        return null;
    }
}