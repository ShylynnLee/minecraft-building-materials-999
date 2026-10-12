package com.stack999.mixin;

import com.stack999.Stack999Mod;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * NeoForge 默认物品处理器的槽位上限常量为 99；放宽到 999 以兼容自动化模组。
 */
@Mixin(ItemStackHandler.class)
public abstract class ItemStackHandlerLimitMixin {

    @Inject(method = "getSlotLimit", at = @At("HEAD"), cancellable = true)
    private void stack999$raiseItemHandlerLimit(int slot, CallbackInfoReturnable<Integer> callback) {
        callback.setReturnValue(Stack999Mod.MAX_STACK_SIZE);
    }
}
