package com.stack999;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.Mod;

/**
 * 建筑材料 999 模组入口。
 *
 * <p>可堆叠物品由数据包物品标签 {@code stack999:stack_999} 决定，
 * 因此服务器管理员可以直接用数据包调整清单，不必重新编译模组。</p>
 */
@Mod(Stack999Mod.MOD_ID)
public final class Stack999Mod {
    public static final String MOD_ID = "stack999";
    public static final int MAX_STACK_SIZE = 999;

    public static final TagKey<Item> STACK_999 = TagKey.create(
        Registries.ITEM,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "stack_999")
    );

    public Stack999Mod() {
    }

    public static boolean canStackTo999(ItemStack stack) {
        return !stack.isEmpty() && stack.is(STACK_999);
    }
}
