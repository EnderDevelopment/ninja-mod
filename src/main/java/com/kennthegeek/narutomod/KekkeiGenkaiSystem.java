package com.kennthegeek.narutomod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;

public
class KekkeiGenkaiSystem {
    public static void tick(PlayerEntity player) {
        NbtCompound persistentData = player.getPersistentData();
        int ninjaXp = persistentData.getInt("ninjaXp");
        if (ninjaXp >= 300) {
            if (Random.create().nextFloat() < 0.001) {
                String tag = getRandomTag();
                player.addCommandTag(tag);
                if (player instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) player).sendMessage(Text.literal("You have awakened the " + tag + " Kekkei Genkai!"));
                }
            }
        }
    }

    private static String getRandomTag() {
        String[] tags = {
            "lava", "ice", "wood", "scorch", "storm"
        };
        return tags[Random.create().nextInt(tags.length)];
    }
}
