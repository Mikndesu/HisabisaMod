package com.github.MitsukiGoto.hisabisamod.asm.mixin;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.DungeonsFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.spawner.AbstractSpawner;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(DungeonsFeature.class)
public abstract class DungeonsFeatureMixin {
    ISeedReader iSeedReader;
    BlockPos pos;
    @Inject(method = "place(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/NoFeatureConfig;)Z", at=@At(value="INVOKE", target="Lnet/minecraft/world/spawner/AbstractSpawner;setEntityId(Lnet/minecraft/entity/EntityType;)V"))
    public void place_inject(ISeedReader p_241855_1_, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos p_241855_4_, NoFeatureConfig p_241855_5_, CallbackInfoReturnable<Boolean> cir) {
        HisabisaMod.LOGGER.log(Level.ERROR, "MonsterRoom is created at "+p_241855_4_.toString());
        iSeedReader = p_241855_1_;
        pos = p_241855_4_;
    }
    @Redirect(method = "place(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/NoFeatureConfig;)Z", at=@At(value="INVOKE", target="Lnet/minecraft/world/spawner/AbstractSpawner;setEntityId(Lnet/minecraft/entity/EntityType;)V"))
    public void place_setEntity_inject(AbstractSpawner instance, EntityType<?> p_200876_1_) {
        ((MobSpawnerTileEntity)iSeedReader.getBlockEntity(pos)).getSpawner().setEntityId(EntityType.CREEPER);
    }
}
