package com.natamus.villagernames.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.villagernames.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean _useCustomNames = true;
	@Entry public static boolean _useFemaleNames = true;
	@Entry public static boolean _useMaleNames = true;
	@Entry public static boolean nameModdedVillagers = true;

	public static void initConfig() {
		configMetaData.put("_useCustomNames", Arrays.asList(
			"Use the custom name list, editable in ./mods/villagernames/customnames.txt, seperated by a comma."
		));
		configMetaData.put("_useFemaleNames", Arrays.asList(
			"Use the list of pre-defined female names when naming villagers."
		));
		configMetaData.put("_useMaleNames", Arrays.asList(
			"Use the list of pre-defined male names when naming villagers."
		));
		configMetaData.put("nameModdedVillagers", Arrays.asList(
			"If enabled, also gives modded villagers a name. If you've found a 'villager'-entity that isn't named let me know by opening an issue so I can add it in."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}