package com.natamus.villagernames.data;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import org.slf4j.Logger;

public class Variables {
	public static Pair<Component, Component> tradedVillagerPair = new Pair<Component, Component>(TextComponent.EMPTY.copy(), TextComponent.EMPTY.copy());
	public static final Logger logger = LogUtils.getLogger();
}
