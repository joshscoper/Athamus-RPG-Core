package net.athamusmc.rpgcore.menu.charactercreation;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.Character;
import net.athamusmc.rpgcore.character.CharacterHandler;
import net.athamusmc.rpgcore.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;

public class CharacterSelectionMenu extends Menu {

    private ItemStack createCharacter;

    public CharacterSelectionMenu(Main main, int size, String name, Player player) {
        super(main, size, name);
        for (int i = 0; i <= inventory.getSize() - 1; i++) {
            int slot = i + 1;
            Character character = main.getCharacterMap().get(player);
            ItemStack characterIcon;
                try {
                if (main.getDatabaseManager().findCharacterByID(player,slot) == null) {
                    characterIcon = new ItemStack(Material.PAPER);
                    ItemMeta meta = characterIcon.getItemMeta();
                    meta.setDisplayName(main.format(main.getLangManager().getFile().getString("en.menu-items.create-character")));
                    meta.setCustomModelData(1235);
                    characterIcon.setItemMeta(meta);
                } else {
                    characterIcon = new ItemStack(Material.PAPER);
                    ItemMeta meta = characterIcon.getItemMeta();
                    meta.setDisplayName(main.format("&eCharacter: &6&l" + slot));
                    meta.setCustomModelData(1234);
                    ArrayList<String> lore = new ArrayList<>();
                    String className = character.getClassName();
                    String combatLevel = String.valueOf(character.getLevel());
                    String balance = String.valueOf(character.getBalance());
                    lore.add(main.format("&e}----[&6&lInfo&e]----{"));
                    lore.add(main.format("&f&lClass: &e" + className));
                    lore.add(main.format("&f&lLevel: &e" + combatLevel));
                    lore.add(main.format("&f&lBalance: &e" + balance + " &f" + main.getConfig().get("config.module.economy.currency-symbol")));
                    lore.add(main.format("&aLeft Click to select"));
                    lore.add(main.format("&cRight Click to delete"));
                    meta.setLore(lore);
                    characterIcon.setItemMeta(meta);
                }
                inventory.setItem(i, characterIcon);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

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
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(main.format(main.getLangManager().getFile().getString("en.menu-items.create-character")))) {
                player.closeInventory();
                main.getMenuManager().getPlayerSession(player).newMenu(new ClassMenu(main, 9, main.format("&2&lClass Selection"),slot));

            } else {
                player.closeInventory();
                main.getMenuManager().removePlayerSession(player);
                main.getCharacterHandler().loadCharacter(player, slot);
            }
        }
        if (e.getClick().equals(ClickType.RIGHT)) {
            if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(main.format(main.getLangManager().getFile().getString("en.menu-items.create-character")))) {
                main.getCharacterHandler().deleteCharacter(player,main.getCharacterMap().get(player));
                player.closeInventory();
                main.getMenuManager().getPlayerSession(player).newMenu(new CharacterSelectionMenu(main, 9, main.format(main.getLangManager().getFile().getString("en.menu-title.character-selection")), player));
            }
        }
    }
}