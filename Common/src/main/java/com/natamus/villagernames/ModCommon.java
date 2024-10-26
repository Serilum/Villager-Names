package com.natamus.villagernames;

import com.natamus.collective.config.GenerateJSONFiles;
import com.natamus.villagernames.config.ConfigHandler;
import com.natamus.villagernames.data.Variables;
import com.natamus.villagernames.util.Names;
import com.natamus.villagernames.util.Reference;

import java.io.IOException;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();

		load();
	}

	private static void load() {
		GenerateJSONFiles.requestJSONFile(Reference.MOD_ID, "entity_names.json");

		try {
			Names.setCustomNames();
		} catch (IOException e) {
			Variables.logger.warn("[" + Reference.NAME + "] Unable to load custom name list config. Custom names disabled.");
		}
	}
}