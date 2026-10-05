package com.kennthegeek.narutomod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public
class JutsuItems {
    public static
    class KamuiEyeItem extends Item {
        public KamuiEyeItem(Settings settings) {
            super(settings);
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            if (!world.isClient) {
                NbtCompound persistentData = player.getPersistentData();
                int chakra = persistentData.getInt("chakra");
                if (chakra >= 40) {
                    persistentData.putInt("chakra", chakra - 40);
                    player.setInvisible(true);
                    world.getServer().getPlayerManager().sendToAll(Text.literal(player.getName().getString() + " uses Kamui Eye!"));
                    new Thread(() -> {
                        try {
                            Thread.sleep(30000);
                            player.setInvisible(false);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } else {
                    if (player instanceof ServerPlayerEntity) {
                        ((ServerPlayerEntity) player).sendMessage(Text.literal("Not enough Chakra!"));
                    }
                }
            }
            return TypedActionResult.success(player.getStackInHand(hand));
        }
    }

    public static
    class EighthGateItem extends Item {
        public EighthGateItem(Settings settings) {
            super(settings);
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            if (!world.isClient) {
                NbtCompound persistentData = player.getPersistentData();
                int chakra = persistentData.getInt("chakra");
                if (chakra >= 90) {
                    persistentData.putInt("chakra", chakra - 90);
                    player.setHealth(player.getMaxHealth() * 0.9f);
                    player.getAbilities().allowFlying = true;
                    player.getAbilities().invulnerable = true;
                    player.getAbilities().setFlySpeed(0.1f);
                    world.getServer().getPlayerManager().sendToAll(Text.literal(player.getName().getString() + " uses Eighth Gate!"));
                } else {
                    if (player instanceof ServerPlayerEntity) {
                        ((ServerPlayerEntity) player).sendMessage(Text.literal("Not enough Chakra!"));
                    }
                }
            }
            return TypedActionResult.success(player.getStackInHand(hand));
        }
    }

    public static
    class ChidoriItem extends Item {
        public ChidoriItem(Settings settings) {
            super(settings);
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            if (!world.isClient) {
                NbtCompound persistentData = player.getPersistentData();
                int chakra = persistentData.getInt("chakra");
                if (chakra >= 25) {
                    persistentData.putInt("chakra", chakra - 25);
                    player.teleport(player.getX(), player.getY() + 5, player.getZ());
                    world.getServer().getPlayerManager().sendToAll(Text.literal(player.getName().getString() + " uses Chidori!"));
                } else {
                    if (player instanceof ServerPlayerEntity) {
                        ((ServerPlayerEntity) player).sendMessage(Text.literal("Not enough Chakra!"));
                    }
                }
            }
            return TypedActionResult.success(player.getStackInHand(hand));
        }
    }
}
