package com.natamus.villagernames.neoforge.events;

import com.natamus.villagernames.events.VillagerEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeVillagerEvent {
	@SubscribeEvent
	public static void onSpawn(EntityJoinLevelEvent e) {
		VillagerEvent.onSpawn(e.getLevel(), e.getEntity());
	}

	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent.EntityInteract e) {
		VillagerEvent.onVillagerInteract(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null);
	}
}
