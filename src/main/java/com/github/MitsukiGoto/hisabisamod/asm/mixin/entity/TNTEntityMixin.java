package com.github.MitsukiGoto.hisabisamod.asm.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(TNTEntity.class)
public class TNTEntityMixin {
    @Redirect(method= "explode()V", at=@At(value="INVOKE", target="Lnet/minecraft/world/World;explode(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/Explosion$Mode;)Lnet/minecraft/world/Explosion;"))
    private Explosion inject(World world, Entity entity, double x_pos, double y_pos, double z_pos, float p_217385_8_, Explosion.Mode mode) {
        for(int i = 0; i < 100; i++) {
            Random random = new Random();
            float rand = random.nextFloat();
            world.explode(entity, x_pos + rand, y_pos + rand, z_pos - rand, 30.0f, mode);
        }
        return world.explode(entity, x_pos, y_pos, z_pos, 30.0f, mode);
    }
}
