package de.zappler2k.api.storage.mysql.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.zappler2k.api.storage.json.ModuleManager;
import de.zappler2k.api.storage.json.impl.IModule;
import lombok.Getter;

import java.io.File;

@Getter
public class MySQLModule implements IModule {

    private String HOST;
    private Integer PORT;
    private String DATABASE;
    private String USER;
    private String PASSWORD;

    public MySQLModule(ModuleManager moduleManager) {
        moduleManager.addModule(this, true);
    }

    public MySQLModule(String HOST, Integer PORT, String DATABASE, String USER, String PASSWORD) {
        this.HOST = HOST;
        this.PORT = PORT;
        this.DATABASE = DATABASE;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    @Override
    public File getFile() {
        return new File("plugins//ConduroMC-API//mysql.json");
    }

    @Override
    public String toJson() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

    @Override
    public IModule fromJson(String data) {
        return new Gson().fromJson(data, this.getClass());
    }

    @Override
    public String getDefaultConfig() {
        return new MySQLModule("localhost", 3306, "conduromcapi", "root", "1234").toJson();
    }
}
