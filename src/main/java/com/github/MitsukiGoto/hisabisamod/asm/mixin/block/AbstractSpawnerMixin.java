package com.github.MitsukiGoto.hisabisamod.asm.mixin.block;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.spawner.AbstractSpawner;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSpawner.class)
public class AbstractSpawnerMixin  {
    @Inject(method= "setEntityId(Lnet/minecraft/entity/EntityType;)V", at=@At("RETURN"))
    private void setEntityId_inject(EntityType<?> p_200876_1_, CallbackInfo info) {
        if(p_200876_1_ == EntityType.CAVE_SPIDER) {
            ((AbstractSpawner)(Object)this).nextSpawnData.getTag().putString("id", Registry.ENTITY_TYPE.getKey(EntityType.CREEPER).toString());
        }
    }
    @Redirect(method= "tick()V", at=@At(shift=At.Shift.AFTER,value="INVOKE",target = "Lnet/minecraft/entity/MobEntity;finalizeSpawn(Lnet/minecraft/world/IServerWorld;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/entity/ILivingEntityData;Lnet/minecraft/nbt/CompoundNBT;)Lnet/minecraft/entity/ILivingEntityData;"))
    private void tick_inject(Entity entity, CallbackInfo ci) {
        if(entity instanceof CreeperEntity) {
        }
    }
}
