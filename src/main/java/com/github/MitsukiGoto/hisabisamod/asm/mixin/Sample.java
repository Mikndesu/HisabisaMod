package com.github.MitsukiGoto.hisabisamod.asm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

@Mixin(CreeperEntity.class)
public class Sample {
    @Inject(at=@At("RETURN"), method="getHurtSound(Lnet/minecraft/util/DamageSource;)Lnet/minecraft/util/SoundEvent", cancellable=true)
    private void injection(CallbackInfoReturnable<SoundEvent> info) {
        info.setReturnValue(SoundEvents.ZOMBIE_HURT);
    }
    
}