package com.natamus.villagernames.cmds;
import com.natamus.villagernames.util.Reference;

import com.mojang.brigadier.CommandDispatcher;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.villagernames.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandVillagernames {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("villagernames")
			.requires((iCommandSender) -> iCommandSender.hasPermission(2))
			.executes((command) -> {
				sendUsage(command.getSource());
				return 1;
			})
			.then(Commands.literal("unname")
			.executes((command) -> {
				CommandSourceStack source = command.getSource();

				int count = Util.unnameLoadedVillagers(source.getLevel());

				MessageFunctions.sendTranslatableMessage(source, "collective.villagernames.message.namesloadedvillagers", ChatFormatting.DARK_GREEN, count);
				return 1;
			}))
			.then(Commands.literal("name")
			.executes((command) -> {
				CommandSourceStack source = command.getSource();

				int count = Util.nameLoadedVillagers(source.getLevel());

				MessageFunctions.sendTranslatableMessage(source, "collective.villagernames.message.loadedvillagerswithout", ChatFormatting.DARK_GREEN, count);
				return 1;
			}))
			.then(Commands.literal("rename")
			.executes((command) -> {
				CommandSourceStack source = command.getSource();

				int count = Util.renameLoadedVillagers(source.getLevel());

				MessageFunctions.sendTranslatableMessage(source, "collective.villagernames.message.loadedvillagersrenamed", ChatFormatting.DARK_GREEN, count);
				return 1;
			}))
		);
	}

	public static void sendUsage(CommandSourceStack source) {
		MessageFunctions.sendTranslatableMessage(source, "collective.shared.message.commandsusage", true, ChatFormatting.DARK_GREEN, Reference.NAME);
		MessageFunctions.sendMessage(source, " /villagernames unname", ChatFormatting.DARK_GREEN);
		MessageFunctions.sendTranslatableMessage(source, "  ", "collective.villagernames.message.removesnamesloaded", ChatFormatting.DARK_GRAY);
		MessageFunctions.sendMessage(source, " /villagernames name", ChatFormatting.DARK_GREEN);
		MessageFunctions.sendTranslatableMessage(source, "  ", "collective.villagernames.message.givesunnamedloaded", ChatFormatting.DARK_GRAY);
		MessageFunctions.sendMessage(source, " /villagernames rename", ChatFormatting.DARK_GREEN);
		MessageFunctions.sendTranslatableMessage(source, "  ", "collective.villagernames.message.givesloadedvillagers", ChatFormatting.DARK_GRAY);
	}
}