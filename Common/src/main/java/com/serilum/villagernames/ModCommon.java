package com.serilum.villagernames;

import com.natamus.collective.config.GenerateJSONFiles;
import com.serilum.villagernames.config.ConfigHandler;
import com.serilum.villagernames.data.Variables;
import com.serilum.villagernames.util.Names;
import com.serilum.villagernames.util.Reference;

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