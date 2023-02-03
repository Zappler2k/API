package de.zappler2k.api.spigot.warp.impl;

import com.google.gson.GsonBuilder;
import de.zappler2k.api.spigot.json.LocationAdapter;
import de.zappler2k.api.storage.json.impl.IModule;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.io.File;

@Getter
@Setter
public class Warp implements IModule {

    private String id;
    private Location location;

    public Warp() {
    }

    @Override
    public File getFile() {
        return new File("plugins//Warps//" + id + ".json");
    }

    @Override
    public String toJson() {
        return new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Location.class, new LocationAdapter()).create().toJson(this);
    }

    @Override
    public IModule fromJson(String data) {
        return new GsonBuilder().registerTypeAdapter(Location.class, new LocationAdapter()).create().fromJson(data, this.getClass());
    }

    @Override
    public String getDefaultConfig() {
        return new Warp().toJson();
    }
}
