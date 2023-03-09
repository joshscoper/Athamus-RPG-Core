package net.athamusmc.rpgcore.menu.charactercreation;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.CharacterHandler;
import net.athamusmc.rpgcore.character.characterclass.AthamusClass;
import net.athamusmc.rpgcore.character.characterclass.CharacterClass;
import net.athamusmc.rpgcore.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ClassMenu extends Menu {
    public ClassMenu(Main main, int size, String name, int characterID) {
        super(main, size, name);
        this.characterID = characterID;
        for (CharacterClass characterClass : CharacterClass.values()){
            ItemStack classItem = characterClass.getDisplay();
            ItemMeta meta = classItem.getItemMeta();
            meta.setDisplayName(characterClass.name());
            meta.setCustomModelData(characterClass.getModelData());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(main.format("&aClick to select."));
            classItem.setItemMeta(meta);
            inventory.addItem(classItem);
        }
    }
    private int characterID;

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Menu getParent() {
        return null;
    }

    @Override
    public void execute(InventoryClickEvent e) {
        e.setCancelled(true);
        e.setCursor(null);
        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot() + 1;
        if (e.getClick().equals(ClickType.LEFT)) {
            main.getCharacterHandler().createCharacter(player,this.characterID, ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
            main.getCharacterHandler().loadCharacter(player,slot);
            player.closeInventory();
            main.getMenuManager().removePlayerSession(player);
        }
    }
}
