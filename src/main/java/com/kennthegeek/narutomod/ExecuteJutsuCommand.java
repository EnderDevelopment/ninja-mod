package com.kennthegeek.narutomod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public
class ExecuteJutsuCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("executejutsu")
        .then(CommandManager.argument("jutsu", StringArgumentType.word())
        .executes(context -> {
            String jutsu = StringArgumentType.getString(context, "jutsu");
            switch (jutsu.toLowerCase()) {
                case "kamuieye":
                new JutsuItems.KamuiEyeItem(new net.minecraft.item.Item.Settings()).use(context.getSource().getWorld(), context.getSource().getPlayer(), net.minecraft.util.Hand.MAIN_HAND);
                break;
                case "eighthgate":
                new JutsuItems.EighthGateItem(new net.minecraft.item.Item.Settings()).use(context.getSource().getWorld(), context.getSource().getPlayer(), net.minecraft.util.Hand.MAIN_HAND);
                break;
                case "chidori":
                new JutsuItems.ChidoriItem(new net.minecraft.item.Item.Settings()).use(context.getSource().getWorld(), context.getSource().getPlayer(), net.minecraft.util.Hand.MAIN_HAND);
                break;
                default:
                context.getSource().sendFeedback(() -> Text.literal("Unknown jutsu: " + jutsu), false);
                break;
            }
            return 1;
        })));
    }
}
