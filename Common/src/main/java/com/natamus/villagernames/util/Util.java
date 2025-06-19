package com.natamus.villagernames.util;

import com.natamus.collective.functions.EntityFunctions;
import com.natamus.villagernames.config.ConfigHandler;
import com.natamus.villagernames.data.Variables;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
	private static final List<String> namesToOverwrite = new ArrayList<String>(Arrays.asList("Bowman", "Crossbowman", "Horseman", "Nomad", "Recruit", "Shieldman"));
	public static boolean shouldOverwriteName(Entity entity) {
		return namesToOverwrite.contains(entity.getName().getString());
	}

	public static MutableComponent getTradeScreenTitle() {
		Component name = Variables.tradedVillagerPair.getFirst();
		Component profession = Variables.tradedVillagerPair.getSecond();

		if (!ConfigHandler.showProfessionOnTradeScreen) {
			return name.copy();
		}

		if (name.equals(Component.empty()) || profession.equals(Component.empty())) {
			return null;
		}

		MutableComponent newTitle = Component.empty();
		if (!ConfigHandler.switchNameAndProfessionTradeScreen) {
			newTitle = newTitle.append(name).append(" | ").append(profession);
		}
		else {
			newTitle = newTitle.append(profession).append(" | ").append(name);
		}
		return newTitle;
	}

	public static int unnameLoadedVillagers(ServerLevel serverLevel) {
		int unnameCount = 0;

		for (Entity entity : serverLevel.getAllEntities()) {
			if (entity instanceof Villager || EntityFunctions.isModdedVillager(entity)) {
				if (entity.hasCustomName()) {
					entity.setCustomName(null);
					entity.removeTag(Reference.MOD_ID + ".named");

					unnameCount += 1;
				}
			}
		}

		return unnameCount;
	}

	public static int nameLoadedVillagers(ServerLevel serverLevel) {
		int nameCount = 0;

		for (Entity entity : serverLevel.getAllEntities()) {
			if (entity instanceof Villager || EntityFunctions.isModdedVillager(entity)) {
				if (!entity.hasCustomName()) {
					EntityFunctions.nameEntity(entity, Names.getRandomName());
					entity.addTag(Reference.MOD_ID + ".named");

					nameCount += 1;
				}
			}
		}

		return nameCount;
	}

	public static int renameLoadedVillagers(ServerLevel serverLevel) {
		unnameLoadedVillagers(serverLevel);
		return nameLoadedVillagers(serverLevel);
	}
}
