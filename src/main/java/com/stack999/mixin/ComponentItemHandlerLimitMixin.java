package com.stack999.mixin;

import com.stack999.Stack999Mod;
import net.neoforged.neoforge.items.ComponentItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 放宽 NeoForge 组件式物品处理器的槽位上限。
 */
@Mixin(ComponentItemHandler.class)
public abstract class ComponentItemHandlerLimitMixin {

    @Inject(method = "getSlotLimit", at = @At("HEAD"), cancellable = true)
    private void stack999$raiseComponentItemHandlerLimit(int slot, CallbackInfoReturnable<Integer> callback) {
        callback.setReturnValue(Stack999Mod.MAX_STACK_SIZE);
    }
}
