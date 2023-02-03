package de.zappler2k.api.spigot;

import de.zappler2k.api.spigot.player.PlayerHandler;
import de.zappler2k.api.spigot.player.inventory.GuiManager;
import de.zappler2k.api.spigot.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SpigotCore extends JavaPlugin implements Listener {
    @Getter
    public static SpigotCore instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }
}
