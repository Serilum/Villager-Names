package com.natamus.villagernames.forge.events;

import com.natamus.villagernames.events.VillagerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeVillagerEvent {
	@SubscribeEvent
	public void onSpawn(EntityJoinWorldEvent e) {
		VillagerEvent.onSpawn(e.getWorld(), e.getEntity());
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent.EntityInteract e) {
		VillagerEvent.onVillagerInteract(e.getPlayer(), e.getWorld(), e.getHand(), e.getTarget(), null);
	}
}
