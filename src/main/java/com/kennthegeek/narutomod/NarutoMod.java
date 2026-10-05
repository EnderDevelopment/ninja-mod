package com.kennthegeek.narutomod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public
class NarutoMod implements ModInitializer {
    public static final String MOD_ID = "narutomod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Naruto Mod");
        registerHud();
        registerCommands();
    }

    private void registerHud() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            PlayerEntity player = client.player;
            if (player != null) {
                int chakra = player.getPersistentData().getInt("chakra");
                int ninjaXp = player.getPersistentData().getInt("ninjaXp");
                drawContext.drawText(client.textRenderer, "Chakra: " + chakra + "/100", 10, 10, 0xFFFFFF, true);
                drawContext.drawText(client.textRenderer, "Ninja XP: " + ninjaXp, 10, 20, 0xFFFFFF, true);
            }
        });
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            AddNinjaXpCommand.register(dispatcher);
            ExecuteJutsuCommand.register(dispatcher);
        });
    }
}
