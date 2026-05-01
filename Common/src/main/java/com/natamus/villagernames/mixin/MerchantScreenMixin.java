package com.natamus.villagernames.mixin;

import com.natamus.collective.functions.ScreenFunctions;
import com.natamus.villagernames.config.ConfigHandler;
import com.natamus.villagernames.util.Util;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MerchantMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MerchantScreen.class, priority = 1001)
public abstract class MerchantScreenMixin extends AbstractContainerScreen<MerchantMenu> {
	public MerchantScreenMixin(MerchantMenu merchantMenu, Inventory inventory, Component component) {
		super(merchantMenu, inventory, component);
	}

	@Inject(method = "extractLabels(Lnet/minecraft/client/gui/GuiGraphicsExtractor;II)V", at = @At(value = "HEAD"))
	protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym, CallbackInfo ci) {
		if (!ConfigHandler.showProfessionOnTradeScreen) {
			return;
		}

		MutableComponent newTitle = Util.getTradeScreenTitle();
		if (newTitle == null) {
			return;
		}

		ScreenFunctions.setMerchantScreenTitle((MerchantScreen)(Object)this, newTitle);
	}

	@ModifyVariable(method = "extractLabels(Lnet/minecraft/client/gui/GuiGraphicsExtractor;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;width(Lnet/minecraft/network/chat/FormattedText;)I", ordinal = 0))
	public Component extractLabels_component(Component titleAndLevel) {
		if (ConfigHandler.hideMerchantLevelTradeScreen) {
			MutableComponent newTitle = Util.getTradeScreenTitle();
			if (newTitle != null) {
				return newTitle;
			}
		}
		return titleAndLevel;
	}
}