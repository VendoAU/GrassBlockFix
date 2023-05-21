package com.vendoau.grassblockfix.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.block.Grass;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TileRenderer;
import net.minecraft.client.render.block.GrassColour;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(TileRenderer.class)
public abstract class TileRendererMixin {

    @Inject(method = "method_48", at = @At("HEAD"), cancellable = true)
    public void method_48(BlockBase arg, int i, float f, CallbackInfo ci) {
        if (!(arg instanceof Grass)) return;

        final Tessellator tessellator = Tessellator.INSTANCE;
        arg.method_1605();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.start();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        method_46(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(0, i));
        tessellator.draw();

        // Top side of grass block
        final Color color = new Color(GrassColour.get(0.5, 1));
        GL11.glColor3f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F);
        tessellator.start();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        method_55(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(1, i));
        tessellator.draw();
        GL11.glColor3f(1, 1, 1);

        tessellator.start();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        method_61(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(2, i));
        tessellator.draw();
        tessellator.start();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        method_65(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(3, i));
        tessellator.draw();
        tessellator.start();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        method_67(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(4, i));
        tessellator.draw();
        tessellator.start();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        method_69(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(5, i));
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);

        ci.cancel();
    }

    @Shadow
    public void method_46(BlockBase arg, double d, double d1, double d2, int i) {}

    @Shadow
    public void method_55(BlockBase arg, double d, double d1, double d2, int i) {}

    @Shadow
    public void method_61(BlockBase arg, double d, double d1, double d2, int i) {}

    @Shadow
    public void method_65(BlockBase arg, double d, double d1, double d2, int i) {}

    @Shadow
    public void method_67(BlockBase arg, double d, double d1, double d2, int i) {}

    @Shadow
    public void method_69(BlockBase arg, double d, double d1, double d2, int i) {}
}