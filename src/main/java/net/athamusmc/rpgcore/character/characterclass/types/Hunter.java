package net.athamusmc.rpgcore.character.characterclass.types;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.characterclass.AthamusClass;
import net.athamusmc.rpgcore.character.characterclass.skilltrees.Tree;
import net.athamusmc.rpgcore.character.equipment.ArmorType;
import net.athamusmc.rpgcore.character.equipment.WeaponType;

import java.util.Arrays;
import java.util.List;
public class Hunter extends AthamusClass {

    public Hunter(Main main) {
        super(main);
    }

    public List<ArmorType> wearableArmorTypes() {
        return Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER);
    }


    public List<WeaponType> usableWeaponTypes() {
        return Arrays.asList(WeaponType.BOW);
    }

    @Override
    public Tree.TreeName skillTreeOne() {
        return null;
    }
}
