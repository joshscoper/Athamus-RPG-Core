package net.athamusmc.rpgcore.util;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import net.athamusmc.rpgcore.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler {

    public static String saveInventory(Player player){
        Inventory inventory = player.getInventory();
        ReadWriteNBT contents = NBT.itemStackArrayToNBT(inventory.getContents());
        String str = contents.toString();
        return str;
    }

    public static Inventory loadInventory(Player player, Main main){
        String str;
        str = main.getCharacterMap().get(player).getInventoryString();
       ReadWriteNBT nbt = NBT.parseNBT(str);
        ItemStack[] contents = NBT.itemStackArrayFromNBT(nbt);
        Inventory inventory = player.getInventory();
        inventory.setContents(contents);
        return inventory;
    }
}
