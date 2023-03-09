package net.athamusmc.rpgcore.character.characterclass;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.characterclass.skilltrees.SkillTree;
import net.athamusmc.rpgcore.character.characterclass.skilltrees.Tree;
import net.athamusmc.rpgcore.character.equipment.ArmorType;
import net.athamusmc.rpgcore.character.equipment.WeaponType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AthamusClass {


    private final Main main;
    Map<Integer, Tree.TreeName> map = new HashMap<>();

    public AthamusClass(Main main){
        this.main = main;
        map.put(1, skillTreeOne());
    }




    public abstract List<ArmorType> wearableArmorTypes();
    public abstract List<WeaponType> usableWeaponTypes();
    public abstract Tree.TreeName skillTreeOne();

    public void openSkillTree(Player p, int number){
        main.getMenuManager().getPlayerSession(p).newMenu(new SkillTree(main, map.get(number), p));
    }


}
