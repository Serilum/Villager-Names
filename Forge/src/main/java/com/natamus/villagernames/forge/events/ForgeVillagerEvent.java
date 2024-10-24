package com.natamus.villagernames.forge.events;

import com.natamus.villagernames.cmds.CommandVillagernames;
import com.natamus.villagernames.events.VillagerEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeVillagerEvent {
	@SubscribeEvent
	public void onSpawn(EntityJoinLevelEvent e) {
		VillagerEvent.onSpawn(e.getLevel(), e.getEntity());
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent.EntityInteract e) {
		VillagerEvent.onVillagerInteract(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null);
	}

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	CommandVillagernames.register(e.getDispatcher());
    }
}
