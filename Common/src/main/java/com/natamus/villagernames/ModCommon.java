package com.natamus.villagernames;

import com.natamus.villagernames.config.ConfigHandler;
import com.natamus.villagernames.util.Names;

import java.io.IOException;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		try {
			Names.setCustomNames();
		} catch (IOException e) {
			System.out.println("[Villager Names] Unable to load custom name list.");
		}
	}
}