package de.zappler2k.api.spigot;

import de.zappler2k.api.spigot.player.inventory.GuiMenu;
import de.zappler2k.api.spigot.player.inventory.impl.InvSize;
import de.zappler2k.api.spigot.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MainMenu extends GuiMenu {


    public MainMenu(Plugin plugin, Player player) {
        super(player.getName(), InvSize.SIZE_4, player, plugin);
        fillInventory(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
        isClickable = false;
        setConsumedItem(0, new ItemBuilder(Material.RED_DYE).build(), event -> {
            event.getWhoClicked().sendMessage(event.getWhoClicked().getName());
        });
    }
}
