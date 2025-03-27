package com.natamus.villagernames.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.villagernames.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean useCustomNames = true;
	@Entry public static boolean useDefaultFemaleNames = true;
	@Entry public static boolean useDefaultMaleNames = true;
	@Entry public static boolean useBothCustomAndDefaultNames = false;
	@Entry public static boolean nameModdedVillagers = true;
	@Entry public static boolean showProfessionOnTradeScreen = true;
	@Entry public static boolean switchNameAndProfessionTradeScreen = false;
	@Entry public static boolean hideMerchantLevelTradeScreen = false;
	@Entry public static boolean shouldCapitalizeNames = true;

	public static void initConfig() {
		configMetaData.put("useCustomNames", Arrays.asList(
			"Use the custom name list, editable in ./mods/villagernames/customnames.txt, seperated by a comma. If custom names are found, the default name list is ignored."
		));
		configMetaData.put("useDefaultFemaleNames", Arrays.asList(
			"Use the list of pre-defined female names when naming villagers."
		));
		configMetaData.put("useDefaultMaleNames", Arrays.asList(
			"Use the list of pre-defined male names when naming villagers."
		));
		configMetaData.put("useBothCustomAndDefaultNames", Arrays.asList(
			"Disabled by default. Whether both custom and default names should be used to name villagers. Custom names will probably not be chosen often due to the amount of default names."
		));
		configMetaData.put("nameModdedVillagers", Arrays.asList(
			"If enabled, also gives modded villagers a name. If you've found a 'villager'-entity that isn't named let me know by opening an issue so I can add it in."
		));
		configMetaData.put("showProfessionOnTradeScreen", Arrays.asList(
			"Whether the profession should be added to the villager's trade screen next to their name."
		));
		configMetaData.put("switchNameAndProfessionTradeScreen", Arrays.asList(
			"If enabled, switches the name and profession on the villager trading screen. Result: <profession> - <name>."
		));
		configMetaData.put("hideMerchantLevelTradeScreen", Arrays.asList(
			"Whether the merchant level (novice, apprentice etc.) should be hidden on the trade screen."
		));
		configMetaData.put("shouldCapitalizeNames", Arrays.asList(
			"If enabled, the mod capitalizes each word in the custom name list."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}