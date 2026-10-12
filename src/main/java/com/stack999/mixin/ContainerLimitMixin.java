package com.stack999.mixin;

import com.stack999.Stack999Mod;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 原版容器默认只允许 99 个，即使物品自身可堆叠更多也会被 Slot 二次限制。
 *
 * <p>这里只提高普通容器上限；熔炉燃料槽等有明确单物品限制的 Slot 会覆盖自身
 * 的 getMaxStackSize，因此仍保持原行为。</p>
 */
@Mixin(Container.class)
public interface ContainerLimitMixin {

    @Inject(method = "getMaxStackSize()I", at = @At("HEAD"), cancellable = true)
    private void stack999$raiseContainerLimit(CallbackInfoReturnable<Integer> callback) {
        callback.setReturnValue(Stack999Mod.MAX_STACK_SIZE);
    }
}
