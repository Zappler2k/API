package de.zappler2k.api.storage.json.impl;

import java.io.File;

public interface IModule {

    File getFile();

    String toJson();

    IModule fromJson(String data);

    String getDefaultConfig();
}
