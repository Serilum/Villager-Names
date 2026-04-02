package com.natamus.villagernames.forge.events;

import com.natamus.villagernames.cmds.CommandVillagernames;
import com.natamus.villagernames.events.VillagerEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeVillagerEvent {
	public static void registerEventsInBus() {
		BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeVillagerEvent.class);
	}

	@SubscribeEvent
	public static void onSpawn(EntityJoinLevelEvent e) {
		VillagerEvent.onSpawn(e.getLevel(), e.getEntity());
	}

	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific e) {
		VillagerEvent.onVillagerInteract(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null);
	}

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent e) {
    	CommandVillagernames.register(e.getDispatcher());
    }
}
