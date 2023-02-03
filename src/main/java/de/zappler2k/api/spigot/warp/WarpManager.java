package de.zappler2k.api.spigot.warp;

import de.zappler2k.api.spigot.warp.impl.Warp;
import de.zappler2k.api.storage.json.ModuleManager;
import lombok.Getter;
import org.bukkit.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class WarpManager {

    private List<Warp> warps;
    private ModuleManager moduleManager;

    public WarpManager(ModuleManager moduleManager) {
        this.warps = new ArrayList<>();
        this.moduleManager = moduleManager;
    }

    public Warp getWarp(String id) {
        return warps.stream().filter(warp -> warp.getId().equals(id)).findAny().orElse(null);
    }

    public boolean warpExists(String id) {
        return getWarp(id) != null;
    }

    public void addWarp(String id, Location location) {
        if (!warpExists(id)) {
            Warp warp = new Warp();
            warp.setId(id);
            warp.setLocation(location);
            moduleManager.addModule(warp, false);
            moduleManager.insert(warp, warp.toJson());
            warps.add((Warp) new Warp().fromJson(moduleManager.getContent(warp.getFile())));
        }
    }

    public void removeWarp(String id) {
        if (warpExists(id)) {
            warps.remove(getWarp(id));
            moduleManager.removeIModule(getWarp(id));
        }
    }

    public void replaceWarp(String id, Location location) {
        if (warpExists(id)) {
            Warp warp = new Warp();
            warp.setId(id);
            warp.setLocation(location);
            moduleManager.insert(warp, warp.toJson());
        }
    }

    public void addAllWarps() {
        File dictonary = new File("plugins//Warps");
        if (!dictonary.exists()) return;
        for (File file : dictonary.listFiles()) {
            Warp warp = new Warp();
            warps.add((Warp) new Warp().fromJson(moduleManager.getContent(file)));
        }
    }
}