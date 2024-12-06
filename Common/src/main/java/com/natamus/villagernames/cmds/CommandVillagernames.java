package com.natamus.villagernames.cmds;

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

				MessageFunctions.sendMessage(source, "The names of " + count + " loaded villagers have been removed.", ChatFormatting.DARK_GREEN);
				return 1;
			}))
			.then(Commands.literal("name")
			.executes((command) -> {
				CommandSourceStack source = command.getSource();

				int count = Util.nameLoadedVillagers(source.getLevel());

				MessageFunctions.sendMessage(source, count + " loaded villagers without a name have been named.", ChatFormatting.DARK_GREEN);
				return 1;
			}))
			.then(Commands.literal("rename")
			.executes((command) -> {
				CommandSourceStack source = command.getSource();

				int count = Util.renameLoadedVillagers(source.getLevel());

				MessageFunctions.sendMessage(source, count + " loaded villagers have been renamed.", ChatFormatting.DARK_GREEN);
				return 1;
			}))
		);
	}

	public static void sendUsage(CommandSourceStack source) {
		MessageFunctions.sendMessage(source, "--- Villager Names Commands Usage ---", ChatFormatting.DARK_GREEN, true);
		MessageFunctions.sendMessage(source, " /villagernames unname", ChatFormatting.DARK_GREEN);
		MessageFunctions.sendMessage(source, "  Removes the names of all loaded villagers.", ChatFormatting.DARK_GRAY);
		MessageFunctions.sendMessage(source, " /villagernames name", ChatFormatting.DARK_GREEN);
		MessageFunctions.sendMessage(source, "  Gives all unnamed loaded villagers a name.", ChatFormatting.DARK_GRAY);
		MessageFunctions.sendMessage(source, " /villagernames rename", ChatFormatting.DARK_GREEN);
		MessageFunctions.sendMessage(source, "  Gives all loaded villagers a new random name.", ChatFormatting.DARK_GRAY);
	}
}