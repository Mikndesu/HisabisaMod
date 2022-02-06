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
    @Redirect(method = "explode()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;explode(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/Explosion$Mode;)Lnet/minecraft/world/Explosion;"))
    private Explosion inject(World world, Entity entity, double x_pos, double y_pos, double z_pos, float p_217385_8_, Explosion.Mode mode) {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            float rand_x = ((float) (random.nextInt(20) - 10)) * 1.1f;
            float rand_z = ((float) (random.nextInt(20) - 10)) * 1.1f;
            world.explode(entity, x_pos + rand_x, y_pos, z_pos - rand_z, 10.0f, mode);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return world.explode(entity, x_pos, y_pos, z_pos, 100.0f, mode);
    }
}
