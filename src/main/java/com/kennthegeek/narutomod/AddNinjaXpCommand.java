package com.kennthegeek.narutomod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public
class AddNinjaXpCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("addninjaxp")
        .then(CommandManager.argument("amount", IntegerArgumentType.integer(1))
        .executes(context -> {
            int amount = IntegerArgumentType.getInteger(context, "amount");
            context.getSource().getPlayer().getPersistentData().putInt("ninjaXp", context.getSource().getPlayer().getPersistentData().getInt("ninjaXp") + amount);
            context.getSource().sendFeedback(() -> Text.literal("Added " + amount + " Ninja XP"), false);
            return 1;
        })));
    }
}
