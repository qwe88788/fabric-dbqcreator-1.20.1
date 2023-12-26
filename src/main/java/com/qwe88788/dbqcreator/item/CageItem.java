package com.qwe88788.dbqcreator.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class CageItem extends Item {
    /**
     * 创建一个CageItem对象
     * @param settings 设置参数
     */
    public CageItem(Settings settings) {
        super(settings);
    }

    /**
     * 使用物品方法，用于捕获实体
     * @param world 世界
     * @param user 玩家实体
     * @param hand 手
     * @return 结果行动作结果
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            HitResult hitResult = user.raycast(5, 0, false);
            if (hitResult.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                Entity entity = entityHitResult.getEntity();
                if (entity != null && !entity.isSpectator() && !entity.isInvulnerable()) {
                    captureEntity(itemStack, entity, user);
                    return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
                }
            }
        }
        return new TypedActionResult<>(ActionResult.PASS, itemStack);
    }

    /**
     * 私钥方法，用于捕获实体
     * @param itemStack 物品栈
     * @param entity 实体
     * @param user 玩家实体
     */
    private void captureEntity(ItemStack itemStack, Entity entity, PlayerEntity user) {
        NbtCompound tag = itemStack.getOrCreateNbt();
        NbtList entitiesTag = tag.getList("Entities", 10);

        NbtCompound entityTag = new NbtCompound();
        Identifier id = EntityType.getId(entity.getType());
        entityTag.putString("id", id.toString());

        // 保存实体的基本数据
        NbtCompound entityData = new NbtCompound();
        entity.writeNbt(entityData);
        entityData.remove("Pos"); // 移除位置信息，避免在读取时出现问题
        entityData.remove("Rotation"); // 移除旋转信息，避免在读取时出现问题
        entityData.remove("Motion"); // 移除运动信息，避免在读取时出现问题
        entityTag.put("entity_data", entityData);

        entitiesTag.add(entityTag);
    }

    /**
     * 使用物品方法，用于在物品上添加提示信息
     * @param stack 物品栈
     * @param world 世界
     * @param tooltip 提示信息列表
     * @param context 提示信息上下文
     */
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound tag = stack.getOrCreateNbt();
        NbtList entitiesTag = tag.getList("Entities", 10);

        if (!entitiesTag.isEmpty()) {
            NbtCompound entityTag = entitiesTag.getCompound(0);
            Identifier id = new Identifier(entityTag.getString("id"));
            Optional<EntityType<?>> entityType = EntityType.get(String.valueOf(id));

            if (entityType.isPresent()) {
                String entityTypeString = entityType.get().getName().toString();
                Text text = Text.literal(entityTypeString).styled(style -> style.withColor(Formatting.GRAY));
                tooltip.add(text);
            } else {
                Text missingType = Text.of("Missing Type");
                tooltip.add(missingType);
            }
        }
    }
}

