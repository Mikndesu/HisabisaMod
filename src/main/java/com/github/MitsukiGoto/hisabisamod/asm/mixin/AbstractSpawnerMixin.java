package com.github.MitsukiGoto.hisabisamod.asm.mixin;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.spawner.AbstractSpawner;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSpawner.class)
public class AbstractSpawnerMixin  {
    @Inject(method= "setEntityId(Lnet/minecraft/entity/EntityType;)V", at=@At("RETURN"))
    private void inject(EntityType<?> p_200876_1_, CallbackInfo info) {
        if(p_200876_1_ == EntityType.CAVE_SPIDER) {
            ((AbstractSpawner)(Object)this).nextSpawnData.getTag().putString("id", Registry.ENTITY_TYPE.getKey(EntityType.WITHER).toString());
            HisabisaMod.LOGGER.log(Level.ERROR, "Injected!!!");
            return;
        }
    }
}
