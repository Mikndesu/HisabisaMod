package com.github.MitsukiGoto.hisabisamod.asm.mixin.World.feature;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootTables;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
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
        HisabisaMod.LOGGER.log(Level.ERROR, "MonsterRoom is at " + p_241855_4_.toString());
        iSeedReader = p_241855_1_;
        pos = p_241855_4_;
    }
    @Redirect(method = "place(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/NoFeatureConfig;)Z", at=@At(value="INVOKE", target="Lnet/minecraft/world/spawner/AbstractSpawner;setEntityId(Lnet/minecraft/entity/EntityType;)V"))
    public void place_setEntity_inject(AbstractSpawner instance, EntityType<?> p_200876_1_) {
        ((MobSpawnerTileEntity)iSeedReader.getBlockEntity(pos)).getSpawner().setEntityId(EntityType.CREEPER);
    }
    @Redirect(method = "place(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/NoFeatureConfig;)Z", at=@At(value="INVOKE", target="Lnet/minecraft/tileentity/LockableLootTileEntity;setLootTable(Lnet/minecraft/world/IBlockReader;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/ResourceLocation;)V"))
    public void lootTable_inject(IBlockReader iBlockReader, Random random, BlockPos blockPos, ResourceLocation p_195479_3_) {
        LockableLootTileEntity.setLootTable(iBlockReader, random, blockPos, new ResourceLocation(HisabisaMod.MODID, "chests/simple_dungeon_custom"));
    }
}
