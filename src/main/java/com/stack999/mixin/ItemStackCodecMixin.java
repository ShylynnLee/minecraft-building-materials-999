package com.stack999.mixin;

import com.stack999.Stack999Mod;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * 原版 ItemStack 数量字段编码上限为 99。
 *
 * <p>网络协议本身使用 VarInt，可以把 999 正常发送；这里同步放宽保存/加载
 * ItemStack 的数量字段，避免玩家背包、箱子等容器重新登录时丢失多余物品。</p>
 */
@Mixin(ItemStack.class)
public abstract class ItemStackCodecMixin {

    @ModifyConstant(
        method = "lambda$static$3",
        constant = @Constant(intValue = 99)
    )
    private static int stack999$allowLargeStackSerialization(int original) {
        return Stack999Mod.MAX_STACK_SIZE;
    }
}
