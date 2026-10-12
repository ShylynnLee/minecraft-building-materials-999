package com.stack999.mixin;

import com.stack999.Stack999Mod;
import net.neoforged.neoforge.items.wrapper.EntityEquipmentInvWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * NeoForge 装备容器能力也会使用 99 的绝对上限。盔甲槽原有的 1 个限制不受影响。
 */
@Mixin(EntityEquipmentInvWrapper.class)
public abstract class EntityEquipmentInvWrapperLimitMixin {

    @ModifyConstant(
        method = "getSlotLimit",
        constant = @Constant(intValue = 99)
    )
    private int stack999$raiseEquipmentHandlerLimit(int original) {
        return Stack999Mod.MAX_STACK_SIZE;
    }
}
