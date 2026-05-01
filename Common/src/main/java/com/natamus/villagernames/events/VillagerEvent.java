package com.natamus.villagernames.events;

import com.mojang.datafixers.util.Pair;
import com.natamus.collective.functions.EntityFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.villagernames.config.ConfigHandler;
import com.natamus.villagernames.data.Variables;
import com.natamus.villagernames.util.Names;
import com.natamus.villagernames.util.Reference;
import com.natamus.villagernames.util.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class VillagerEvent {
	public static void onSpawn(Level level, Entity entity) {
		if (level.isClientSide) {
			return;
		}

		if (entity.getTags().contains(Reference.MOD_ID + ".named")) {
			return;
		}

		if (!(entity instanceof Villager)) {
			boolean goname = false;
			if (ConfigHandler.nameModdedVillagers) {
				if (EntityFunctions.isModdedVillager(entity)) {
					goname = true;
				}
			}
			
			if (!goname) {
				return;
			}
		}

		if (entity.hasCustomName()) {
			if (!Util.shouldOverwriteName(entity)) {
				return;
			}
		}
		
		String name = Names.getRandomName();
		if (!name.isEmpty()) {
			EntityFunctions.nameEntity(entity, name);

			try {
				entity.getTags().add(Reference.MOD_ID + ".named");
			}
			catch (UnsupportedOperationException ignored) { } // Extra try/catch for Origins
		}
	}

	public static InteractionResult onVillagerInteract(Player player, Level level, InteractionHand hand, Entity entity, EntityHitResult hitResult) {
		if (!level.isClientSide()) { // Client side only!
			return InteractionResult.PASS;
		}

		if (!entity.hasCustomName()) {
			return InteractionResult.PASS;
		}

		if (player.isCrouching()) {
			return InteractionResult.PASS;
		}

		if (!hand.equals(InteractionHand.MAIN_HAND)) {
			return InteractionResult.PASS;
		}

		if (entity instanceof WanderingTrader) {
			Component profession = Component.translatable("entity.minecraft.wandering_trader");
			Variables.tradedVillagerPair = new Pair<>(entity.getName(), profession);
			return InteractionResult.PASS;
		}

		if (!entity.getClass().equals(Villager.class)) {
			Variables.tradedVillagerPair = new Pair<>(Component.empty(), Component.empty());
			return InteractionResult.PASS;
		}

		Villager villager = (Villager)entity;
		VillagerData d = villager.getVillagerData();

		String rawProfession = d.getProfession().toString();
		if (rawProfession.equals("none") || rawProfession.equals("nitwit")) {
			return InteractionResult.PASS;
		}

		if (rawProfession.contains(":")) {
			rawProfession = rawProfession.split(":")[1];
		}
		if (rawProfession.contains("-")) {
			rawProfession = rawProfession.split("-")[0];
		}

		String translatableInput = "entity.minecraft.villager." + rawProfession.trim();
		Component profession = Component.translatable(translatableInput);

		if (translatableInput.equals(profession.getString())) {
			rawProfession = rawProfession.replace("_", " ");
			profession = Component.literal(StringFunctions.capitalizeEveryWord(rawProfession));
		}

		Variables.tradedVillagerPair = new Pair<>(villager.getName(), profession);
		return InteractionResult.PASS;
	}
}
