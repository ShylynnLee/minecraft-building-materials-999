package com.stack999.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 修复 100–999 个物品的数量文字超出槽格并被裁掉的问题。
 *
 * <p>原版把三位数按正常字号右对齐，最左侧数字会落到槽格外面。例如 164 在箱子、
 * 公会仓库等界面里可能只看到“64”。这里保持右边缘不变，把过宽的数量文字缩放到
 * 能完整放进一个 16px 物品格。</p>
 */
@Mixin(GuiGraphics.class)
public abstract class ItemCountRendererMixin {

    @Redirect(
        method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"
        )
    )
    private int stack999$fitLargeCountIntoSlot(
        GuiGraphics self,
        Font font,
        String text,
        int x,
        int y,
        int color,
        boolean shadow
    ) {
        if (text == null) {
            return self.drawString(font, (String) null, x, y, color, shadow);
        }

        int textWidth = font.width(text);
        if (textWidth <= 17) {
            return self.drawString(font, text, x, y, color, shadow);
        }

        float scale = 17.0F / textWidth;
        float rightEdge = x + textWidth;
        PoseStack pose = self.pose();
        pose.pushPose();
        pose.translate(rightEdge, y, 0.0F);
        pose.scale(scale, scale, 1.0F);
        int result = self.drawString(font, text, -textWidth, 0, color, shadow);
        pose.popPose();
        return result;
    }
}
