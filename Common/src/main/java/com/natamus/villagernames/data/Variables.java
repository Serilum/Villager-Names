package com.natamus.villagernames.data;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;

public class Variables {
	public static Pair<Component, Component> tradedVillagerPair = new Pair<Component, Component>(Component.empty(), Component.empty());
	public static final Logger logger = LogUtils.getLogger();
}
