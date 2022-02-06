package com.github.MitsukiGoto.hisabisamod.asm.mixin.entity;

import com.github.MitsukiGoto.hisabisamod.enchant.LavaWalkerEnchantment;
import com.github.MitsukiGoto.hisabisamod.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method= "onChangedBlock(Lnet/minecraft/util/math/BlockPos;)V", at=@At("HEAD"))
    private void inject(BlockPos p_184594_1_, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        int k = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), livingEntity);
        if (k > 0) {
            LavaWalkerEnchantment.onEntityMoved(livingEntity, livingEntity.level, p_184594_1_, k);
        }
    }
}
