package com.vendoau.grassblockfix.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.block.Grass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBase.class)
public class BlockBaseMixin {

    @Inject(method = "getTextureForSide(II)I", at = @At("HEAD"), cancellable = true)
    public void getTextureForSide(int side, int meta, CallbackInfoReturnable<Integer> cir) {
        //noinspection ConstantValue
        if ((Object) this instanceof Grass) {
            if (side == 0) {
                cir.setReturnValue(2);
            } else if (side == 1) {
                cir.setReturnValue(0);
            }
        }
    }
}
