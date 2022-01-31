package com.github.MitsukiGoto.hisabisamod.asm.mixin.entity;

import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreeperEntity.class)
public class CreeperMixin {
    @Inject(at=@At("RETURN"), method="getHurtSound(Lnet/minecraft/util/DamageSource;)Lnet/minecraft/util/SoundEvent;", cancellable=true)
    private void injection(CallbackInfoReturnable<SoundEvent> info) {
        info.setReturnValue(SoundEvents.ZOMBIE_HURT);
    }
}

