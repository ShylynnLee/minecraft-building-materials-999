package com.stack999.mixin;

import com.stack999.Stack999Mod;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 让标签中的物品使用 999 作为物品自身的最大堆叠数。
 */
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "getMaxStackSize", at = @At("HEAD"), cancellable = true)
    private void stack999$setMaxStackSize(CallbackInfoReturnable<Integer> callback) {
        ItemStack self = (ItemStack) (Object) this;
        if (Stack999Mod.canStackTo999(self)) {
            callback.setReturnValue(Stack999Mod.MAX_STACK_SIZE);
        }
    }
}
