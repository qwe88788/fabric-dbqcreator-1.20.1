package com.qwe88788.dbqcreator.event;

import com.qwe88788.dbqcreator.item.CageItem;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CageItemEventHandler {
    public static void register() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.getItem() instanceof CageItem) {
                releaseEntity(player, world, itemStack);
                return TypedActionResult.success(itemStack);
            }
            return TypedActionResult.pass(itemStack);
        });
    }

    private static void releaseEntity(PlayerEntity player, World world, ItemStack itemStack) {
        NbtCompound tag = itemStack.getOrCreateNbt();
        NbtList entitiesTag = tag.getList("Entities", 10);

        EntityType<?> entityType = null;
        if (!entitiesTag.isEmpty()) {
            NbtCompound entityTag = (NbtCompound) entitiesTag.get(0);
            Identifier id = new Identifier(entityTag.getString("id"));
            entityType = Registries.ENTITY_TYPE.get(id);
            // 这里可以根据实体类型创建实体对象并设置NBT数据
            Entity entity = entityType.create(world);
            if (entity != null) {
                entity.readNbt(entityTag);
            }
            if (entity != null) {
                double x = entityTag.getDouble("x");
                double y = entityTag.getDouble("y");
                double z = entityTag.getDouble("z");
                entity.setPos(x, y, z);
            }
            world.spawnEntity(entity);
            entitiesTag.remove(0);
        }
    }
}